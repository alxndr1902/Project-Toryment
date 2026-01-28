package com.tokopakde.toryment.exceptiohandler.exception;

public class OptimisticLockException extends RuntimeException{
    public OptimisticLockException(String message) {
        super(message);
    }
}
