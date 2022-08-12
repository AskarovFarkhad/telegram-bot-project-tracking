package com.askarov.bot.telegram.repository;

import com.askarov.bot.telegram.repository.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
