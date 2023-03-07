package fr.abj.leap.years.kata.controller.v1.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class HolidayV1 implements Serializable {

    private final static long serialVersionUID = 5798633039064811438L;
    private String date;
    private String name;
}
