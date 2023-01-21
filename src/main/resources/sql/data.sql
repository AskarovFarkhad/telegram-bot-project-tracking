INSERT INTO employees(employee_chat_id,
                      employee_first_name,
                      employee_last_name,
                      employee_patronymic,
                      employee_post,
                      employee_status)
VALUES (1, 'ИмяСотрудника1', 'ФамилияСотрудника1', 'ОтчествоСотрудника1', 'Developer', 'Офис'),
       (2, 'ИмяСотрудника1', 'ФамилияСотрудника2', 'ОтчествоСотрудника2', 'QA', 'Офис');

INSERT INTO projects(project_number, project_name, project_status)
VALUES ('1491.21', 'Проект1', 'Тестирование'),
       ('2561.22', 'Проект2', 'В работе');

INSERT INTO projects_registration(employee_id, project_id, start_date)
VALUES (1, 1, '10.09.2022'),
       (1, 2, '16.09.2022'),
       (2, 1, '15.12.2022');
