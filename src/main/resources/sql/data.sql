INSERT INTO employees(employee_chat_id,
                      employee_first_name,
                      employee_last_name,
                      employee_patronymic,
                      employee_post,
                      employee_status)
VALUES (1, 'Никита', 'Сидоров', 'Сергеевич', 'Developer', 'Офис'),
       (2, 'Михаил', 'Куликов', 'Андреевич', 'QA', 'Офис');

INSERT INTO projects(project_number, project_name, project_status)
VALUES ('1491.21', 'ПСП CW', 'Тестирование'),
       ('2561.22', 'ПО DeltaV', 'В работе');

INSERT INTO projects_registration(employee_id, project_id, start_date)
VALUES (1, 1, '10.09.2022'),
       (1, 2, '16.09.2022'),
       (2, 1, '15.12.2022');
