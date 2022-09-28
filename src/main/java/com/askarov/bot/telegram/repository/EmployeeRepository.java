package com.askarov.bot.telegram.repository;

import com.askarov.bot.telegram.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {

    int deleteByChatId(Long ChatId);
}
