package com.infogain.demo.controller;

import com.infogain.demo.exception.BadArgumentsException;
import com.infogain.demo.exception.InternalException;
import com.infogain.demo.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ExController.class)
class ExControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void givenNotFound_whenGetSpecificException_thenNotFoundCode() throws Exception {
        String exceptionParam = "not_found";

        mvc.perform(get("/v1/exception/{exception_id}", exceptionParam)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
                .andExpect(result -> assertEquals("resource not found", result.getResolvedException().getMessage()));
    }

    @Test
    public void givenBadArguments_whenGetSpecificException_thenBadRequest() throws Exception {
        String exceptionParam = "bad_arguments";

        mvc.perform(get("/v1/exception/{exception_id}", exceptionParam)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BadArgumentsException))
                .andExpect(result -> assertEquals("bad arguments", result.getResolvedException().getMessage()));
    }

    @Test
    public void givenOther_whenGetSpecificException_thenInternalServerError() throws Exception {
        String exceptionParam = "dummy";

        mvc.perform(get("/v1/exception/{exception_id}", exceptionParam)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InternalException))
                .andExpect(result -> assertEquals("internal error", result.getResolvedException().getMessage()));
    }
}