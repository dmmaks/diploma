package com.diploma.service.interfaces;

import com.diploma.model.PasswordResetToken;
import com.diploma.model.payload.AuthRequestResetUpdatePassword;

public interface PasswordResetTokenService {
    void createToken(String email);
    PasswordResetToken generateToken(Long userId);
    boolean validateResetToken(String token);
    void changePassword(AuthRequestResetUpdatePassword authRequestResetUpdatePassword);
}
