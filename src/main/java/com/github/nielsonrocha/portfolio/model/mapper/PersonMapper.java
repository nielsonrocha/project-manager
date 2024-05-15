package com.github.nielsonrocha.portfolio.model.mapper;

import com.github.nielsonrocha.portfolio.model.Person;
import com.github.nielsonrocha.portfolio.model.dto.PersonDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PersonMapper {

  PersonDto toPersonDto(Person person);

  Person toPerson(PersonDto dto);
  
}
