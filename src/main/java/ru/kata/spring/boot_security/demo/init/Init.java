package ru.kata.spring.boot_security.demo.init;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.ripository.RoleRepository;
import ru.kata.spring.boot_security.demo.ripository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class Init {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public Init(UserRepository userRepository,
                RoleRepository roleRepository,
                PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {


        Role userRole = roleRepository.findByRole("ROLE_USER")
                .orElseGet(() -> roleRepository.save(new Role("ROLE_USER")));

        Role adminRole = roleRepository.findByRole("ROLE_ADMIN")
                .orElseGet(() -> roleRepository.save(new Role("ROLE_ADMIN")));


        if (userRepository.findByUsername("user").isEmpty()) {
            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user"));
            user.setRoles(Set.of(userRole));

            userRepository.save(user);
        }


        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRoles(Set.of(userRole, adminRole));

            userRepository.save(admin);
        }
    }
}
