package com.askarov.bot.telegram.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CallbackDataAndBotState {

    START(
            "Запуск бота",
            "/start"),

    EMPLOYEE_CREATE(
            "Добавить сотрудника",
            "/create"),

    EMPLOYEE_DELETE(
            "Удалить сотрудника",
            "/delete"),

    EMPLOYEE_UPDATE(
            "Обновить данные сотрудника",
            "/update"),

    PROJECT_ADD(
            "Добавить проект",
            "/project"),

    PROJECT_UPDATE(
            "Обновить название проекта",
            "/update_pr"),

    PROJECT_DELETE(
            "Удалить проект",
            "/delete_pr"),

    SEARCH_PROJECT(
            "Поиск по проекту",
            "/search_pr"),

    SEARCH_EMPLOYEE(
            "Поиск по сотруднику",
            "/search_emp"),

    SEARCH_ALL_EMPLOYEES(
            "Список всех сотрудников",
            "/list"),

    ADMIN(
            "Администратор",
            "/admin");

    @Getter
    private final String commandName;
    @Getter
    private final String syntax;
}
