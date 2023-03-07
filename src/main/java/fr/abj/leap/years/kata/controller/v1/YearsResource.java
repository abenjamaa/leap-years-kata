package fr.abj.leap.years.kata.controller.v1;

import fr.abj.leap.years.kata.exception.NotANumberException;
import fr.abj.leap.years.kata.service.YearsService;
import fr.abj.leap.years.kata.service.model.Holiday;
import fr.abj.leap.years.kata.controller.v1.dto.HolidayV1;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/years")
@RequiredArgsConstructor
public class YearsResource {

    private final YearsService yearsService;

    @GetMapping("/{year}/leap")
    public ResponseEntity<Boolean> isLeap(@PathVariable String year) throws NotANumberException {
        return new ResponseEntity<>(yearsService.isLeap(year), HttpStatus.OK);
    }

    @GetMapping("/{year}/holidays")
    public ResponseEntity<List<HolidayV1>> getHolidays(@PathVariable String year) throws NotANumberException {
        List<Holiday> holidays = yearsService.getHolidays(year);
        return CollectionUtils.isEmpty(holidays) ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT):
                new ResponseEntity<>(HolidaysMapper.INSTANCE.toDto(holidays), HttpStatus.OK);
    }

}
