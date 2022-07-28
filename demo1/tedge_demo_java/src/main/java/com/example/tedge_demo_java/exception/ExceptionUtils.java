package com.example.tedge_demo_java.exception;

/**
 * @author lumang.wei@tuya.com
 * @since 2021/11/24
 */
public enum ExceptionUtils {

    UNKNOWN("10000", "UNKNOWN ERROR"),
    JSON_ERROR("10001", "JSON CONVERT ERROR");

    private final String code;
    private final String message;

    ExceptionUtils(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public DriveException toException() {
        return new DriveException(code, message);
    }
}
