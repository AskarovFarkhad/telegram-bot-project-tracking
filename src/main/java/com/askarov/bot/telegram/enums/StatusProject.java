package com.askarov.bot.telegram.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum StatusProject {

    CONTACT("Ознакомление"),
    DEVELOP("Разработка"),
    TEST("Тестирование"),
    START_UP_WORKS("ПНР"),
    SUPPORT("ТО"),
    CHECK("ЗПИ"),
    TRIP("Командировка");

    @Getter
    private final String statusProject;
}
