package com.msavaliadorcredito.api.application.ex;

public class ErroSolicitacaoCartaoException extends RuntimeException{
    public ErroSolicitacaoCartaoException(String message) {
        super(message);
    }
}
