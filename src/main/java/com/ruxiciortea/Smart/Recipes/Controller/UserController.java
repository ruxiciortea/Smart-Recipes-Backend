package com.ruxiciortea.Smart.Recipes.Controller;

import com.ruxiciortea.Smart.Recipes.Service.UserService;
import com.ruxiciortea.Smart.Recipes.Util.DTO.User.UserDetailsDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/details")
    public ResponseEntity<UserDetailsDTO> register(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth) throws Exception {
        return ResponseEntity.ok(userService.getUser(auth.substring(7)));
    }

    @PostMapping("/update")
    public ResponseEntity<UserDetailsDTO> update(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth,
                                                 @RequestBody @Valid UserDetailsDTO user) throws Exception {
        return ResponseEntity.ok(userService.updateUser(auth.substring(7), user));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        boolean deleteStatus = userService.deleteUser(auth.substring(7));

        if (deleteStatus) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

}
