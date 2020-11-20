package com.sicredi.evaluation.common.exception;

/**
 * Exception class to be used when gets timeout from connection with
 * the Service from Receita
 */
public class ConnectionTimeoutException extends BaseException {
    @Override
    String title() {
        return "Connection with the service of Receita has timed out";
    }

    @Override
    String message() {
        return "No response from Receita Service in able time";
    }

}
