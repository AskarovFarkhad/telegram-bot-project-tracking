package com.askarov.bot.telegram.repository;

import com.askarov.bot.telegram.entity.Employee;
import com.askarov.bot.telegram.entity.Project;
import com.askarov.bot.telegram.entity.ProjectRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ProjectRegistrationRepository
        extends JpaRepository<ProjectRegistration, Long> {

    ProjectRegistration getByEmployee(Employee employee);

    ProjectRegistration getByProject(Project project);
}
