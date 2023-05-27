package com.edu.netc.bakensweets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Technique {
    private long id;
    private String name;
    private String description;
}
