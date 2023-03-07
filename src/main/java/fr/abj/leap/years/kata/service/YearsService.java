package fr.abj.leap.years.kata.service;

import fr.abj.leap.years.kata.exception.NotANumberException;
import fr.abj.leap.years.kata.service.model.Holiday;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Service
public class YearsService {
    private static final String ANNEE = "annee";
    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.gouv.calendrier}")
    private String apiGouvUrl;

    /**
     * check if is a leap year
     * @param strYear year to check
     * @return is a leap year
     */
    public Boolean isLeap(String strYear) throws NotANumberException {

        checkYear(strYear);

        int year = Integer.parseInt(strYear);

        return checkIfIsLeap(year);
    }

    /**
     * retrieve the holidays of a year
     * @param year of research
     * @return holidays
     */
    public List<Holiday> getHolidays(String year) throws NotANumberException {

        checkYear(year);

        ResponseEntity<Map> response = restTemplate.exchange(getUri(year), HttpMethod.GET, new HttpEntity<>(getHeaders()), Map.class);
        return Optional.of(response)
                .map(HttpEntity::getBody)
                .map(this::extractHolidaysFromMap)
                .orElse(Collections.emptyList());
    }


    /**
     * check if year is a leap year
     * @param year to check
     * @return is leap year
     */
    private static Boolean checkIfIsLeap(int year) {
        if(year % 400 == 0) {
            return true;
        }
        if (year % 100 == 0) {
            return false;
         }
        if(year % 4 == 0 ){
            return true;
        }

        return false;
    }


    private static void checkYear(String year) throws NotANumberException {
        if(!NumberUtils.isParsable(year)){
            throw new NotANumberException("< "+ year +" >"+ " is not a number");
        }
    }

    /**
     * extract holidays from map
     * @param holidaysMap holidays map
     * @return holidays
     */
    private List<Holiday> extractHolidaysFromMap(Map<String, String> holidaysMap) {
        List<Holiday> response = new ArrayList<>();
        for (Map.Entry<String, String> entry : holidaysMap.entrySet()) {
            response.add(Holiday.builder()
                    .date(entry.getKey())
                    .name(entry.getValue())
                    .build());
        }
        return response;
    }

    /**
     * creation of the headers of the resttemplate call
     * @return headers
     */
    private static HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    /**
     * Creation of the gouv api URI to retrieve the holidays of a year
     * @param year year of research
     * @return URI
     */
    private URI getUri(String year){
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put(ANNEE, year);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiGouvUrl);
        return uriBuilder.buildAndExpand(uriParams).toUri();
    }
}
