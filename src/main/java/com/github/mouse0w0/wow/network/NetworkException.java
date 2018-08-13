package com.github.mouse0w0.wow.network;

public class NetworkException extends RuntimeException {

    public NetworkException(Throwable cause){
        super(cause);
    }

    public NetworkException(String message) {
        super(message);
    }

    public NetworkException(String message, Throwable cause){
        super(message, cause);
    }
}
