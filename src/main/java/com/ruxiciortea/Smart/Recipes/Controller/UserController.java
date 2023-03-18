package com.ruxiciortea.Smart.Recipes.Controller;

import com.ruxiciortea.Smart.Recipes.Service.UserService;
import com.ruxiciortea.Smart.Recipes.Util.DTO.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user-details")
    public ResponseEntity<UserDTO> register(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth) throws Exception {
        return ResponseEntity.ok(userService.getUser(auth.substring(7)));
    }

}
