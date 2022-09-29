package com.askarov.bot.telegram.repository;

import com.askarov.bot.telegram.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ProjectRepository
        extends JpaRepository<Project, Long> {

    int deleteByProjectNumber(String projectNumber);

    Project getByProjectNumber(String projectNumber);
}
