package com.askarov.bot.telegram.repository;

import com.askarov.bot.telegram.entity.Employee;
import com.askarov.bot.telegram.entity.Project;
import com.askarov.bot.telegram.entity.ProjectRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ProjectRegistrationRepository
        extends JpaRepository<ProjectRegistration, Long> {

    List<ProjectRegistration> getByEmployee(Employee employee);

    List<ProjectRegistration> getByProject(Project project);

    void deleteByProject(Project project);
}
