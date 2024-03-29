package com.diploma.service;

import com.diploma.repository.interfaces.AccountRepository;
import com.diploma.repository.interfaces.CredentialsRepository;
import com.diploma.model.Account;
import com.diploma.model.Credentials;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final CredentialsRepository credentialsRepository;

    public CustomUserDetailsService(AccountRepository accountRepository, CredentialsRepository credentialsRepository) {
        this.accountRepository = accountRepository;
        this.credentialsRepository = credentialsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Credentials credentials = credentialsRepository.findByEmail(username);
        final Account account = accountRepository.findById(credentials.getId());
        if (account == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }
        return User
                .withUsername(username)
                .password(credentials.getPassword())
                .authorities(account.getAccountRole())
                .build();
    }
}
