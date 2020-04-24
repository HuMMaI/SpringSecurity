package org.lgs.lviv.homework.services;

import org.lgs.lviv.homework.domain.User;
import org.lgs.lviv.homework.domain.UserRole;
import org.lgs.lviv.homework.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private static final Set<UserRole> DEFAULT_USER_ROLES = Collections.singleton(UserRole.ROLE_USER);

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(User user){
        user.setRoles(DEFAULT_USER_ROLES);

        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);

        userRepository.save(user);
    }

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
