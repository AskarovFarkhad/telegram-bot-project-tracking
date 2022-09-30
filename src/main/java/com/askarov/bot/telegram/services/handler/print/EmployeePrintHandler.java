package com.askarov.bot.telegram.services.handler.print;

import com.askarov.bot.telegram.entity.Employee;
import com.askarov.bot.telegram.repository.ProjectRegistrationRepository;

import java.util.List;

public class EmployeePrintHandler {

    public static String printData(List<Employee> employeeList,
                                   ProjectRegistrationRepository projectRegistrationRepository) {
        StringBuilder reply = new StringBuilder();

        employeeList.forEach(employee -> {
                    reply.append(employee).append("<b>Проекты:</b> ").append("\n");
                    projectRegistrationRepository.getByEmployee(employee).forEach(projectRegistration ->
                            reply
                                    .append(projectRegistration.getRegisteredDate()).append(" --- ")
                                    .append(projectRegistration.getProject()));
                    reply.append("\n");
                }
        );
        return reply.toString();
    }
}
