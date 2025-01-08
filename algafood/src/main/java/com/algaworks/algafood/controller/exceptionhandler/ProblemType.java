package com.algaworks.algafood.controller.exceptionhandler;

import org.springframework.http.HttpStatus;

public enum ProblemType {

    PARAMETRO_INVALIDO(HttpStatus.BAD_REQUEST,"/parametro-invalido"),
    PROPIEDADE_NAO_SUPORTADA(HttpStatus.BAD_REQUEST,"/propiedade-nao-suportada"),
    MENSAGEM_INCOMPREENSIVEL(HttpStatus.BAD_REQUEST, "/mensagem-incompreensivel"),
    ENTIDADE_EXISTENTE(HttpStatus.BAD_REQUEST,"/entidade-existente"),
    SINTAXE_JSON_INVALIDA(HttpStatus.BAD_REQUEST, "/sintaxe-json-invalida"),

    RECURSO_NAO_ENCONTRADO(HttpStatus.NOT_FOUND, "/recurso-nao-encontrado"),
    ENTIDADE_EM_USO(HttpStatus.CONFLICT,"/entidade-em-uso");


    private HttpStatus httpStatus;
    private String uri;


    private static final String dominio = "https://algaworks.como.br";
    private static final String suporte = "/suporte";

    ProblemType(HttpStatus httpStatus, String path) {
        this.httpStatus = httpStatus;
        this.uri = dominio + path;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getUri() {
        return uri;
    }

}
