package com.askarov.bot.telegram.entity;

import com.askarov.bot.telegram.enums.EmployeeStatus;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_chat_id")
    private Long chatId;

    @Column(name = "employee_first_name")
    private String employeeFirstName;

    @Column(name = "employee_last_name")
    private String employeeLastName;

    @Column(name = "employee_patronymic")
    private String employeePatronymic;

    @Column(name = "employee_post")
    private String employeePost;

    @Column(name = "employee_status")
    private String employeeStatus = EmployeeStatus.WORK.getStatusEmployee();

    @OneToMany(mappedBy = "employee", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<ProjectRegistration> projectRegistrations = new HashSet<>();

    @Override
    public String toString() {
        return "\n" + getId() + "\n" + "<b>Данные:</b> " +
                getEmployeeLastName() + (" ") +
                getEmployeeFirstName() + " " +
                getEmployeePatronymic() + "\n" +
                "<b>Должность:</b> " + getEmployeePost() + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Employee employee = (Employee) o;
        return id != null && Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
