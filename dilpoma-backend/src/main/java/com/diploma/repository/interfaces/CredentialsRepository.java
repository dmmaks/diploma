package com.diploma.repository.interfaces;

import com.diploma.model.Credentials;

public interface CredentialsRepository extends BaseCrudRepository<Credentials, Long> {
    Credentials findByEmail(String email);
    Integer getCountEmailUsages (String email);
}
