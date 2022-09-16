package com.askarov.bot.telegram.entity;

import com.askarov.bot.telegram.enums.StatusProject;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "projects")
public class Project {

    @Id
    @Column(name = "id")
    private Integer projectId;

    @Column(name = "project_number", nullable = false)
    private String projectNumber;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "project_status")
    private StatusProject statusProject;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "projects")
    private Set<Employee> employees;
}

