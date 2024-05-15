package com.github.nielsonrocha.portfolio.model.mapper;

import com.github.nielsonrocha.portfolio.model.Project;
import com.github.nielsonrocha.portfolio.model.ProjectRisk;
import com.github.nielsonrocha.portfolio.model.ProjectStatus;
import com.github.nielsonrocha.portfolio.model.dto.ProjectDto;
import com.github.nielsonrocha.portfolio.util.EnumUtils;
import io.micrometer.common.util.StringUtils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectMapper {

  @Mapping(target = "managerName", source = "manager.name")
  @Mapping(target = "managerId", source = "manager.id")
  @Mapping(target = "startDate", dateFormat = "dd/MM/yyyy")
  @Mapping(target = "endDate", dateFormat = "dd/MM/yyyy")
  @Mapping(target = "expectedEndDate", dateFormat = "dd/MM/yyyy")
  @Mapping(target = "status", source = "status", qualifiedByName = "convertStatus")
  @Mapping(target = "risk", source = "risk", qualifiedByName = "convertRisk")
  ProjectDto toProjectDto(Project project);

  @Mapping(target = "manager.id", source = "managerId")
  @Mapping(target = "startDate", dateFormat = "dd/MM/yyyy")
  @Mapping(target = "expectedEndDate", dateFormat = "dd/MM/yyyy")
  @Mapping(target = "endDate", qualifiedByName = "convertDate")
  @Mapping(target = "status", source = "status", qualifiedByName = "fromStatus")
  @Mapping(target = "risk", source = "risk", qualifiedByName = "fromRisk")
  Project toProject(ProjectDto dto);

  @Named("convertStatus")
  default ProjectStatus convertStatus(String status) {
    EnumUtils<ProjectStatus> enumUtils = new EnumUtils<>();
    return enumUtils.fromString(ProjectStatus.class, status, "getDescription");
  }

  @Named("convertRisk")
  default ProjectRisk convertRisk(String status) {
    EnumUtils<ProjectRisk> enumUtils = new EnumUtils<>();
    return enumUtils.fromString(ProjectRisk.class, status, "getDescription");
  }

  @Named("fromStatus")
  default String fromStatus(ProjectStatus status) {
    if (status != null) {
      return status.getDescription();
    }
    return null;
  }

  @Named("fromRisk")
  default String fromRisk(ProjectRisk risk) {
    if (risk != null) {
      return risk.getDescription();
    }
    return null;
  }

  @Named("convertDate")
  default LocalDate convertDate(String date) {
    if (StringUtils.isNotEmpty(date)) {
      return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    return null;
  }

}
