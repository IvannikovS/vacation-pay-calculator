package com.ivannikov.vacationpaycalculator.service;

import com.ivannikov.vacationpaycalculator.utils.exception.InvalidInputException;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

@Service
public class VacationPayService {

    private static final double AVERAGE_DAYS_IN_MONTH = 29.3;

    private static final Set<LocalDate> HOLIDAYS = Set.of(
            LocalDate.of(2024, 1, 1),
            LocalDate.of(2024, 1, 7),
            LocalDate.of(2024, 2, 23),
            LocalDate.of(2024, 3, 8),
            LocalDate.of(2024, 5, 1),
            LocalDate.of(2024, 5, 9),
            LocalDate.of(2024, 6, 12),
            LocalDate.of(2024, 11, 4)
    );

    public double calculate(double averageSalary, int vacationDays) {
        validateInput(averageSalary, vacationDays);
        double dailyEarnings = round(averageSalary / AVERAGE_DAYS_IN_MONTH);
        return round(dailyEarnings * vacationDays);
    }

    public double calculateWithStartDate(double averageSalary, int vacationDays, LocalDate startDate) {
        validateInput(averageSalary, vacationDays);

        if (startDate == null) {
            throw new InvalidInputException("Дата начала отпуска не может быть пустой.");
        }

        int totalVacationDays = calculateTotalVacationDays(startDate, vacationDays);
        return calculate(averageSalary, totalVacationDays);
    }

    public int calculateTotalVacationDays(LocalDate startDate, int vacationDays) {
        int daysCounted = 0;
        int workingDays = 0;
        LocalDate date = startDate;

        while (workingDays < vacationDays) {
            if (isWorkingDay(date)) {
                workingDays++;
            }
            daysCounted++;
            date = date.plusDays(1);
        }

        return daysCounted;
    }

    private boolean isWorkingDay(LocalDate date) {
        return !(date.getDayOfWeek() == DayOfWeek.SATURDAY ||
                date.getDayOfWeek() == DayOfWeek.SUNDAY ||
                HOLIDAYS.contains(date));
    }

    private void validateInput(double averageSalary, int vacationDays) {
        if (averageSalary <= 0) {
            throw new InvalidInputException("Средняя зарплата должна быть положительным числом.");
        }
        if (vacationDays <= 0) {
            throw new InvalidInputException("Количество дней отпуска должно быть положительным числом.");
        }
    }

    private double round(double value) {
        return Math.round(value * 100) / 100.0;
    }
}
