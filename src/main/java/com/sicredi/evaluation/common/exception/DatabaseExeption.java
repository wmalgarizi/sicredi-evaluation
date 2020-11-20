package com.sicredi.evaluation.common.exception;

import lombok.AllArgsConstructor;

/**
 * Exception class to be used when gets occurs and error
 * while querying database using Spring Data Jpa
 */
@AllArgsConstructor
public class DatabaseExeption extends BaseException {
    private static final String TITLE = "Internal server error";

    private final String message;

    @Override
    String title() {
        return TITLE;
    }

    @Override
    String message() {
        return this.message;
    }
}
