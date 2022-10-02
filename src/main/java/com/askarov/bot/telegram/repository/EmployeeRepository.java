package com.askarov.bot.telegram.repository;

import com.askarov.bot.telegram.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {

    void deleteByChatId(Long chatId);

    Employee getByChatId(Long chatId);

    List<Employee> getAllByEmployeeLastName(String employeeLastName);

    List<Employee> getAllByEmployeeLastNameAndEmployeeFirstNameAndEmployeePatronymic(String employeeLastName,
                                                                                     String employeeFirstName,
                                                                                     String employeePatronymic);
}
