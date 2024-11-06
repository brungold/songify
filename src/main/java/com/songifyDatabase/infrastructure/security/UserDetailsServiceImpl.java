package com.songifyDatabase.infrastructure.security;

import com.songifyDatabase.domain.usercrud.User;
import com.songifyDatabase.domain.usercrud.UserConformer;
import com.songifyDatabase.domain.usercrud.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.List;

@Log4j2
@AllArgsConstructor
class UserDetailsServiceImpl implements UserDetailsManager {

    public static final String DEFAULT_USER_ROLE = "ROLE_USER";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserConformer userConformer;

    @Override
    public void createUser(UserDetails user) {
        if (userExists(user.getUsername())){
            log.warn("not saved user - already exists");
            throw new RuntimeException("not saved user - already exists");
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        String confirmationToken = UUID.randomUUID().toString();
        User createdUser = new User(
                user.getUsername(),
                encodedPassword,
                confirmationToken,
                List.of(DEFAULT_USER_ROLE)
//                user.getAuthorities()
//                        .stream()
//                        .map(GrantedAuthority::getAuthority)
//                        .collect(Collectors.toList())
        );
        User savedUser = userRepository.save(createdUser);
        log.info("Saved user with Id: " + savedUser.getId());
        userConformer.sendConfirmationEmail(createdUser);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return userRepository.existsByEmail(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findFirstByEmail(username)
                .map(SecurityUser::new)
                .orElseThrow(() -> new RuntimeException("not fount user"));
    }
}
