package com.springapp.mvc;

/**
 * Created by dmitry on 05.10.15.
 */
public enum MessageStatus {

    ERROR(0),
    SUCCESS(1);

    private final int value;

    MessageStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
