package br.com.banco.exception;

public class InvestmentNotFoundException extends RuntimeException {

    public InvestmentNotFoundException(String message) {
        super(message);
    }
}
