package com.github.nielsonrocha.portfolio.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.github.nielsonrocha.portfolio.model.Member;
import com.github.nielsonrocha.portfolio.model.Person;
import com.github.nielsonrocha.portfolio.model.Project;
import com.github.nielsonrocha.portfolio.repository.MemberRepository;
import com.github.nielsonrocha.portfolio.repository.PersonRepository;
import com.github.nielsonrocha.portfolio.repository.ProjectRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class MemberServiceTest {

  @Autowired
  MemberService memberService;

  @MockBean
  MemberRepository memberRepository;

  @MockBean
  ProjectRepository projectRepository;

  @MockBean
  PersonRepository personRepository;



  @Test
  void addMemberToProject_whenMemberDoesNotExist_shouldAddMember() {
    when(memberRepository.findByPersonIdAndProjectId(any(), any())).thenReturn(Optional.empty());
    when(memberRepository.save(any())).thenReturn(new Member());
    when(projectRepository.findById(any())).thenReturn(Optional.of(new Project()));
    when(personRepository.findById(any())).thenReturn(Optional.of(new Person()));

    memberService.addMemberToProject(1L, 1L);

    verify(memberRepository, times(1)).save(any());
  }

  @Test
  void addMemberToProject_whenMemberExists_shouldThrowException() {
    when(memberRepository.findByPersonIdAndProjectId(anyLong(), anyLong())).thenReturn(Optional.of(new Member()));

    assertThrows(RuntimeException.class, () -> memberService.addMemberToProject(1L, 1L));
  }

  @Test
  void getMembersByProjectId_whenCalled_shouldReturnMembers() {
    memberService.getMembersByProjectId(1L);

    verify(memberRepository, times(1)).findMembersByProjectId(anyLong());
  }
}
