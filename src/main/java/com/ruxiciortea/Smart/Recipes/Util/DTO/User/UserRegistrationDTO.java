package com.ruxiciortea.Smart.Recipes.Util.DTO.User;

import com.ruxiciortea.Smart.Recipes.Model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDTO {

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @Email
    private String email;

    @NotBlank
    private String password;

    private Role role;

}
