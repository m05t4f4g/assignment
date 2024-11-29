package com.isc.assignment.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        // You can perform any setup needed before each test if necessary.
    }

    @Test
    public void givenValidCredentials_whenGetCourses_thenStatusOk() throws Exception {
        mockMvc.perform(get("/courses/")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin", "admin"))) // Use correct endpoint and credentials
                .andExpect(status().isOk());
    }

    @Test
    public void givenInvalidCredentials_whenGetCourses_thenStatusUnauthorized() throws Exception {
        mockMvc.perform(get("/courses/")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("user", "wrongpassword")))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void givenNoCredentials_whenGetCourses_thenStatusUnauthorized() throws Exception {
        mockMvc.perform(get("/courses/"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails("user")
    void testUserAccess() throws Exception {
        mockMvc.perform(get("/courses/")) // Change to your actual endpoint
                .andExpect(status().isOk());
    }


}