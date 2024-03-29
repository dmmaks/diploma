package com.diploma.service;

import com.diploma.exception.CustomException;
import com.diploma.repository.interfaces.AccountRepository;
import com.diploma.repository.interfaces.CredentialsRepository;
import com.diploma.repository.interfaces.UnconfirmedModerRepository;
import com.diploma.service.interfaces.EmailSenderService;
import com.diploma.service.interfaces.ModerCreationService;
import com.diploma.dto.NewModeratorDTO;
import com.diploma.mapper.AccountMapper;
import com.diploma.mapper.ModeratorMapper;
import com.diploma.model.Account;
import com.diploma.model.AccountRole;
import com.diploma.model.Credentials;
import com.diploma.model.UnconfirmedModerator;
import com.diploma.model.payload.AuthRequestResetUpdatePassword;
import com.diploma.utils.Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ModerCreationServiceImpl implements ModerCreationService {
    @Value("24")
    private Long expiration;

    CredentialsRepository credentialsRepository;
    AccountRepository accountRepository;
    UnconfirmedModerRepository moderRepository;
    AccountMapper accountMapper;
    ModeratorMapper moderatorMapper;
    EmailSenderService emailSenderService;
    PasswordEncoder passwordEncoder;

    public ModerCreationServiceImpl (CredentialsRepository credentialsRepository,
                                     UnconfirmedModerRepository moderRepository,
                                     AccountMapper accountMapper,
                                     ModeratorMapper moderatorMapper,
                                     EmailSenderService emailSenderService,
                                     PasswordEncoder passwordEncoder,
                                     AccountRepository accountRepository) {
        this.credentialsRepository = credentialsRepository;
        this.moderRepository = moderRepository;
        this.accountMapper = accountMapper;
        this.moderatorMapper = moderatorMapper;
        this.emailSenderService = emailSenderService;
        this.passwordEncoder = passwordEncoder;
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public void createToken(NewModeratorDTO moderatorDTO) {
        if (emailIsUnique(moderatorDTO.getEmail()) && hasNoActualExpiryDate(moderatorDTO.getEmail())) {
            UnconfirmedModerator moderator = getModerWithToken(moderatorDTO);
            moderRepository.create(moderator);
            emailSenderService.sendNewModerLinkPassword(moderator.getEmail(), moderator.getModerToken());
        }
    }

    @Override
    public boolean validateModerToken(String token){
        UnconfirmedModerator moderator = moderRepository.getByToken(token);
        if (moderator != null) {
            if (moderator.getExpiryDate().isAfter(LocalDateTime.now())) {
                return true;
            }
            throw new CustomException(HttpStatus.GONE, "token is not valid");
        }
        throw new CustomException(HttpStatus.NOT_FOUND, "token not found");
    }

    @Override
    @Transactional
    public void createAccount(AuthRequestResetUpdatePassword authRequestResetUpdatePassword) {
        if (!validateModerToken(authRequestResetUpdatePassword.getToken())) {
            throw new CustomException(HttpStatus.GONE, "Invalid or expired token");
        }
        UnconfirmedModerator moderator = moderRepository.getByToken(authRequestResetUpdatePassword.getToken());
        if (moderator == null) throw new CustomException(HttpStatus.NOT_FOUND, "Moderator is not found");

        String password = passwordEncoder.encode(authRequestResetUpdatePassword.getPassword());
        Long id = Utils.generateUniqueId();

        credentialsRepository.create(new Credentials(id, moderator.getEmail(), password));
        Account account = moderatorMapper.unconfirmedModerToAccount(moderator);
        account.setAccountRole(AccountRole.ROLE_MODERATOR);
        account.setId(id);
        accountRepository.create(account);
    }

    private UnconfirmedModerator getModerWithToken (NewModeratorDTO moderatorDTO) {
        UnconfirmedModerator moderator = moderatorMapper.newModerDTOtoUnconfirmedModer((moderatorDTO));
        moderator.setModerToken(Utils.stringGenerateUniqueId());
        moderator.setExpiryDate(LocalDateTime.now().plusHours(expiration));
        return moderator;
    }

    private boolean emailIsUnique(String email) {
        if (credentialsRepository.getCountEmailUsages(email) == 0) return true;
        else throw new CustomException(HttpStatus.CONFLICT, "email in not unique");
    }

    private boolean hasNoActualExpiryDate (String email) {
        LocalDateTime expiryDate = moderRepository.findLatestExpiryDate(email);
        if ( expiryDate != null && expiryDate.isAfter(LocalDateTime.now())) {
            throw new CustomException(HttpStatus.FORBIDDEN, "this email has actual link that is valid until " + expiryDate);
        }
        return true;
    }
}
