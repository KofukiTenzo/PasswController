package com.projects.passwc.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class UserRegisterControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getRegistrationPage_ShouldViewRegisterForm() throws Exception {
        this.mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register_form"));
    }

    @Test
    public void registerNewUser_ShouldCreateNewUser() throws Exception{
        mockMvc.perform(post("/register")
                        .param("username", "testUser2")
                        .param("email", "testUser2@localhost.com")
                        .param("password", "testPasswd")
                        .with(csrf()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(redirectedUrl("/user/profile"));
    }

    @Test
    public void loginWithCreatedUserData_ShouldSuccessfulLoginThanRedirectionOnUserProfile() throws Exception{
        mockMvc.perform(post("/login")
                        .param("username", "testUser2")
                        .param("password", "testPasswd")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/profile"));
    }
}
