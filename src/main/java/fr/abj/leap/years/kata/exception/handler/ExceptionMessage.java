package fr.abj.leap.years.kata.exception.handler;

import lombok.Data;

@Data
public class ExceptionMessage {

    private  String message;
    private  String error;
    private  String path;
}
