package com.diploma.repository;

import com.diploma.repository.interfaces.UnconfirmedModerRepository;
import com.diploma.model.UnconfirmedModerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class UnconfirmedModerRepositoryImpl extends BaseJdbcRepository implements UnconfirmedModerRepository {

    @Value("${sql.unconfirmedModer.create}")
    private String sqlCreateQuery;

    @Value("${sql.unconfirmedModer.getByToken}")
    private String sqlGetByToken;

    @Value("${sql.unconfirmedModer.getLatestExpiryDate}")
    private String getLatestExpiryDate;

    public UnconfirmedModerRepositoryImpl (JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public long create (UnconfirmedModerator moderator) {
        jdbcTemplate.update(
                sqlCreateQuery, moderator.getModerToken(), moderator.getExpiryDate(), moderator.getEmail(),
                moderator.getFirstName(), moderator.getLastName(), moderator.getBirthDate(), moderator.getGender().name()
        );
        return 0;
    }

    @Override
    public UnconfirmedModerator getByToken (String token) {
        return jdbcTemplate.queryForObject(sqlGetByToken,  new BeanPropertyRowMapper<>(UnconfirmedModerator.class), token);
    }

    @Override
    public boolean update (UnconfirmedModerator moderator) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean deleteById (Integer integer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public UnconfirmedModerator findById (Integer id) {
        throw new UnsupportedOperationException();
    }


    @Override
    public LocalDateTime findLatestExpiryDate(String email) {
        return jdbcTemplate.queryForObject(getLatestExpiryDate, LocalDateTime.class, email);
    }
}
