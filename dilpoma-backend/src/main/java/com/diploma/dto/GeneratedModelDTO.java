package com.diploma.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

@Data
@RequiredArgsConstructor
public class GeneratedModelDTO {
    private final Collection<GeneratedModelEntryDTO> content;
}
