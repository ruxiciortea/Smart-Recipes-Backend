package com.ruxiciortea.Smart.Recipes.Repository;

import java.util.Optional;

import com.ruxiciortea.Smart.Recipes.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

}
