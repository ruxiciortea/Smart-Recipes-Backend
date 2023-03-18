package com.ruxiciortea.Smart.Recipes.Service;

import com.ruxiciortea.Smart.Recipes.Config.JwtService;
import com.ruxiciortea.Smart.Recipes.Model.User;
import com.ruxiciortea.Smart.Recipes.Repository.UserRepository;
import com.ruxiciortea.Smart.Recipes.Util.DTO.UserDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserDTO getUser(String auth) throws Exception {
        String userEmail = jwtService.extractUsername(auth);
        Optional<User> user =  userRepository.findByEmail(userEmail);

        if (user.isPresent()) {
            return modelMapper.map(user.get(), UserDTO.class);
        }

        throw new UsernameNotFoundException("Could not find user in database.");
    }

}
