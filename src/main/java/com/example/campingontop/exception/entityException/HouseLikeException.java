package com.example.campingontop.exception.entityException;

import com.example.campingontop.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HouseLikeException extends RuntimeException {
    private ErrorCode errorCode;
    private String message;


    public HouseLikeException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = null;
    }

    @Override
    public String getMessage() {
        if (message == null) {
            return errorCode.getMessage();
        } else {
            return String.format("[%s] %s", message, errorCode.getMessage());
        }
    }
}
