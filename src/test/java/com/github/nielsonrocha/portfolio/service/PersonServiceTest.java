package com.github.nielsonrocha.portfolio.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.github.nielsonrocha.portfolio.model.Person;
import com.github.nielsonrocha.portfolio.model.dto.PersonDto;
import com.github.nielsonrocha.portfolio.model.mapper.PersonMapper;
import com.github.nielsonrocha.portfolio.repository.PersonRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class PersonServiceTest {

  @Autowired
  PersonService personService;

  @MockBean
  PersonMapper personMapper;

  @MockBean
  PersonRepository personRepository;


  @Test
  void createPerson_shouldReturnCreatedPerson() {
    PersonDto personDto = new PersonDto();
    Person person = new Person();
    when(personMapper.toPerson(any(PersonDto.class))).thenReturn(person);
    when(personRepository.save(any(Person.class))).thenReturn(person);
    when(personMapper.toPersonDto(any(Person.class))).thenReturn(personDto);

    personService.createPerson(personDto);

    verify(personRepository, times(1)).save(any(Person.class));
  }

  @Test
  void getPerson_whenPersonExists_shouldReturnPerson() {
    PersonDto personDto = new PersonDto();
    when(personRepository.findById(anyLong())).thenReturn(Optional.of(new Person()));
    when(personMapper.toPersonDto(any(Person.class))).thenReturn(personDto);

    personService.getPerson(1L);

    verify(personRepository, times(1)).findById(anyLong());
  }

  @Test
  void updatePerson_whenPersonExists_shouldReturnUpdatedPerson() {
    PersonDto personDto = new PersonDto();
    Person person = new Person();
    when(personRepository.findById(anyLong())).thenReturn(Optional.of(person));
    when(personMapper.toPerson(any(PersonDto.class))).thenReturn(person);
    when(personRepository.save(any(Person.class))).thenReturn(person);
    when(personMapper.toPersonDto(any(Person.class))).thenReturn(personDto);

    personService.updatePerson(1L, personDto);

    verify(personRepository, times(1)).save(any(Person.class));
  }

  @Test
  void updatePerson_whenPersonDoesNotExist_shouldThrowException() {
    PersonDto personDto = new PersonDto();
    when(personRepository.findById(anyLong())).thenReturn(Optional.empty());

    assertThrows(RuntimeException.class, () -> personService.updatePerson(1L, personDto));
  }

  @Test
  void deletePerson_whenCalled_shouldDeletePerson() {
    personService.deletePerson(1L);

    verify(personRepository, times(1)).deleteById(anyLong());
  }


  @Test
  void getManagers_whenCalled_shouldReturnManagers() {
    personService.getManagers();

    verify(personRepository, times(1)).findByManagerIsTrueOrderByName();
  }

  @Test
  void getEmployees_whenCalled_shouldReturnEmployees() {
    personService.getEmployees();

    verify(personRepository, times(1)).findByManagerIsFalseOrderByName();
  }
}
