package com.kelaskoding.auth_service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
class ValidateTokenTests {

        @Autowired
        private MockMvc mockMvc;
        private String tokenValid = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoYW5pIiwiaWF0IjoxNzQ1NDY2MDI5LCJleHAiOjE3NDU0Njc4Mjl9._G7HZGz3oPuRwZMgW5mk-puR-n5hvaDiZ8LBxUCwsug";
        @Test
        void validateTokenSuccess() throws Exception {
            mockMvc.perform(
                            get("/api/auth/validateToken")
                                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                    .param("token", tokenValid)
                    ).andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.content().string("Token is Valid"));
        }


    @Test
    void validateTokenFail() throws Exception {
        mockMvc.perform(
                        get("/api/auth/validateToken")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("token", "INVALID_TOKEN_STRING")
                ).andExpect(status().isForbidden()) // atau .isUnauthorized() kalau itu yang kamu pakai
                .andDo(print()); // biar keliatan di log
    }

}
