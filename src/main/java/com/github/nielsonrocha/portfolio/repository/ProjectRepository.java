package com.github.nielsonrocha.portfolio.repository;

import com.github.nielsonrocha.portfolio.model.Project;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

  @Query("select p from Project p join fetch p.manager m order by p.name")
  List<Project> findOrderByName();

  @Query("select p from Project p join fetch p.manager m where p.id = :id")
  Optional<Project> findProjectById(Long id);
}
