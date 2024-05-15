package com.github.nielsonrocha.portfolio.service;

import com.github.nielsonrocha.portfolio.model.Project;
import com.github.nielsonrocha.portfolio.model.ProjectStatus;
import com.github.nielsonrocha.portfolio.model.dto.ProjectDto;
import com.github.nielsonrocha.portfolio.model.mapper.ProjectMapper;
import com.github.nielsonrocha.portfolio.repository.ProjectRepository;
import com.github.nielsonrocha.portfolio.util.BusinessException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectService {

  private final ProjectRepository repository;
  private final ProjectMapper mapper;

  public List<ProjectDto> getAllProjects() {
    return repository.findOrderByName().stream().map(this::mapperProject).toList();
  }

  public ProjectDto mapperProject(Project project) {
    var mappedProject = mapper.toProjectDto(project);
    mappedProject.setCanDelete(canBeDeleted(mappedProject));
    return mappedProject;
  }

  public void saveProject(ProjectDto dto) {
    repository.save(mapper.toProject(dto));
  }

  private boolean canBeDeleted(ProjectDto project) {
    return project.getStatus() != ProjectStatus.STARTED
        && project.getStatus() != ProjectStatus.IN_PROGRESS
        && project.getStatus() != ProjectStatus.CLOSED;
  }

  public ProjectDto getProjectById(Long id) {
    return repository.findProjectById(id)
        .map(this::mapperProject)
        .orElse(null);
  }

  public void deleteProject(Long id) {
    var project = getProjectById(id);
    if (project != null && project.isCanDelete()) {
      repository.deleteById(id);
      return;
    }
    throw new BusinessException("Projeto não pode ser excluído");
  }

}
