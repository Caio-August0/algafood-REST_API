package com.algaworks.algafood.controller.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatusCode;


@JsonInclude(JsonInclude.Include.NON_NULL) // Inclui no JSON apenas a propiedades que não são nulas
public class Problem {

    private String type;
    private String title;
    private int status;
    private String detail;


    private Problem(int rawStatusCode) {
        setStatus(rawStatusCode);
    }

    public static Problem forStatus(int status) {
       return new Problem(status);
    }
    /*Não  é necessário configurar o tittle pois a Classe ProblemDetail tem método para isso*/

    public static Problem builder(ProblemType problemType, String details){

        Problem problem = new Problem(problemType.getHttpStatus().value());

            problem.setTitle(problemType.getHttpStatus().getReasonPhrase());
            problem.setType(problemType.getUri());
            problem.setDetail(details);

            return problem;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
