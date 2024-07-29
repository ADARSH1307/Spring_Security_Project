package com.ust.Ust_Ptojects.repo;

import com.ust.Ust_Ptojects.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Integer> {
}
