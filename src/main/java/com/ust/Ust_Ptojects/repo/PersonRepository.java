package com.ust.Ust_Ptojects.repo;

import com.ust.Ust_Ptojects.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository  extends JpaRepository<Person,Integer> {
    Optional<Person> findByUsername(String username);
}
