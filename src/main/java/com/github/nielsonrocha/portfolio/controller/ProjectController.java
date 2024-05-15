package com.github.nielsonrocha.portfolio.controller;

import com.github.nielsonrocha.portfolio.model.ProjectRisk;
import com.github.nielsonrocha.portfolio.model.ProjectStatus;
import com.github.nielsonrocha.portfolio.model.dto.PersonDto;
import com.github.nielsonrocha.portfolio.model.dto.ProjectDto;
import com.github.nielsonrocha.portfolio.service.MemberService;
import com.github.nielsonrocha.portfolio.service.PersonService;
import com.github.nielsonrocha.portfolio.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ProjectController {

  private final PersonService personService;
  private final ProjectService projectService;
  private final MemberService memberService;

  @GetMapping("/list-projects")
  public String getListProjects(ModelMap model){
    model.addAttribute("projectsList", projectService.getAllProjects());
    return "list-projects";
  }

  @GetMapping("/edit-project")
  public String showEditProject(@RequestParam("id") Long id, ModelMap model){
    var projectDto = projectService.getProjectById(id);
    if (projectDto == null) {
      return "redirect:list-projects";
    }
    model.addAttribute("riskList", ProjectRisk.getListValues());
    model.addAttribute("statusList", ProjectStatus.getListValues());
    model.addAttribute("managerList", personService.getManagers());
    model.addAttribute("projectDto", projectDto);
    return "project";
  }

  @GetMapping("/add-project")
  public String showInsertProject(ModelMap model) {
    model.addAttribute("projectDto", new ProjectDto());
    model.addAttribute("riskList", ProjectRisk.getListValues());
    model.addAttribute("statusList", ProjectStatus.getListValues());
    model.addAttribute("managerList", personService.getManagers());
    return "project";
  }

  @PostMapping("/add-project")
  public String insertProject(@Valid ProjectDto projectDto, BindingResult result) {
    if (result.hasErrors()) {
      return "project";
    }
    projectService.saveProject(projectDto);
    return "redirect:list-projects";
  }

  @GetMapping("/delete-project")
  public String deleteProject(@RequestParam Long id) {
    projectService.deleteProject(id);
    return "redirect:/list-projects";
  }

  @PostMapping("/edit-project")
  public String editProject(@Valid ProjectDto projectDto, BindingResult result) {
    if (result.hasErrors()) {
      return "project";
    }
    projectService.saveProject(projectDto);
    return "redirect:list-projects";
  }

  @GetMapping("/project-members")
  public String getListProjects(@RequestParam("projectId") Long projectId, ModelMap model){
    var project = projectService.getProjectById(projectId);
    if (project == null) {
      return "redirect:list-projects";
    }
    model.addAttribute("personList", personService.getEmployees());
    model.addAttribute("projectDto", project);
    model.addAttribute("personDto", new PersonDto());
    model.addAttribute("membersList", memberService.getMembersByProjectId(projectId));
    return "members";
  }

  @PostMapping("/project-members")
  public String addMemberProject(@RequestParam("projectId") Long projectId, @Valid PersonDto personDto, BindingResult result) {
    if (result.hasErrors()) {
      return "project-members?projectId=" + projectId;
    }
    memberService.addMemberToProject(projectId, personDto.getId());
    return "redirect:project-members?projectId=" + projectId;

  }
}
