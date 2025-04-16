package com.ivannikov.vacationpaycalculator.controller;

import com.ivannikov.vacationpaycalculator.service.VacationPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/calculate")
public class VacationPayController {

    private final VacationPayService vacationPayService;

    @Autowired
    public VacationPayController(VacationPayService vacationPayService) {
        this.vacationPayService = vacationPayService;
    }

    @GetMapping
    public double calculateVacationPay(
            @RequestParam double averageSalary,
            @RequestParam int vacationDays,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate) {

        if (startDate != null) {
            return vacationPayService.calculateWithStartDate(averageSalary, vacationDays, startDate);
        } else {
            return vacationPayService.calculate(averageSalary, vacationDays);
        }
    }
}
