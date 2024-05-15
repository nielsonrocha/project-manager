package com.github.nielsonrocha.portfolio.service;

import com.github.nielsonrocha.portfolio.model.Project;
import com.github.nielsonrocha.portfolio.model.ProjectStatus;
import com.github.nielsonrocha.portfolio.model.dto.ProjectDto;
import com.github.nielsonrocha.portfolio.model.mapper.ProjectMapper;
import com.github.nielsonrocha.portfolio.repository.ProjectRepository;
import com.github.nielsonrocha.portfolio.util.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class ProjectServiceTest {

  @Autowired
  private ProjectService projectService;

  @MockBean
  private ProjectRepository projectRepository;

  @MockBean
  private ProjectMapper projectMapper;


  @Test
  void saveProject_whenCalled_shouldSaveProject() {
    ProjectDto projectDto = new ProjectDto();
    Project project = new Project();
    when(projectMapper.toProject(any(ProjectDto.class))).thenReturn(project);
    when(projectRepository.save(any(Project.class))).thenReturn(project);

    projectService.saveProject(projectDto);

    verify(projectRepository, times(1)).save(any(Project.class));
  }

  @Test
  void getProjectById_whenProjectExists_shouldReturnProject() {
    ProjectDto projectDto = new ProjectDto();
    when(projectRepository.findProjectById(anyLong())).thenReturn(Optional.of(new Project()));
    when(projectMapper.toProjectDto(any(Project.class))).thenReturn(projectDto);

    projectService.getProjectById(1L);

    verify(projectRepository, times(1)).findProjectById(anyLong());
  }

  @Test
  void getProjectById_whenProjectDoesNotExist_shouldReturnNull() {
    when(projectRepository.findProjectById(anyLong())).thenReturn(Optional.empty());

    var result = projectService.getProjectById(1L);

    verify(projectRepository, times(1)).findProjectById(anyLong());
    assertNull(result);
  }

  @Test
  void deleteProject_whenProjectCanBeDeleted_shouldDeleteProject() {
    ProjectDto projectDto = new ProjectDto();
    projectDto.setCanDelete(true);
    when(projectRepository.findProjectById(anyLong())).thenReturn(Optional.of(new Project()));
    when(projectMapper.toProjectDto(any(Project.class))).thenReturn(projectDto);

    projectService.deleteProject(1L);

    verify(projectRepository, times(1)).deleteById(anyLong());
  }

}
