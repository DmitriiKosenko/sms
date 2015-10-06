package com.springapp.mvc;

/**
 * Created by dmitry on 05.10.15.
 */
public enum MessageStatus {

    ERROR(0, "ошибка"),
    SUCCESS(1, "отправлено");

    private final int value;
    private final String message;

    MessageStatus(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public static MessageStatus getByBoolean(boolean flag) {
        if (flag) {
            return SUCCESS;
        }

        return ERROR;
    }

    public static MessageStatus getByInteger(int value) {
        assert value == 0 || value == 1;

        if (value == 1) {
            return SUCCESS;
        }

        return ERROR;
    }

    public int getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}
