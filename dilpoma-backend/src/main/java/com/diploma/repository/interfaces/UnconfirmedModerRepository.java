package com.diploma.repository.interfaces;

import com.diploma.model.UnconfirmedModerator;

import java.time.LocalDateTime;

public interface UnconfirmedModerRepository extends BaseCrudRepository<UnconfirmedModerator, Integer> {
    UnconfirmedModerator getByToken(String token);
    LocalDateTime findLatestExpiryDate(String email);
}
