package com.github.nielsonrocha.portfolio.model.dto;

import com.github.nielsonrocha.portfolio.model.ProjectRisk;
import com.github.nielsonrocha.portfolio.model.ProjectStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {

  private Long id;
  @NotEmpty(message = "Nome do projeto deve ser innformado")
  private String name;
  private String managerName;
  private ProjectStatus status;
  private ProjectRisk risk;
  private String startDate;
  private String endDate;
  private boolean canDelete;
  private String expectedEndDate;
  private Long managerId;
  private List<Long> membersId;
  private BigDecimal budget;
  private String description;

}
