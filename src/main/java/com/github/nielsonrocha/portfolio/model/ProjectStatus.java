package com.github.nielsonrocha.portfolio.model;

import com.github.nielsonrocha.portfolio.util.EnumUtils;
import java.util.List;

public enum ProjectStatus {

  IN_ANALYSIS("Em Análise"),
  ANALYSIS_CARRIED_OUT("Análise Realizada"),
  APPROVED_ANALYSIS("Análise Aprovada"),
  STARTED("Iniciado"),
  PLANNED("Planejado"),
  IN_PROGRESS("Em Andamento"),
  CLOSED("Encerrado"),
  CANCELED("Cancelado");

  private final String description;

  ProjectStatus(String description) {
    this.description =description;
  }

  public String getDescription() {
    return description;
  }

  public static List<ProjectStatus> getListValues() {
    EnumUtils<ProjectStatus> enumUtils = new EnumUtils<>();
    return enumUtils.transformEnumList(ProjectStatus.class);
  }
}
