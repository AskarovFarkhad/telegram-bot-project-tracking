/** Сотрудники **/
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

/** Проекты **/
DROP TABLE IF EXISTS projects;

CREATE TABLE projects
(
    id             SERIAL PRIMARY KEY,
    project_number VARCHAR(20) NOT NULL,
    project_name   VARCHAR(20) NOT NULL,
    project_status VARCHAR(10) NULL
);

/** Соотношение ManyToMany **/
DROP TABLE IF EXISTS projects_registration;

CREATE TABLE projects_registration
(
    id          SERIAL PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    project_id  BIGINT NOT NULL,
    start_data  DATE   NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employees (id),
    FOREIGN KEY (project_id) REFERENCES projects (id)
);
