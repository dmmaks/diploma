package com.diploma.repository.interfaces;



import com.diploma.model.Account;
import com.diploma.model.AccountRole;

import java.util.Collection;

public interface AccountRepository extends BaseCrudRepository<Account, Long>{
    Account findByEmail(String email);
    int countAccountsBySearch (String search, AccountRole role, String gender, String status);
    Collection<Account> findAccountsBySearch (String search, String gender, AccountRole role, String status, int limit, int offset, boolean order);
    void updateStatus(long id);
}
