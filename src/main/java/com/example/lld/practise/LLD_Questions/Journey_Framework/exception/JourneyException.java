package com.example.lld.practise.LLD_Questions.Journey_Framework.exception;

import com.example.lld.practise.LLD_Questions.Journey_Framework.enums.ErrorCode;
import lombok.Getter;

@Getter
public class JourneyException extends RuntimeException {
    private final ErrorCode code;

    public JourneyException(ErrorCode code, String message) {
        super(message);
        this.code = code;
    }

    @Override
    public String toString() {
        return "[" + code + "] " + getMessage();
    }
}
