package com.diploma.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class PasswordResetToken {
    private Long id;
    @NonNull
    private String resetToken;
    @NonNull
    private LocalDateTime expiryDate;
    @NonNull
    private long accountId;
    @NonNull
    private boolean active;

    public PasswordResetToken(){}
}
