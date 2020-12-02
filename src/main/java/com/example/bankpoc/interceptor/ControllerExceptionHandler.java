package com.example.bankpoc.interceptor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.example.bankpoc.exception.BusinessException;
import com.example.bankpoc.exception.NotFoundException;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public List<MessageBuilder> processValidationError(MethodArgumentNotValidException exception) {

        List<MessageBuilder> listErrors = new ArrayList<>();
        for(int i = 0; i < exception.getBindingResult().getAllErrors().size();i++){
            listErrors.add(new MessageBuilder(exception.getBindingResult().getFieldErrors().get(i).getField(),
                    exception.getBindingResult().getAllErrors().get(i).getDefaultMessage()));
        }
        return listErrors;
    }


    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MessageBuilder processValidationErrorIntern(BusinessException exception) {
        MessageBuilder messageBuilder = new MessageBuilder();
        messageBuilder.setField(exception.getField());
        messageBuilder.setTitle(exception.getError());
        messageBuilder.setMessage(exception.getMessage());
        return messageBuilder;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public MessageBuilder processValidationErrorInternNotFound(NotFoundException exception) {
        MessageBuilder messageBuilder = new MessageBuilder();
        messageBuilder.setField(exception.getField());
        messageBuilder.setTitle(exception.getError());
        messageBuilder.setMessage(exception.getMessage());
        return messageBuilder;
    }


}