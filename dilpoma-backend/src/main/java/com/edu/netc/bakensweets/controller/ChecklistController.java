package com.edu.netc.bakensweets.controller;

import com.edu.netc.bakensweets.dto.ChecklistDTO;
import com.edu.netc.bakensweets.service.interfaces.ChecklistService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;

@RestController
@Validated
@RequestMapping("/api/checklists")
public class ChecklistController {
    private final ChecklistService checklistService;

    public ChecklistController(ChecklistService checklistService) {
        this.checklistService = checklistService;
    }

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request")})
    public ChecklistDTO getChecklistById (@PathVariable long id, Principal principal) {
        return checklistService.getChecklistById(principal.getName(), id);
    }

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_USER', 'ROLE_ADMIN')")
    @PutMapping(value = "/checklistEntries/changeIsChecked/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request")})
    public void updateIsChecklistEntryChecked (@PathVariable long id,
                                               @RequestParam(value = "isChecked", defaultValue = "true", required = true) boolean isChecked,
                                               Principal principal) {
        checklistService.updateIsChecked(principal.getName(), id, isChecked);
    }

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request")})
    public Collection<ChecklistDTO> getUserChecklists (@RequestParam(value = "searchRequest", defaultValue = "") String searchRequest, Principal principal) {
        return checklistService.getUserChecklists(principal.getName(), searchRequest);
    }
}
