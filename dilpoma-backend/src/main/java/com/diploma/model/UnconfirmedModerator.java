package com.diploma.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class UnconfirmedModerator {
    private String moderToken;
    private LocalDateTime expiryDate;
    private String email;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Gender gender;
}
