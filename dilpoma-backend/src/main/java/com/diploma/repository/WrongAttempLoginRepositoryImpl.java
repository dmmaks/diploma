package com.diploma.repository;

import com.diploma.repository.interfaces.WrongAttempLoginRepository;
import com.diploma.model.WrongAttemptLogin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class WrongAttempLoginRepositoryImpl extends BaseJdbcRepository implements WrongAttempLoginRepository {

    @Value("${sql.wrongAttemptLogin.create}")
    private String sqlQueryCreate;
    @Value("${sql.wrongAttemptLogin.update}")
    private String sqlQueryUpdate;
    @Value("${sql.wrongAttemptLogin.delete}")
    private String sqlQueryDelete;
    @Value("${sql.wrongAttemptLogin.findById}")
    private String sqlQueryFindById;
    @Value("${sql.wrongAttemptLogin.findByIpAndTime}")
    private String sqlQueryFindByIpAndTime;

    public WrongAttempLoginRepositoryImpl(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public long create(WrongAttemptLogin wrongAttemptLogin) {
        jdbcTemplate.update(sqlQueryCreate, wrongAttemptLogin.getIp(), wrongAttemptLogin.getExpiryTime(), wrongAttemptLogin.getCountWrongAttempts());
        return 0;
    }

    @Override
    public boolean update(WrongAttemptLogin wrongAttemptLogin) {
        jdbcTemplate.update(sqlQueryUpdate, wrongAttemptLogin.getIp(), wrongAttemptLogin.getExpiryTime(), wrongAttemptLogin.getCountWrongAttempts(),
                wrongAttemptLogin.getId());
        return true;
    }

    @Override
    public boolean deleteById(Long id) {
        jdbcTemplate.update(sqlQueryDelete, id);
        return true;
    }

    @Override
    public WrongAttemptLogin findById(Long id) {
        return jdbcTemplate.queryForObject(sqlQueryFindById, new BeanPropertyRowMapper<>(WrongAttemptLogin.class), id);
    }

    @Override
    public WrongAttemptLogin findByIpAndTime(String ip, LocalDateTime time) {
        return jdbcTemplate.queryForObject(sqlQueryFindByIpAndTime, new BeanPropertyRowMapper<>(WrongAttemptLogin.class), ip, time);
    }
}
