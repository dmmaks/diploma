package com.edu.netc.bakensweets.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechniqueApplicabilityWithLinksDTO {
    TechniqueMitigationWithLinksDTO techniqueWithLinks;

    ApplicabilityDTO applicability;
}
