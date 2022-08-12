CREATE
DATABASE IF NOT EXISTS ProjectTracking_db
COLLATE utf8mb4_unicode_ci;

USE
ProjectTracking_db;

DROP TABLE IF EXISTS projects;

CREATE TABLE projects
(
    project_id    INT PRIMARY KEY AUTO_INCREMENT,
    projectNumber VARCHAR(20) NOT NULL,
    projectName   VARCHAR(20) NOT NULL,
    projectStatus VARCHAR(10) NULL
);

DROP TABLE IF EXISTS employees;

CREATE TABLE employees
(
    employee_id        INT PRIMARY KEY AUTO_INCREMENT,
    employeeChatId     BIGINT(20) NOT NULL,
    employeeFirstName  VARCHAR(20) NOT NULL,
    employeeLastName   VARCHAR(20) NOT NULL,
    employeePatronymic VARCHAR(20) NOT NULL,
    employeePost       VARCHAR(20) NOT NULL,
    employeeStatus     VARCHAR(10) NULL,
    project_id         int NULL,
    FOREIGN KEY (project_id) REFERENCES projects (project_id)
);