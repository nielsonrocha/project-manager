package com.github.nielsonrocha.portfolio.repository;

import com.github.nielsonrocha.portfolio.model.Member;
import com.github.nielsonrocha.portfolio.model.MemberId;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, MemberId> {

  @Query(
      "select m from Member m join fetch m.person person join fetch m.project p where p.id = :id")
  List<Member> findMembersByProjectId(Long id);

  @Query(
      "select m from Member m join fetch m.person person join fetch m.project p where p.id = :projectId and person.id = :personId")
  Optional<Member> findByPersonIdAndProjectId(Long projectId, Long personId);
}
