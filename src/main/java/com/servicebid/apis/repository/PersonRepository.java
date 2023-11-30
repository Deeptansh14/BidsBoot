package com.servicebid.apis.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.servicebid.apis.models.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByIsenabled(boolean isEnabled);
    List<Person> findByEmailid(String emailid);
    List<Person> findByPersonnameContaining(String namefragment);
}
