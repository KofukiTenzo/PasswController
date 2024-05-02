package com.projects.passwc.web;


import com.projects.passwc.DTO.PasswdsDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PasswdsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        PasswdsDTO passwdsDTO = new PasswdsDTO();
        passwdsDTO.setResourceName("Resource");
        passwdsDTO.setPasswd("Password");
    }

    @Test
    @WithMockUser(username = "testUser", password = "testUser")
    public void getPasswdsPage_ShouldViewPasswds() throws Exception {

        this.mockMvc.perform(get("/passwds"))
                .andExpect(status().isOk())
                .andExpect(view().name("passwds"));
    }

    @Test
    @WithMockUser(username = "testUser", password = "testUser")
    public void getAddPasswdPage_ShouldViewAdd() throws Exception {

        this.mockMvc.perform(get("/passwds/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("passwdForm"));
    }

    @Test
    @WithMockUser(username = "testUser", password = "testUser")
    public void savePasswd_ShouldAddPassThenRedirectToPasswds() throws Exception {

        mockMvc.perform(post("/passwds/add")
                        .param("resourceName", "Resource")
                        .param("passwd", "Password")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/passwds"));
    }

    @Test
    @WithMockUser(username = "testUser", password = "testUser")
    public void getPasswds_ShouldFindAddedPasswd() throws Exception {

        mockMvc.perform(get("/passwds"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Resource")))
                .andExpect(content().string(containsString("Password")));
    }

    @Test
    @WithMockUser(username = "testUser", password = "testUser")
    public void saveGeneratedPasswdAndGetThem_ShouldAddGeneratedPassThenRedirectToPasswdsAndFindThem() throws Exception {

        MvcResult result = mockMvc.perform(get("/passwds/generatePassword")
                        .param("useLower", "true")
                        .param("useUpper", "true")
                        .param("useDigits", "true")
                        .param("usePunctuation", "true")
                        .param("length", "15")
                        .with(csrf()))
                .andReturn();

        String generatedPasswd = result.getResponse().getContentAsString();

        mockMvc.perform(post("/passwds/add")
                        .param("resourceName", "Resource")
                        .param("passwd", generatedPasswd)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/passwds"));

        mockMvc.perform(get("/passwds"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Resource")))
                .andExpect(content().string(containsString(generatedPasswd)));
    }

    @Test
    @WithMockUser(username = "testUser", password = "testUser")
    public void getSearchedPasswd_ShouldFindPasswd() throws Exception {
        mockMvc.perform(get("/passwds/search")
                        .param("query", "34t34t")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("34t34t")));
    }
}