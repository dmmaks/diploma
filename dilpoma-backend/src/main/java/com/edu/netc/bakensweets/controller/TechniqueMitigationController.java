package com.edu.netc.bakensweets.controller;

import com.edu.netc.bakensweets.dto.TechniqueMitigationDTO;
import com.edu.netc.bakensweets.model.TechniqueMitigation;
import com.edu.netc.bakensweets.model.TechniqueMitigationEntity;
import com.edu.netc.bakensweets.service.interfaces.TechniqueMitigationService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api/techniques-mitigations")
public class TechniqueMitigationController {
    private final TechniqueMitigationService techniqueMitigationService;

    public TechniqueMitigationController(TechniqueMitigationService techniqueMitigationService) {
        this.techniqueMitigationService = techniqueMitigationService;
    }

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
    @GetMapping(value = "/techniques/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 404, message = "Item not found")})
    public TechniqueMitigationDTO getTechniqueById(@PathVariable long id) {
        return techniqueMitigationService.getTechniqueMitigationById(id, TechniqueMitigationEntity.TECHNIQUE);
    }

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
    @GetMapping(value = "/mitigations/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 404, message = "Item not found")})
    public TechniqueMitigationDTO getMitigationById(@PathVariable long id) {
        return techniqueMitigationService.getTechniqueMitigationById(id, TechniqueMitigationEntity.MITIGATION);
    }
}
