package fr.abj.leap.years.kata.service;

import fr.abj.leap.years.kata.exception.NotANumberException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class YearsServiceTest {

    YearsService target = new YearsService();

    @Test
    void shouldThrowErrorWhenYearIsNotANumber() {
        //GIVEN
        String notANumber = "NotANumber";

        //WHEN
        Throwable thrown = catchThrowable(() -> {
            target.isLeap(notANumber);
        });
        //THEN
        assertThat(thrown)
                .isInstanceOf(NotANumberException.class)
                .hasMessageContaining("is not a number");
    }

    @Test
    void shouldReturnTrueWhenYearsDivisibleBy400() throws NotANumberException {
        assertThat(target.isLeap("2000")).isTrue();
    }

    @Test
    void shouldReturnFalseWhenYearsDivisibleBy100ButNotBy400() throws NotANumberException {

        assertThat(target.isLeap("1700")).isFalse();
        assertThat(target.isLeap("1800")).isFalse();
        assertThat(target.isLeap("1900")).isFalse();
        assertThat(target.isLeap("2100")).isFalse();
    }

    @Test
    void shouldReturnTrueWhenYearsDivisibleBy4ButNotBy100() throws NotANumberException {

        assertThat(target.isLeap("2008")).isTrue();
        assertThat(target.isLeap("2012")).isTrue();
        assertThat(target.isLeap("2016")).isTrue();
    }

    @Test
    void shouldReturnFalseWhenYearsNotDivisibleBy4() throws NotANumberException {
        assertThat(target.isLeap("2017")).isFalse();
        assertThat(target.isLeap("2018")).isFalse();
        assertThat(target.isLeap("2019")).isFalse();
    }

}