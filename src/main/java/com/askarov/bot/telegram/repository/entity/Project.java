package com.askarov.bot.telegram.repository.entity;

import com.askarov.bot.telegram.services.enums.StatusProject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@NoArgsConstructor
//@Entity
//@Table(name = "projects")
public class Project {

//    @Id
//    @Column(name = "project_id")
    private Integer projectId;

//    @Column
    private String projectNumber;

//    @Column
    private String projectName;

//    @Column
    private StatusProject statusProject;

//    @Column(name = "project_id")
    private Integer project;

    public Project(String projectNumber, String projectName, StatusProject statusProject) {
        this.projectNumber = projectNumber;
        this.projectName = projectName;
        this.statusProject = statusProject;
    }
}

