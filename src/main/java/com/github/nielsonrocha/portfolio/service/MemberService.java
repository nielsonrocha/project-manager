package com.github.nielsonrocha.portfolio.service;

import com.github.nielsonrocha.portfolio.model.Member;
import com.github.nielsonrocha.portfolio.model.MemberId;
import com.github.nielsonrocha.portfolio.repository.MemberRepository;
import com.github.nielsonrocha.portfolio.repository.PersonRepository;
import com.github.nielsonrocha.portfolio.repository.ProjectRepository;
import com.github.nielsonrocha.portfolio.util.BusinessException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository repository;
  private final ProjectRepository projectRepository;
  private final PersonRepository personRepository;

  public List<Member> getMembersByProjectId(Long id) {
    return repository.findMembersByProjectId(id);
  }

  public void addMemberToProject(Long projectId, Long personId) {
    var member = repository.findByPersonIdAndProjectId(projectId, personId);
    if (member.isPresent()) {
      throw new BusinessException("Membro já está participando do projeto");
    }


    var project = projectRepository.findById(projectId).orElseThrow(() -> new BusinessException("Projeto não encontrado"));
    var person = personRepository.findById(personId).orElseThrow(() -> new BusinessException("Pessoa não encontrada"));

    var newMember = new Member();
    newMember.setId(new MemberId(projectId, personId));
    newMember.setProject(project);
    newMember.setPerson(person);

    repository.save(newMember);
  }
}
