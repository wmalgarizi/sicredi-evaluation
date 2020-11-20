package com.sicredi.evaluation.common.exception;

import lombok.AllArgsConstructor;

/**
 * Exception class to be used when the Receita Service is down
 */
@AllArgsConstructor
public class ServiceUnavailableException extends BaseException {
    private static final String TITLE = "The Receita Service is unavailable";

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
