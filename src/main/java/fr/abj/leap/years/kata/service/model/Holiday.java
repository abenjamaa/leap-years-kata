package fr.abj.leap.years.kata.service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Holiday {
    private String date;
    private String name;
}
