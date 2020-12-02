package com.example.bankpoc.interceptor;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageBuilder {

    private String title;
    private String field;
    private String message;

    public MessageBuilder() {

    }

    public MessageBuilder(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "MessageBuilder{" +
                "title='" + title + '\'' +
                ", field='" + field + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
