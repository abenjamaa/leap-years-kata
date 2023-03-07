package fr.abj.leap.years.kata.controller.v1;

import fr.abj.leap.years.kata.service.model.Holiday;
import fr.abj.leap.years.kata.controller.v1.dto.HolidayV1;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface HolidaysMapper {

    HolidaysMapper INSTANCE = Mappers.getMapper(HolidaysMapper.class);

    List<HolidayV1> toDto(List<Holiday> holidays);
}
