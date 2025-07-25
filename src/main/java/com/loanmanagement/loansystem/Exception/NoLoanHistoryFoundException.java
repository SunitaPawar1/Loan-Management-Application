package com.loanmanagement.loansystem.Exception;

public class NoLoanHistoryFoundException extends RuntimeException {
    public NoLoanHistoryFoundException(String message) {
        super(message);
    }
}
