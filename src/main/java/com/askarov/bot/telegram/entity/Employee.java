package com.askarov.bot.telegram.entity;

import com.askarov.bot.telegram.enums.StatusEmployee;
import lombok.*;

import javax.persistence.*;
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
    @Column(name = "id")
    private Long employeeId;

    @Column(name = "employee_chat_id", nullable = false)
    private Long employeeChatId;

    @Column(name = "employee_first_name", nullable = false)
    private String employeeFirstName;

    @Column(name = "employee_last_name")
    private String employeeLastName;

    @Column(name = "employee_patronymic")
    private String employeePatronymic;

    @Column(name = "employee_post", nullable = false)
    private String employeePost;

    @Column(name = "employee_status")
    private StatusEmployee employeeStatus = StatusEmployee.WORK;

    @ManyToMany
    @JoinTable(name = "employees_projects",
            joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"))
    private Set<Project> projects;
}
