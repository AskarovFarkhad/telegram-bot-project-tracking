package com.askarov.bot.telegram.services.handler.text;

public class TextHandler {

    public static String projectNameToString(String[] text) {
        StringBuilder projectName = new StringBuilder();
        for (int i = 1; i < text.length; i++) {
            projectName.append(text[i]).append(" ");
        }
        return projectName.toString();
    }

    public static String employeePostToString(String[] text) {
        StringBuilder projectName = new StringBuilder();
        for (int i = 3; i < text.length; i++) {
            projectName.append(text[i]).append(" ");
        }
        return projectName.toString();
    }
}
