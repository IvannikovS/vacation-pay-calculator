package com.ivannikov.vacationpaycalculator;

import com.ivannikov.vacationpaycalculator.service.VacationPayService;
import com.ivannikov.vacationpaycalculator.utils.exception.InvalidInputException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VacationPayServiceTests {

    private final VacationPayService vacationPayService = new VacationPayService();

    @Test
    void testCalculate() {
        double averageSalary = 60000;
        int vacationDays = 10;
        double expected = 20477.7;
        double actual = vacationPayService.calculate(averageSalary, vacationDays);
        assertEquals(expected, actual, 0.25);
    }

    @Test
    void testCalculateWithStartDate() {
        double averageSalary = 60000;
        int vacationDays = 10;
        LocalDate startDate = LocalDate.of(2024, 5, 1);
        double expected = 32764.32;
        double actual = vacationPayService.calculateWithStartDate(averageSalary, vacationDays, startDate);
        assertEquals(expected, actual, 0.25);
    }

    @Test
    void testCalculateTotalCalendarDays() {
        LocalDate startDate = LocalDate.of(2024, 5, 1);
        int vacationDays = 10;
        int expectedTotalDays = 16;
        int actualTotalDays = vacationPayService.calculateTotalVacationDays(startDate, vacationDays);
        assertEquals(expectedTotalDays, actualTotalDays, "Общее количество календарных дней отпуска должно быть 16");
    }

    @Test
    void testCalculateWithInvalidSalary() {
        assertThrows(InvalidInputException.class, () -> vacationPayService.calculate(-50000, 10));
    }

    @Test
    void testCalculateWithZeroSalary() {
        assertThrows(InvalidInputException.class, () -> vacationPayService.calculate(0, 10));
    }

    @Test
    void testCalculateWithInvalidVacationDays() {
        assertThrows(InvalidInputException.class, () -> vacationPayService.calculate(50000, -5));
    }

    @Test
    void testCalculateWithNullStartDate() {
        assertThrows(InvalidInputException.class, () -> vacationPayService.calculateWithStartDate(50000, 10, null));
    }
}
