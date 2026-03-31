package ru.kata.spring.boot_security.demo.servis;

import ru.kata.spring.boot_security.demo.model.Person;

import java.util.List;

public interface PersonService {
    Person findByUsername(Long id);

    List<Person> findAll();

    Person add(Person person);

    void deleteById(Long id);
}
