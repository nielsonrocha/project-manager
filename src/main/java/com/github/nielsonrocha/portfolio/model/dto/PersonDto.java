package com.github.nielsonrocha.portfolio.model.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

  @NotNull(message = "O ID é obrigatório")
  private Long id;
  private String name;
  private Boolean manager;
  private Boolean employee;
  private LocalDate birthDate;

}
