package com.example.bankpoc.interceptor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.example.bankpoc.exception.BusinessException;
import com.example.bankpoc.exception.NotFoundException;

public class ControllerExceptionHandlerTest {

    private BusinessException businessException;
    private NotFoundException notFoundException;
    ControllerExceptionHandler controllerExceptionHandler;

    @Before
    public void setUp() throws Exception {
        businessException = new BusinessException("Titulo","mensagem","campo");
        notFoundException = new NotFoundException("Titulo","mensagem","campo");
        controllerExceptionHandler = new ControllerExceptionHandler();
    }

    @Test
    public void processValidationErrorInternTest_Ok() {
        MessageBuilder messageBuilder = controllerExceptionHandler.processValidationErrorIntern(businessException);
        assertEquals("Titulo", messageBuilder.getTitle());
        assertEquals("mensagem", messageBuilder.getMessage());
        assertEquals("campo", messageBuilder.getField());

    }

    @Test
    public void processValidationErrorInternNotFoundTest_Ok() {
        MessageBuilder messageBuilder = controllerExceptionHandler.processValidationErrorInternNotFound(notFoundException);
        assertEquals("Titulo", messageBuilder.getTitle());
        assertEquals("mensagem", messageBuilder.getMessage());
        assertEquals("campo", messageBuilder.getField());
        assertEquals("MessageBuilder{title='Titulo', field='campo', message='mensagem'}", messageBuilder.toString());
    }
}