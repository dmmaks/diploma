package com.diploma.service.interfaces;

import com.diploma.dto.NewModeratorDTO;
import com.diploma.model.payload.AuthRequestResetUpdatePassword;

public interface ModerCreationService {
    void createToken(NewModeratorDTO moderatorDTO);
    boolean validateModerToken(String token);
    void createAccount(AuthRequestResetUpdatePassword authRequestResetUpdatePassword);
}
