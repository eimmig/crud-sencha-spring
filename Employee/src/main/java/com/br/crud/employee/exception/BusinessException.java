package com.br.crud.employee.exception;

public class BusinessException extends  RuntimeException {
    public  BusinessException(String message) {
        super(message);
    }
}
