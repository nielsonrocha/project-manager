package com.github.nielsonrocha.portfolio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "projeto")
public class Project {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ColumnDefault("nextval('projeto_id_seq'")
  @Column(name = "id", nullable = false)
  private Long id;

  @Size(max = 200)
  @NotNull
  @Column(name = "nome", nullable = false, length = 200)
  private String name;

  @Column(name = "data_inicio")
  private LocalDate startDate;

  @Column(name = "data_previsao_fim")
  private LocalDate expectedEndDate;

  @Column(name = "data_fim")
  private LocalDate endDate;

  @Size(max = 5000)
  @Column(name = "descricao", length = 5000)
  private String description;

  @Column(name = "status", length = 45)
  private String status;

  @Column(name = "orcamento")
  private BigDecimal budget;

  @Size(max = 45)
  @Column(name = "risco", length = 45)
  private String risk;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "idgerente", nullable = false)
  private Person manager;

}
