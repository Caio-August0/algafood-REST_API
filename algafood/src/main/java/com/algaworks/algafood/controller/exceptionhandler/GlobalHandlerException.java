package com.algaworks.algafood.controller.exceptionhandler;

import com.algaworks.algafood.domain.exception.abstractexception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.abstractexception.EntidadeExistente;
import com.algaworks.algafood.domain.exception.abstractexception.EntidadeNaoEncontradaException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalHandlerException extends ResponseEntityExceptionHandler {
    /* Ao invés de termos métodos manipuladores de exceções
     * que manipulam/tratam as exceções somente dentro de sua
     * classe, nos podemos tornar isso mais abrangente através
     * do uso de apenas uma única classe.
     *
     *
     * @RestControllerAdvice é uma especificação do @ControllerAdvice
     * que contém o @ResponseBody. O intuíto dessa anotação é aconselhar,
     * fazer recomendações, que deverão ser compartilhadas para todos os
     * controladores. Mais adequado para aplicações RESTful.
     *
     *
     * Neste caso, estamos utilizando a Classe com a anotação @RestControllerAdvice
     * para informar/aconselhar os outros Controladores que as exceções lançadas deverão
     * ser maninupaladas/tratadas através dos métodos definidos nessa classe.Além de informar
     * que toda as respostas/resultados obtidos nessa manipulação será retornados no corpo
     * da resposta HTTP.
     *
     *
     * A classe ResponseEntityExceptionHandler é uma classe base que manipula
     * todas as exceções lançadas pelo Spring MVC e retorna uma ResponseEntity
     * com todos os detalhes de erros formatados no corpo de resposta, seguindo
     * as especificações do RFC 7807.
     *
     *
     * O método handleExceptionInternal é o principal método da classe
     * ResponseEntityExceptionHandler, pois todos os outros métodos usam
     * esse método. Sua responsabilidade é realizar configurações finais
     * caso sejam necessárias para criar o ResponseEntity. Usa o createResponseEntity()
     * como etapa final da criação do ResponseEntity
     *
     *
     * Sobrescrevemos esse método para manipular exceções de forma específicas
     * Porém devemos tomar cuidado, pois todo os métodos de ResponseEntityExceptionHandler
     * irá utilizá-lo com isso devemos validar o bady, verificar se existe um conteúdo antes
     * de sobreescreve-lo.
     */

    //-------------------------------------------------------------

    @ExceptionHandler({EntidadeNaoEncontradaException.class})
    public ResponseEntity<?> handlerEntidadeNaoEcontradaException(
            EntidadeNaoEncontradaException ex, WebRequest webRequest) {

        Problem problem = Problem.builder(ProblemType.RECURSO_NAO_ENCONTRADO,ex.getMessage());

        return handleExceptionInternal(ex, problem,
                new HttpHeaders(), HttpStatus.NOT_FOUND,webRequest);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handlerEntidadeEmUsoException(
            EntidadeEmUsoException ex, WebRequest webRequest) {

        Problem problem = Problem.builder(ProblemType.ENTIDADE_EM_USO, ex.getMessage());

        return handleExceptionInternal(ex, problem,
                new HttpHeaders(),HttpStatus.CONFLICT,webRequest);
    }

    @ExceptionHandler(EntidadeExistente.class)
    public ResponseEntity<?> handlerEntidadeExistente(
            EntidadeExistente ex, WebRequest webRequest) {

        Problem problem = Problem.builder(ProblemType.ENTIDADE_EXISTENTE, ex.getMessage());

        return handleExceptionInternal(ex,problem,new HttpHeaders(),
                HttpStatus.BAD_REQUEST,webRequest);
    }




    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        HttpStatus httpStatus =
                HttpStatus.valueOf(statusCode.value());

        Problem problem = Problem.forStatus(httpStatus.value());

		/*Para alguns métodos padrões que passam nulo
			Apesar de ser criado um Body para eles
			queremos customizar e deixar esse corpo mais simples
		 * Irão deixar de retornar corpo vazio*/

        if (body == null || body instanceof String) {
            problem.setTitle(httpStatus.getReasonPhrase());
            problem.setDetail(ex.getMessage());
            body = problem;
            System.out.println("Entrei no body  handleExceptionInternal");
        } else if(body instanceof String) {
            //Caso seja passa uma String como corpo queremo que ela siga o padrão e seja colocada dentro de um objeto de problema
            //Nossos métodos manipuladores que passam a mensagem da excepction como corpo
            problem.setTitle(body.toString());
            body = problem;
        }

        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch( // tratamento de exceção de parâmetro inválido na URL
            TypeMismatchException ex, HttpHeaders headers,
            HttpStatusCode status, WebRequest request) {

        Throwable rootCause = ExceptionUtils.getRootCause(ex);
        System.out.println(rootCause.getClass());

        if (ex instanceof MethodArgumentTypeMismatchException) {
            return handlerMethodArgumentTypeMismatchException((MethodArgumentTypeMismatchException) ex, headers,status,request);
        }

        return super.handleTypeMismatch(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
                                                                   HttpStatusCode status, WebRequest request) {
        return super.handleNoHandlerFoundException(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        /*A biliblioteca do apache tem um biblioteca que fornece métodos para trabalharmos com exceção*/
        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        System.out.println(rootCause.getClass());

        if (rootCause instanceof InvalidFormatException) // Exceção de tipo de dado invalido
            return handlerInvalidFormatException((InvalidFormatException) rootCause, headers, status,request);

        if (rootCause instanceof PropertyBindingException){
            // Captura as seguintes exceções IgnoredPropertyException e UnrecognizedPropertyException
            //pois configuramos no application.propierties para serem lançadas
            return handlerPropertyBindingException((PropertyBindingException) rootCause,headers,status,request);
        }

        if (rootCause instanceof JsonParseException) { // Sintaxe do JSON invalida
            return handlerJsonParseException((JsonParseException) rootCause,headers,status,request);
        }

        Problem body = Problem.builder(ProblemType.MENSAGEM_INCOMPREENSIVEL, "O corpo da requisição é inválido verifique erro de sintaxe");
        return handleExceptionInternal(ex, body, headers, status, request);
    }


    @Override
    protected ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException ex, HttpHeaders headers,
                                                                    HttpStatusCode status, WebRequest request) {

            Problem problem = Problem.builder(ProblemType.RECURSO_NAO_ENCONTRADO,
                    String.format("O recurso %s que você tentou acessar, é inexistente.",
                            ex.getResourcePath()));

        return handleExceptionInternal(ex,problem, headers, status, request);
    }

    public ResponseEntity<Object> handlerPropertyBindingException(
            PropertyBindingException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        String path = ex.getPath().stream().map(reference -> reference.getFieldName()).collect(Collectors.joining("."));

        Problem problem = Problem.builder(ProblemType.PROPIEDADE_NAO_SUPORTADA,
                String.format("A propiedade %s não é suportada",path));

        return handleExceptionInternal(ex,problem,headers,status,request);
    }

    public ResponseEntity<Object> handlerInvalidFormatException(InvalidFormatException ex, HttpHeaders headers,
                                                                HttpStatusCode status, WebRequest request) {
        String path = ex.getPath().stream().map(reference -> reference.getFieldName()).collect(Collectors.joining("."));

        Problem problem = Problem.builder(ProblemType.MENSAGEM_INCOMPREENSIVEL,
                String.format("A propiedade %s recebeu o valor %s que é inválido." +
                        "Use os valores de tipo: %s como entrada de dados.",path ,ex.getValue(),ex.getTargetType().getSimpleName()));

        return handleExceptionInternal(ex,problem,headers,status,request);
    }

    public  ResponseEntity<Object> handlerJsonParseException(
            JsonParseException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        /*JsonParser jsonParser = ex.getProcessor();
        System.out.println("-------" + ex.getOriginalMessage());
        if(jsonParser != null) {
            System.out.println("Token atual: " + jsonParser.getCurrentToken());
            System.out.println("Localização: linha " +
                    ex.getLocation().getLineNr() +
                    ", coluna " + ex.getLocation().getColumnNr());
        }*/

        Problem problem = Problem.builder(
                ProblemType.SINTAXE_JSON_INVALIDA,"Sintaxe JSON inválida. Verifique sua sintaxe");


        return handleExceptionInternal(ex,problem,headers,status,request);
    }

    public ResponseEntity<Object> handlerMethodArgumentTypeMismatchException(
                                                MethodArgumentTypeMismatchException ex, HttpHeaders headers,
                                                HttpStatusCode statusCode, WebRequest request) {

        System.out.println("Entrei no handlerMethodArgumentTypeMismatchException---------------------------------");


        String details = String.format("O parâmetro da URL %s recebeu o valor %s, que é de um tipo inválido." +
                "Corrija e informe um valor compátivel com tipo %s.",ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());

        Problem problem = Problem.builder(ProblemType.PARAMETRO_INVALIDO, details);

        return handleExceptionInternal(ex, problem, headers,statusCode,request);
    }



}
