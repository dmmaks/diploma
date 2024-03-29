package com.diploma.repository;

import com.diploma.repository.interfaces.PasswordResetTokenRepository;
import com.diploma.model.PasswordResetToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PasswordResetTokenRepositoryImpl extends BaseJdbcRepository implements PasswordResetTokenRepository {
    @Value("${sql.passwordResetToken.create}")
    private String sqlQueryCreate;
    @Value("${sql.passwordResetToken.findByAccountId}")
    private String sqlQueryFindByAccountId;
    @Value("${sql.passwordResetToken.disableAllActiveSessionResetLink}")
    private String sqlQueryDisableAllTokens;
    @Value("${sql.passwordResetToken.findByToken}")
    private String sqlQueryFindByToken;
    @Value("${sql.passwordResetToken.findById}")
    private String sqlQueryFindById;
    @Value("${sql.passwordResetToken.update}")
    private String sqlQueryUpdate;

    public PasswordResetTokenRepositoryImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public long create(PasswordResetToken passResetToken) {
        jdbcTemplate.update(sqlQueryCreate, passResetToken.getResetToken(),
                passResetToken.getExpiryDate(),
                passResetToken.getAccountId(),
                passResetToken.isActive());
        return 0;
    }

    @Override
    public boolean update(PasswordResetToken passResetToken) {
        jdbcTemplate.update(sqlQueryUpdate, passResetToken.getResetToken(), passResetToken.getExpiryDate(), passResetToken.getAccountId(),
                passResetToken.isActive(), passResetToken.getId());
        return true;
    }

    @Override
    public boolean deleteById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public PasswordResetToken findById(Long id) {
        return jdbcTemplate.queryForObject(sqlQueryFindById, new BeanPropertyRowMapper<>(PasswordResetToken.class), id);
    }

    @Override
    public PasswordResetToken findByAccountId(Long accountId) {
        return jdbcTemplate.queryForObject(sqlQueryFindByAccountId, new BeanPropertyRowMapper<>(PasswordResetToken.class), accountId);
    }

    @Override
    public void disableAllTokensByAccountId(Long id) {
        jdbcTemplate.update(sqlQueryDisableAllTokens, id);
    }

    @Override
    public PasswordResetToken findByToken(String token) {
        return jdbcTemplate.queryForObject(sqlQueryFindByToken, new BeanPropertyRowMapper<>(PasswordResetToken.class), token);
    }
}
