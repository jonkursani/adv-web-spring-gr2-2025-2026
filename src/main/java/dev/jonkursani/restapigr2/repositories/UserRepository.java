package dev.jonkursani.restapigr2.repositories;

import dev.jonkursani.restapigr2.entities.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {
    Optional<User> findByEmail(String email);
}