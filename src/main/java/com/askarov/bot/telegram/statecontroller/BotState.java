package com.askarov.bot.telegram.statecontroller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
public enum BotState {

    START,
    EMPLOYEE_CREATE,
    EMPLOYEE_DELETE,
    EMPLOYEE_UPDATE,
    PROJECT_ADD,
    PROJECT_UPDATE,
    PROJECT_DELETE,
    SEARCH_PROJECT,
    SEARCH_EMPLOYEE,
    SEARCH_ALL_EMPLOYEES,
    INFO
}
