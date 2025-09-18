package br.com.banco.exception;

public class NoFoundsEnoughException extends RuntimeException {

    public NoFoundsEnoughException(String message) {
        super(message);
    }
}
