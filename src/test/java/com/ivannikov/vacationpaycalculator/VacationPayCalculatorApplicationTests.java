package com.ivannikov.vacationpaycalculator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
class VacationPayCalculatorApplicationTests {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void testCalculateWithoutStartDate() throws Exception {
        this.mockMvc.perform(get("/calculate")
                        .param("averageSalary", "60000")
                        .param("vacationDays", "10"))
                .andExpect(status().isOk())
                .andExpect(content().string("20477.8"));
    }

    @Test
    void testCalculateWithStartDate() throws Exception {
        this.mockMvc.perform(get("/calculate")
                        .param("averageSalary", "60000")
                        .param("vacationDays", "10")
                        .param("startDate", "2024-05-01"))
                .andExpect(status().isOk())
                .andExpect(content().string("32764.48"));
    }
}
