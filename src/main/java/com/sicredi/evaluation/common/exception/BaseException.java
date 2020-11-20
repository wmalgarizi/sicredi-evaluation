package com.sicredi.evaluation.common.exception;

public abstract class BaseException extends RuntimeException {
    /**
     * Method which returns the Exception title
     *
     * @return the Exception title
     */
    abstract String title();

    /**
     * Method which returns the Exception message
     *
     * @return the description of the error
     */
    abstract String message();

}
