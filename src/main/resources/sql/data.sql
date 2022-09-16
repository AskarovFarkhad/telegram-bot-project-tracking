INSERT INTO employees(employee_chat_id,
                      employee_first_name,
                      employee_last_name,
                      employee_patronymic,
                      employee_post,
                      employee_status)
VALUES (1, 'Сотрудник1', 'Сотрудник1', 'Сотрудник1', 'Сотрудник1', 'Офис'),
       (2, 'Сотрудник2', 'Сотрудник2', 'Сотрудник2', 'Сотрудник2', 'Отпуск');

INSERT INTO projects(project_number, project_name, project_status)
VALUES ('1221', 'Проект1', 'В работе'),
       ('1331', 'Проект2', 'Тестирование');

INSERT INTO projects_registration(employee_id, project_id, start_date)
VALUES (1, 1, '10.09.2022'),
       (1, 2, '16.09.2022'),
       (2, 2, '15.12.2022');