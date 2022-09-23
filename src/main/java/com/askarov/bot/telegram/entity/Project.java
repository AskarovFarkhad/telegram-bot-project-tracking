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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_number", nullable = false)
    private String projectNumber;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "project_status")
    private String statusProject;

    @OneToMany(mappedBy = "project")
    private Set<ProjectRegistration> projectRegistrations;

}

