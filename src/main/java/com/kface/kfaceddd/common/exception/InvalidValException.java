package com.kface.kfaceddd.common.exception;

public class InvalidValException extends BaseException{

    public InvalidValException(String errMessage) {
        super(errMessage);
    }

    public InvalidValException(String errCode, String errMessage) {
        super(errCode, errMessage);
    }

    public InvalidValException(String errMessage, Throwable e) {
        super(errMessage, e);
    }

    public InvalidValException(String errCode, String errMessage, Throwable e) {
        super(errCode, errMessage, e);
    }
}
