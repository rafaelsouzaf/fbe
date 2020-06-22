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
class EmployeeControllerTest extends WebAppConfigTest {

    @Test
    void getAll_thenResponseOK() throws Exception {
        mvc
                .perform(
                        get("/employee")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    void getById_thenResponseOkAndNotEmpty() throws Exception {
        mvc
                .perform(
                        get("/employee/3")
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
                        get("/employee/asdasd")
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
                        get("/employee/0")
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
                        post("/employee")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"name\": \"Rafael\", \"surname\": \"Fijalkowski\", \"email\": \"myemail@gmail.com\", \"address\": \"Street 41, 50832\", \"salary\": 120000, \"company\": {\"id\": 4}}'"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty());
    }

    @Test
    void insertWrong_thenBadRequest() throws Exception {
        mvc
                .perform(
                        post("/employee")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"XXXXXname\": \"Rafael\", \"surname\": \"Fijalkowski\", \"email\": " +
                                        "\"myemail@gmail.com\", \"address\": \"Street 41, 50832\", \"salary\": 120000, \"company\": {\"id\": 4}}'"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").isNotEmpty());
    }

    @Test
    void edit_thenReturnOk() throws Exception {
        mvc
                .perform(
                        put("/employee")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"id\": 3, \"name\": \"Rafael\", \"surname\": \"Fijalkowski\", \"email\": \"myemail@gmail.com\", \"address\": \"Street 41, 50832\", \"salary\": 120000, \"company\": {\"id\": 4}}"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty());
    }

    @Test
    void editNotExistent_thenNotFound() throws Exception {
        mvc
                .perform(
                        put("/employee")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"id\": 0, \"name\": \"Rafael\", \"surname\": \"Fijalkowski\", \"email\": \"myemail@gmail.com\", \"address\": \"Street 41, 50832\", \"salary\": 120000, \"company\": {\"id\": 4}}"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").isNotEmpty());
    }

    @Test
    void delete_thenReturnOk() throws Exception {
        mvc
                .perform(delete("/employee/1"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void delete_thenNotFound() throws Exception {
        mvc
                .perform(delete("/employee/0"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").isNotEmpty());
    }

}
