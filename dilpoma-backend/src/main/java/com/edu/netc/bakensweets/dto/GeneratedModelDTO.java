package com.edu.netc.bakensweets.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

@Data
@RequiredArgsConstructor
public class GeneratedModelDTO {
    private final Collection<GeneratedModelEntryDTO> content;
}
