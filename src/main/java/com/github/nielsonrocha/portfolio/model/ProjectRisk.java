package com.github.nielsonrocha.portfolio.model;

import com.github.nielsonrocha.portfolio.util.EnumUtils;
import java.util.List;

public enum ProjectRisk {

  HIGH("Alto Risco"), MEDIUM("Médio Risco"), LOW("Baixo Risco");

  private final String description;

  ProjectRisk(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public static List<ProjectRisk> getListValues() {
    EnumUtils<ProjectRisk> enumUtils = new EnumUtils<>();
    return enumUtils.transformEnumList(ProjectRisk.class);
  }
}
