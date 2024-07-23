package com.projects.passwc.web;

import com.projects.passwc.DTO.PasswdsDTO;
import com.projects.passwc.DTO.UserRegisterDTO;
import com.projects.passwc.Entitys.Passwds;
import com.projects.passwc.Entitys.User;
import com.projects.passwc.response.PasswdsResponse;
import com.projects.passwc.service.PasswdsService;
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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class PasswdsControllerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    @Autowired
    private PasswdsService passwdsService;
    @MockBean
    @Autowired
    private UserService userService;

    @BeforeClass
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    private User setupUser() {
        UserRegisterDTO user = new UserRegisterDTO();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setEmail("testuser@test.com");

        User registeredUser = new User();
        registeredUser.setUsername(user.getUsername());
        registeredUser.setPassword(user.getPassword());
        registeredUser.setEmail(user.getEmail());

        Mockito.when(userService.register(Mockito.any(UserRegisterDTO.class))).thenReturn(registeredUser);
        return registeredUser;
    }

    @Test
    public void testShowPasswordForm() throws Exception {
        mockMvc.perform(get("/passwds/add")
                        .with(user(setupUser())))
                .andExpect(status().isOk())
                .andExpect(view().name("passwdForm"))
                .andExpect(model().attributeExists("passwdForm"));
    }

    @Test
    public void testGeneratePassword() throws Exception {
        String generatedPassword = "generatedPass123";

        Mockito.when(passwdsService.generatePasswd(true, false, false, false, 10)).thenReturn(generatedPassword);

        mockMvc.perform(get("/passwds/generatePassword")
                        .with(user(setupUser()))
                        .param("useLower", "true")
                        .param("length", "10"))
                .andExpect(status().isOk())
                .andExpect(content().string(generatedPassword));
    }

    @Test
    public void testSavePassword() throws Exception {
        PasswdsDTO passwdsDTO = new PasswdsDTO();
        passwdsDTO.setResourceName("Resource");
        passwdsDTO.setPasswd("password123");

        Mockito.doNothing().when(passwdsService).save(Mockito.any(PasswdsDTO.class), Mockito.anyString());

        mockMvc.perform(post("/passwds/add")
                        .with(user(setupUser()))
                        .flashAttr("passwdForm", passwdsDTO))
                .andExpect(status().is2xxSuccessful());

        PasswdsResponse response = new PasswdsResponse();
        response.setPageNumber(0);
        response.setPageSize(7);
        response.setTotalElements(1);
        response.setTotalPages(1);

        List<Passwds> passwdsList = new ArrayList<>();
        Passwds passwd = new Passwds();
        passwd.setId(1L);
        passwd.setResourceName("Resource");
        passwd.setPasswd("password123");
        passwd.setUser(setupUser());
        passwdsList.add(passwd);

        response.setPasswdsList(passwdsList);

        Mockito.when(passwdsService.getAllUserPasswds(0, "testuser")).thenReturn(response);

        mockMvc.perform(get("/passwds?page=0")
                        .with(user(setupUser())))
                .andExpect(status().isOk())
                .andExpect(view().name("passwds"))
                .andExpect(model().attributeExists("response"))
                .andExpect(model().attribute("response", response))
                .andExpect(xpath("//div[@id='passwd_1']//span[@id='name']").string("Resource"))
                .andExpect(xpath("//div[@id='passwd_1']//span[@id='passwd']").string("password123"));
    }

}