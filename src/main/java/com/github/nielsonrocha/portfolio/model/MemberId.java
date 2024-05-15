package com.github.nielsonrocha.portfolio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import java.io.Serial;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class MemberId implements java.io.Serializable {
  @Serial
  private static final long serialVersionUID = 6844749858771031807L;

  @NotNull
  @Column(name = "idprojeto", nullable = false)
  private Long projectId;

  @NotNull
  @Column(name = "idpessoa", nullable = false)
  private Long personId;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    MemberId memberId = (MemberId) o;
    return Objects.equals(projectId, memberId.projectId) &&
        Objects.equals(personId, memberId.personId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(projectId, personId);
  }
}
