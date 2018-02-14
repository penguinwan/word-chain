package com.penguinwan.word.chain.infrastructure;

public class InvalidFileContentException extends Exception {
    public InvalidFileContentException(String message, Throwable cause) {
        super(message, cause);
    }
}
