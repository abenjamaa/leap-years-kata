package fr.abj.leap.years.kata;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {
	@Autowired
	private TestRestTemplate restTemplate;
	@LocalServerPort
	int randomServerPort;
	@Test
	public void shouldReturn500WhenYearIsNotANumber()
	{
		//GIVEN
		String url = "http://localhost:" + randomServerPort + "/v1/years/zezez/leap";

		//WHEN
		ResponseEntity<String> result = this.restTemplate.getForEntity(url, String.class);

		//THEN
		assertThat(result.getStatusCode().value()).isEqualTo(500);
		assertThat(result.getBody()).contains("is not a number");
	}

	@Test
	public void shouldReturnTrueWhenIsLeapYear()
	{
		//GIVEN
		String url = "http://localhost:" + randomServerPort + "/v1/years/2000/leap";

		//WHEN
		ResponseEntity<String> result = this.restTemplate.getForEntity(url, String.class);

		//THEN
		assertThat(result.getStatusCode().value()).isEqualTo(200);
		assertThat(result.getBody()).isEqualTo("true");
	}

	@Test
	public void shouldReturnFalseWhenIsNotLeapYear()
	{
		//GIVEN
		String url = "http://localhost:" + randomServerPort + "/v1/years/1999/leap";

		//WHEN
		ResponseEntity<String> result = this.restTemplate.getForEntity(url, String.class);

		//THEN
		assertThat(result.getStatusCode().value()).isEqualTo(200);
		assertThat(result.getBody()).isEqualTo("false");
	}

}
