package com.edu.netc.bakensweets.controller;

import com.edu.netc.bakensweets.dto.*;
import com.edu.netc.bakensweets.model.TechniqueMitigationEntity;
import com.edu.netc.bakensweets.service.interfaces.TechniqueMitigationService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@Validated
@RequestMapping("/api/techniquesMitigations")
public class TechniqueMitigationController {
    private final TechniqueMitigationService techniqueMitigationService;

    public TechniqueMitigationController(TechniqueMitigationService techniqueMitigationService) {
        this.techniqueMitigationService = techniqueMitigationService;
    }

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/techniques/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 404, message = "Item not found")})
    public TechniqueMitigationDTO getTechniqueById(@PathVariable long id) {
        return techniqueMitigationService.getTechniqueMitigationById(id, TechniqueMitigationEntity.TECHNIQUE);
    }

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/mitigations/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 404, message = "Item not found")})
    public TechniqueMitigationDTO getMitigationById(@PathVariable long id) {
        return techniqueMitigationService.getTechniqueMitigationById(id, TechniqueMitigationEntity.MITIGATION);
    }

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR')")
    @GetMapping("/techniques")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request")})
    public PaginationDTO<TechniqueMitigationDTO> getFilteredTechniques(
            @RequestParam(value = "pageSize", defaultValue = "12", required = false) @Min(value = 1, message = "Page size must be higher than 0") int pageSize,
            @RequestParam(value = "pageNum", defaultValue = "0", required = false) @Min(value = 0, message = "Current page must be higher than 0") int currentPage,
            @RequestParam(value = "name", defaultValue = "", required = false) String name,
            @RequestParam(value = "order", defaultValue = "true", required = false) boolean order) {
        return techniqueMitigationService.getFilteredTechniquesMitigations(name, pageSize, order, currentPage, TechniqueMitigationEntity.TECHNIQUE);
    }

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR')")
    @GetMapping("/mitigations")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request")})
    public PaginationDTO<TechniqueMitigationDTO> getFilteredMitigations(
            @RequestParam(value = "pageSize", defaultValue = "12", required = false) @Min(value = 1, message = "Page size must be higher than 0") int pageSize,
            @RequestParam(value = "pageNum", defaultValue = "0", required = false) @Min(value = 0, message = "Current page must be higher than 0") int currentPage,
            @RequestParam(value = "name", defaultValue = "", required = false) String name,
            @RequestParam(value = "order", defaultValue = "true", required = false) boolean order) {
        return techniqueMitigationService.getFilteredTechniquesMitigations(name, pageSize, order, currentPage, TechniqueMitigationEntity.MITIGATION);
    }

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR')")
    @GetMapping(value = "/techniques/withLinks/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 404, message = "Item not found")})
    public TechniqueMitigationWithLinksDTO getTechniqueWithLinksById(@PathVariable long id) {
        return techniqueMitigationService.getTechniqueMitigationWithLinksById(id, TechniqueMitigationEntity.TECHNIQUE);
    }

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR')")
    @GetMapping(value = "/mitigations/withLinks/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 404, message = "Item not found")})
    public TechniqueMitigationWithLinksDTO getMitigationWithLinksById(@PathVariable long id) {
        return techniqueMitigationService.getTechniqueMitigationWithLinksById(id, TechniqueMitigationEntity.MITIGATION);
    }

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR')")
    @DeleteMapping(value = "/techniques/{id}")
    public void deleteTechniqueById(@PathVariable long id) {
        techniqueMitigationService.deleteTechniqueMitigation(id, TechniqueMitigationEntity.TECHNIQUE);
    }

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR')")
    @GetMapping(value = "/techniques/applicability/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 404, message = "Item not found")})
    public ApplicabilityDTO getApplicabilityByTechniqueId(@PathVariable long id) {
        return techniqueMitigationService.getApplicabilityByTechniqueId(id);
    }

    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PostMapping(value = "/techniques")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Technique has been added"),
            @ApiResponse(code = 400, message = "Something went wrong")})
    public void createTechnique(@RequestBody @Valid TechniqueApplicabilityWithLinksDTO techniqueApplicabilityWithLinksDTO) {
        techniqueMitigationService.createTechnique(techniqueApplicabilityWithLinksDTO.getTechniqueWithLinks(),
                techniqueApplicabilityWithLinksDTO.getApplicability());
    }

    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PutMapping(value = "/techniques/{id}")
    public void updateTechnique(@PathVariable long id, @RequestBody @Valid TechniqueApplicabilityWithLinksDTO techniqueApplicabilityWithLinksDTO) {
        techniqueMitigationService.updateTechnique(id, techniqueApplicabilityWithLinksDTO.getTechniqueWithLinks(),
                techniqueApplicabilityWithLinksDTO.getApplicability());
    }

    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PostMapping(value = "/mitigations")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Technique has been added"),
            @ApiResponse(code = 400, message = "Something went wrong")})
    public void createMitigation(@RequestBody @Valid TechniqueMitigationWithLinksDTO techniqueMitigationWithLinksDTO) {
        techniqueMitigationService.createMitigation(techniqueMitigationWithLinksDTO);
    }

    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PutMapping(value = "/mitigations/{id}")
    public void updateMitigation(@PathVariable long id, @RequestBody @Valid TechniqueMitigationWithLinksDTO techniqueMitigationWithLinksDTO) {
        techniqueMitigationService.updateMitigation(id, techniqueMitigationWithLinksDTO);
    }
}
