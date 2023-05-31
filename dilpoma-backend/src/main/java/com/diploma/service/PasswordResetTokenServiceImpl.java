package com.diploma.service;

import com.diploma.exception.BadRequestParamException;
import com.diploma.exception.CustomException;
import com.diploma.repository.interfaces.CredentialsRepository;
import com.diploma.repository.interfaces.PasswordResetTokenRepository;
import com.diploma.service.interfaces.EmailSenderService;
import com.diploma.service.interfaces.PasswordResetTokenService;
import com.diploma.model.Credentials;
import com.diploma.model.PasswordResetToken;
import com.diploma.model.payload.AuthRequestResetUpdatePassword;
import com.diploma.utils.Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {
    private final PasswordResetTokenRepository passResetTokenRepository;
    private final CredentialsRepository credentialsRepository;
    private final EmailSenderService emailSenderService;
    private final PasswordEncoder passwordEncoder;
    @Value("60")
    private Long expiration;

    public PasswordResetTokenServiceImpl(PasswordResetTokenRepository passResetTokenRepository, CredentialsRepository credentialsRepository,
                                         EmailSenderService emailSenderService, PasswordEncoder passwordEncoder) {
        this.passResetTokenRepository = passResetTokenRepository;
        this.credentialsRepository = credentialsRepository;
        this.emailSenderService = emailSenderService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createToken(String email) {
        try {
            Credentials credentials = credentialsRepository.findByEmail(email);
            disableAllTokensByAccountId(credentials.getId());
            PasswordResetToken passwordResetToken = generateToken(credentials.getId());
            emailSenderService.sendResetLinkPassword(email, passwordResetToken.getResetToken());
            passResetTokenRepository.create(passwordResetToken);
        } catch (EmptyResultDataAccessException ex){
            throw new CustomException(HttpStatus.NOT_FOUND, String.format("Account %s not found.", email));
        }
    }

    @Override
    public boolean validateResetToken(String token){
        try {
            PasswordResetToken passwordResetToken = passResetTokenRepository.findByToken(token);
            boolean expiry = passwordResetToken.isActive() && passwordResetToken.getExpiryDate().isAfter(LocalDateTime.now());
            if(!expiry)
                throw new CustomException(HttpStatus.GONE, "The link to change the password is invalid");
            return true;
        } catch (EmptyResultDataAccessException ex){
            throw new CustomException(HttpStatus.NOT_FOUND, String.format("The link to change the password is invalid", token));
        }
    }

    @Override
    public void changePassword(AuthRequestResetUpdatePassword authRequestResetUpdatePassword){
        String token = authRequestResetUpdatePassword.getToken();
        String newPassword = authRequestResetUpdatePassword.getPassword();
        validateResetToken(token);
        PasswordResetToken passResetToken = passResetTokenRepository.findByToken(token);
        Credentials credentials = credentialsRepository.findById(passResetToken.getAccountId());
        if(passwordEncoder.matches(newPassword, credentials.getPassword()))
            throw new BadRequestParamException("password", "The new password is similar to the old one");
        credentials.setPassword(passwordEncoder.encode(newPassword));
        credentialsRepository.update(credentials);
        passResetToken.setActive(false);
        passResetTokenRepository.update(passResetToken);
    }

    @Override
    public PasswordResetToken generateToken(Long userId) {
        return new PasswordResetToken(Utils.stringGenerateUniqueId(),
                LocalDateTime.now().plusMinutes(expiration),
                userId,
                true);
    }

    public void disableAllTokensByAccountId(Long accountId){
        passResetTokenRepository.disableAllTokensByAccountId(accountId);
    }
}
