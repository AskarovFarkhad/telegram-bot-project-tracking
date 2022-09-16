DROP TABLE IF EXISTS employees;

CREATE TABLE employees
(
    id                  SERIAL PRIMARY KEY,
    employee_chat_id    BIGINT      NOT NULL,
    employee_first_name VARCHAR(20) NOT NULL,
    employee_last_name  VARCHAR(20) NOT NULL,
    employee_patronymic VARCHAR(20) NOT NULL,
    employee_post       VARCHAR(20) NOT NULL,
    employee_status     VARCHAR(10) NULL
);

DROP TABLE IF EXISTS projects;

CREATE TABLE projects
(
    id             SERIAL PRIMARY KEY,
    project_number VARCHAR(20) NOT NULL,
    project_name   VARCHAR(20) NOT NULL,
    project_status VARCHAR(10) NULL
);

DROP TABLE IF EXISTS employees_projects;

CREATE TABLE employees_projects
(
    employee_id INTEGER NOT NULL,
    project_id   INTEGER NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employees (id),
    FOREIGN KEY (project_id) REFERENCES projects (id)
);
