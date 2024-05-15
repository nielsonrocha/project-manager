package com.github.nielsonrocha.portfolio.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "membros")
@EqualsAndHashCode
public class Member {
  @EmbeddedId private MemberId id;

  @MapsId("projectId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "idprojeto", nullable = false)
  private Project project;

  @MapsId("personId")
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "idpessoa", nullable = false)
  private Person person;
}
