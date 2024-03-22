package com.chatmrkhoi.chatmrkhoi.exception.cutomer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int code;
    private String message;

    public SignUpException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
