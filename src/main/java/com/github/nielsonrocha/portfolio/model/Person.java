package com.github.nielsonrocha.portfolio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "pessoa")
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ColumnDefault("nextval('pessoa_id_seq'")
  @Column(name = "id", nullable = false)
  private Long id;

  @Size(max = 100)
  @NotNull
  @Column(name = "nome", nullable = false, length = 100)
  private String name;

  @Column(name = "datanascimento")
  private LocalDate birthDate;

  @Size(max = 14)
  @Column(name = "cpf", length = 14)
  private String cpf;

  @Column(name = "funcionario")
  private Boolean employee;

  @Column(name = "gerente")
  private Boolean manager;
}
