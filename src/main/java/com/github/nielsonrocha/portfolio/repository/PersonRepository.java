package com.github.nielsonrocha.portfolio.repository;

import com.github.nielsonrocha.portfolio.model.Person;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

  List<Person> findByManagerIsTrueOrderByName();

  List<Person> findByManagerIsFalseOrderByName();

}
