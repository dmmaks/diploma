package com.diploma.repository.interfaces;

import com.diploma.model.WrongAttemptLogin;

import java.time.LocalDateTime;

public interface WrongAttempLoginRepository extends BaseCrudRepository<WrongAttemptLogin,Long>{
    WrongAttemptLogin findByIpAndTime(String ip, LocalDateTime time);
}
