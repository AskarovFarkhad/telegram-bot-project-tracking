package com.askarov.bot.telegram.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static com.askarov.bot.telegram.enums.ProjectStatus.DEVELOP;

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
    private String statusProject = DEVELOP.getStatusProject();

    @OneToMany(mappedBy = "project", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<ProjectRegistration> projectRegistrations = new HashSet<>();

    @Override
    public String toString() {
        return getProjectNumber() + " --- " + getProjectName() + "\n";
    }
}

