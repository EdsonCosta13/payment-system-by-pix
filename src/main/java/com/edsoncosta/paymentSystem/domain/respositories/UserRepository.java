package com.edsoncosta.paymentSystem.domain.respositories;

import com.edsoncosta.paymentSystem.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);
    User findByName(String name);
    List<User> findAllByEnabledIsTrue();
}
