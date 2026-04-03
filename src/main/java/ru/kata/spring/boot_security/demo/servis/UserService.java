package ru.kata.spring.boot_security.demo.servis;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;


public interface UserService extends UserDetailsService {

    List<User> findAll();

    User findById(Long id);

    User createUser(User user);

    User getInfo();

    void save(User user);

    void updateUser(Long id, User user);

    void delete(Long id);
}
