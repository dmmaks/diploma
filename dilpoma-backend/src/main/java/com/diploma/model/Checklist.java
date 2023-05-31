package com.diploma.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Checklist {
    private long id;
    private String name;
    private String deviceName;
    private long accountId;
}
