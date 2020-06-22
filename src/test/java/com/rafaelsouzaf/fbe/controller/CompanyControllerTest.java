package com.rafaelsouzaf.fbe.controller;

import com.rafaelsouzaf.fbe.WebAppConfigTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CompanyControllerTest extends WebAppConfigTest {

    @Test
    void getAll_thenResponseOK() throws Exception {
        mvc
                .perform(
                        get("/company")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    void getById_thenResponseOkAndNotEmpty() throws Exception {
        mvc
                .perform(
                        get("/company/3")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty());
    }

    @Test
    void getById_thenBadRequest() throws Exception {
        mvc
                .perform(
                        get("/company/asdasd")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").isNotEmpty());
    }

    @Test
    void getById_thenNotFound() throws Exception {
        mvc
                .perform(
                        get("/company/0")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").isNotEmpty());
    }

    @Test
    void wrongURL_thenNotFound() throws Exception {
        mvc
                .perform(
                        get("/blahblah")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").isNotEmpty());
    }

    @Test
    void getAverageSalary_thenResponseOkAndNotEmpty() throws Exception {
        mvc
                .perform(
                        get("/company/average-salary/1")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty());
    }

    @Test
    void getAverageSalary_thenResponseNotFound() throws Exception {
        mvc
                .perform(
                        get("/company/average-salary/0")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").isNotEmpty());
    }

    @Test
    void insert_thenReturnOk() throws Exception {
        mvc
                .perform(
                        post("/company")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"name\": \"Company Name Here\"}"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty());
    }

    @Test
    void insertWrong_thenBadRequest() throws Exception {
        mvc
                .perform(
                        post("/company")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"XXXname\": \"Company Name Here\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").isNotEmpty());
    }

    @Test
    void edit_thenReturnOk() throws Exception {
        mvc
                .perform(
                        put("/company")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"id\": 3, \"name\": \"Changed Company Name\"}"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty());
    }

    @Test
    void editNotExistent_thenNotFound() throws Exception {
        mvc
                .perform(
                        put("/company")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"id\": 0, \"name\": \"Changed Company Name\"}"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").isNotEmpty());
    }

    @Test
    void delete_thenReturnOk() throws Exception {
        mvc
                .perform(delete("/company/11"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void delete_thenNotFound() throws Exception {
        mvc
                .perform(delete("/company/0"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").isNotEmpty());
    }

    @Test
    void delete_thenErrorConstraint() throws Exception {
        mvc
                .perform(delete("/company/3"))
                .andExpect(status().is5xxServerError())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").isNotEmpty());
    }

}
