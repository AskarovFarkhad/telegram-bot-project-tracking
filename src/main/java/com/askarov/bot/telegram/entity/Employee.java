package com.askarov.bot.telegram.entity;

import com.askarov.bot.telegram.enums.EmployeeStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_chat_id", nullable = false)
    private Long chatId;

    @Column(name = "employee_first_name", nullable = false)
    private String employeeFirstName;

    @Column(name = "employee_last_name")
    private String employeeLastName;

    @Column(name = "employee_patronymic")
    private String employeePatronymic;

    @Column(name = "employee_post", nullable = false)
    private String employeePost;

    @Column(name = "employee_status")
    private String employeeStatus = EmployeeStatus.WORK.getStatusEmployee();

    @OneToMany(mappedBy = "employee", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<ProjectRegistration> projectRegistrations = new HashSet<>();

    @Override
    public String toString() {
        return getId() + "\n" + "<b>Данные:</b> " +
                getEmployeeLastName() + (" ") +
                getEmployeeFirstName() + " " +
                getEmployeePatronymic() + "\n" +
                "<b>Должность:</b> " + getEmployeePost() + "\n";
    }
}
