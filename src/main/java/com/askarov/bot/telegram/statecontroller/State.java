package com.askarov.bot.telegram.statecontroller;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum State {

    NO_EMPLOYEE_NO_PROJECT,
    YES_EMPLOYEE_NO_PROJECT,
    NO_EMPLOYEE_YES_PROJECT,
    YES_EMPLOYEE_YES_PROJECT

}
