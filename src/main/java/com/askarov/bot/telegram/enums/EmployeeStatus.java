package com.askarov.bot.telegram.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EmployeeStatus {

    WORK("Офис"),
    VACATION("Отпуск"),
    SICK_LEAVE("Больничный"),
    FIRED("Уволен"),
    TRIP("Командировка");

    @Getter
    private final String statusEmployee;
}
