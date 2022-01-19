package com.wn.ssp.open.sdk.exception;

public class ErrorMsg {

    public static SspOpenSdkException NEW(String errorCode, String message) {
        return new SspOpenSdkException(errorCode, message);
    }

    public static SspOpenSdkException NEW(String message) {
        return new SspOpenSdkException(message);
    }

}
