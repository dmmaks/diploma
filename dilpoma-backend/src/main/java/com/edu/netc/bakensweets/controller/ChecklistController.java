package com.edu.netc.bakensweets.controller;

import com.edu.netc.bakensweets.dto.ChecklistDTO;
import com.edu.netc.bakensweets.service.interfaces.ChecklistService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

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
    public ChecklistDTO getGeneratedModelByDeviceId (@PathVariable long id, Principal principal) {
        return checklistService.getChecklistById(principal.getName(), id);
    }
}
