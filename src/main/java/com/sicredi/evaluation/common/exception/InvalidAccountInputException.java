package com.sicredi.evaluation.common.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InvalidAccountInputException extends BaseException {
    private static final String TITLE = "Invalid account input data";

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
