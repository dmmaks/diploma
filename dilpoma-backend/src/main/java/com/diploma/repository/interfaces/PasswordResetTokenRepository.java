package com.diploma.repository.interfaces;

import com.diploma.model.PasswordResetToken;

public interface PasswordResetTokenRepository extends BaseCrudRepository<PasswordResetToken, Long>{
    PasswordResetToken findByAccountId(Long id);
    void disableAllTokensByAccountId(Long id);
    PasswordResetToken findByToken(String token);
}
