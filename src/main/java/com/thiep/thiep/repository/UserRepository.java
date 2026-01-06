package com.thiep.thiep.repository;

import com.thiep.thiep.entity.User;
import com.thiep.thiep.model.AuthProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByProviderAndProviderUserId(AuthProvider provider, String providerUserId);
}
