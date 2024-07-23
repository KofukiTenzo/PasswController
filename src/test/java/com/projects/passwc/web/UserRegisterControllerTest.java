package com.projects.passwc.web;

import com.projects.passwc.DTO.UserRegisterDTO;
import com.projects.passwc.service.UserService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserRegisterControllerTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    @Autowired
    private UserService userService;

    @BeforeClass
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testShowRegistrationForm() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register_form"))
                .andExpect(model().attributeExists("userRegisterDTO"));
    }

    @Test
    public void testProcessRegistration_Success() throws Exception {
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setUsername("testuser");
        userRegisterDTO.setPassword("password");
        userRegisterDTO.setEmail("test@example.com");

        Mockito.when(userService.userExistByUsername(userRegisterDTO.getUsername())).thenReturn(false);
        Mockito.when(userService.userExistByEmail(userRegisterDTO.getEmail())).thenReturn(false);

        mockMvc.perform(post("/register")
                        .flashAttr("userRegisterDTO", userRegisterDTO))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/profile"));

        Mockito.verify(userService).register(userRegisterDTO);
    }

    @Test
    public void testProcessRegistration_UserExists() throws Exception {
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setUsername("existinguser");
        userRegisterDTO.setPassword("password");
        userRegisterDTO.setEmail("existing@example.com");

        Mockito.when(userService.userExistByUsername("existinguser")).thenReturn(true);
        Mockito.when(userService.userExistByEmail("existing@example.com")).thenReturn(true);

        mockMvc.perform(post("/register")
                        .flashAttr("userRegisterDTO", userRegisterDTO))
                .andExpect(status().isOk())
                .andExpect(view().name("register_form"))
                .andExpect(model().attributeHasFieldErrors("userRegisterDTO", "username"))
                .andExpect(model().attributeHasFieldErrors("userRegisterDTO", "email"));
    }
}
