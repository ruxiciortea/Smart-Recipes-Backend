package com.ruxiciortea.Smart.Recipes.Controller;

import com.ruxiciortea.Smart.Recipes.Util.DTO.User.UserAuthenticationDTO;
import com.ruxiciortea.Smart.Recipes.Util.DTO.TokenDTO;
import com.ruxiciortea.Smart.Recipes.Util.DTO.User.UserRegistrationDTO;
import com.ruxiciortea.Smart.Recipes.Service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<TokenDTO> register(@RequestBody @Valid UserRegistrationDTO request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<TokenDTO> authenticate(@RequestBody @Valid UserAuthenticationDTO request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

}
