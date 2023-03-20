package com.ruxiciortea.Smart.Recipes.Service;

import com.ruxiciortea.Smart.Recipes.Config.JwtService;
import com.ruxiciortea.Smart.Recipes.Model.*;
import com.ruxiciortea.Smart.Recipes.Util.DTO.User.UserAuthenticationDTO;
import com.ruxiciortea.Smart.Recipes.Util.DTO.TokenDTO;
import com.ruxiciortea.Smart.Recipes.Util.DTO.User.UserRegistrationDTO;
import com.ruxiciortea.Smart.Recipes.Repository.UserRepository;
import com.ruxiciortea.Smart.Recipes.Util.ReportsGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ReportsGenerator reportsGenerator;

    public TokenDTO register(UserRegistrationDTO request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        reportsGenerator.generateNewUserReport(user);

        return TokenDTO.builder().token(jwtToken).build();
    }

    public TokenDTO authenticate(UserAuthenticationDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword())); // this will throw if no user found

        // if we got here, it means the user exists
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        return TokenDTO.builder().token(jwtToken).build();
    }

}