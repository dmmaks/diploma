package com.diploma.controller;

import com.diploma.service.interfaces.ModelGenerationService;
import com.diploma.dto.GeneratedModelDTO;
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
@RequestMapping("/api/modelGeneration")
public class ModelGenerationController {
    private final ModelGenerationService modelGenerationService;

    public ModelGenerationController(ModelGenerationService modelGenerationService) {
        this.modelGenerationService = modelGenerationService;
    }

    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/generate/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request")})
    public GeneratedModelDTO getGeneratedModelByDeviceId (@PathVariable long id, Principal principal) {
        return new GeneratedModelDTO(modelGenerationService.getGeneratedModel(principal.getName(), id));
    }
}
