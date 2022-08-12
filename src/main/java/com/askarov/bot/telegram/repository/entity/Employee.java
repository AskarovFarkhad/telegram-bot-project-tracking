package com.askarov.bot.telegram.repository.entity;

import com.askarov.bot.telegram.services.enums.StatusEmployee;
import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

    @Column
    private Long employeeChatId;

    @Column
    private String employeeFirstName;

    @Column
    private String employeeLastName;

    @Column
    private String employeePatronymic;

    @Column
    private String employeePost;

    @Column
    private StatusEmployee employeeStatus = StatusEmployee.WORK;

    public Employee(Long employeeChatId,
                    String employeeFirstName,
                    String employeeLastName,
                    String employeePatronymic,
                    String employeePost) {
        this.employeeChatId = employeeChatId;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.employeePatronymic = employeePatronymic;
        this.employeePost = employeePost;
    }
}
