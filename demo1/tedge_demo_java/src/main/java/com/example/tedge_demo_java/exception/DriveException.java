package com.example.tedge_demo_java.exception;

/**
 * @author lumang.wei@tuya.com
 * @since 2021/11/24
 */
public class DriveException extends RuntimeException {
    private final String code;
    private final String message;

    public DriveException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public DriveException(String code, String message, Throwable t) {
        super(message, t);
        this.code = code;
        this.message = message;
    }

    public DriveException(String message) {
        super(message);
        this.code = "10000";
        this.message = message;
    }

    public DriveException(String message, Throwable t) {
        super(message, t);
        this.code = "10000";
        this.message = message;
    }
}
