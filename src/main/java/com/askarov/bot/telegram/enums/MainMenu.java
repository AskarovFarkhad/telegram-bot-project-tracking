package com.askarov.bot.telegram.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Farkhad Askarov
 */
@RequiredArgsConstructor
public enum MainMenu {

    EMPLOYEE("\uD83D\uDC54 Сотрудник"),
    PROJECT("\uD83D\uDDC3 Проект"),
    SEARCH("\uD83D\uDD0D Поиск"),
    INFO("\uD83D\uDCC3 Информация");

    @Getter
    private final String menu;
}
