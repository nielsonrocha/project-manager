package com.github.nielsonrocha.portfolio.service;

import com.github.nielsonrocha.portfolio.model.dto.PersonDto;
import com.github.nielsonrocha.portfolio.model.mapper.PersonMapper;
import com.github.nielsonrocha.portfolio.repository.PersonRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {

  private final PersonMapper mapper;
  private final PersonRepository repository;

  public List<PersonDto> getManagers() {
    return repository.findByManagerIsTrueOrderByName().stream().map(mapper::toPersonDto).toList();
  }

  public List<PersonDto> getEmployees() {
    return repository.findByManagerIsFalseOrderByName().stream().map(mapper::toPersonDto).toList();
  }

  public PersonDto createPerson(PersonDto personDto) {
    var person = mapper.toPerson(personDto);
    var savedPerson = repository.save(person);
    return mapper.toPersonDto(savedPerson);
  }

  public Optional<PersonDto> getPerson(Long id) {
    return repository.findById(id).map(mapper::toPersonDto);
  }

  public PersonDto updatePerson(Long id, PersonDto personDto) {
    repository.findById(id).orElseThrow(() -> new RuntimeException("Pessoa não localizada"));
    var person = mapper.toPerson(personDto);
    var updatedPerson = repository.save(person);
    return mapper.toPersonDto(updatedPerson);
  }

  // Delete
  public void deletePerson(Long id) {
    repository.deleteById(id);
  }

  public List<PersonDto> getAll() {
    return repository.findAll(Sort.by(Sort.Direction.ASC, "name")).stream()
        .map(mapper::toPersonDto)
        .toList();
  }
}
