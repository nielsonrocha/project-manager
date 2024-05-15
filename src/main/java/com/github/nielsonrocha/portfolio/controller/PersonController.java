package com.github.nielsonrocha.portfolio.controller;

import com.github.nielsonrocha.portfolio.model.dto.PersonDto;
import com.github.nielsonrocha.portfolio.service.PersonService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/persons")
@RequiredArgsConstructor
public class PersonController {

  private final PersonService personService;

  @GetMapping("/")
  public ResponseEntity<List<PersonDto>> getPersons() {
    return ResponseEntity.ok(personService.getAll());
  }

  @PostMapping
  public ResponseEntity<PersonDto> createPerson(@RequestBody PersonDto personDto) {
    return new ResponseEntity<>(personService.createPerson(personDto), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PersonDto> getPerson(@PathVariable Long id) {
    return personService.getPerson(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<PersonDto> updatePerson(@PathVariable Long id, @RequestBody PersonDto personDto) {
    try {
      return new ResponseEntity<>(personService.updatePerson(id, personDto), HttpStatus.OK);
    } catch (RuntimeException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
    personService.deletePerson(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
