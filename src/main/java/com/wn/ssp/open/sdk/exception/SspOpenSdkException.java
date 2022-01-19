package com.wn.ssp.open.sdk.exception;

import com.wn.ssp.open.sdk.Constant;

/**
 * @author majunjie
 * @date 2022/1/19 10:08
 */
public class SspOpenSdkException extends RuntimeException {

    private String errorCode;
    private String message;

    public SspOpenSdkException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public SspOpenSdkException(String message) {
        super(message);
        this.errorCode = Constant.DEFAULT_ERROR_CODE;
    }

    public String getErrorCode() {
        return errorCode;
    }

}
