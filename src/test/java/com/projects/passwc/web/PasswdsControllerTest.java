package com.projects.passwc.web;
//import com.projects.passwc.DTO.PasswdsDTO;
//import com.projects.passwc.PasswcApplication;
//import com.projects.passwc.service.PasswdsService;
//import com.projects.passwc.service.UserService;
//import com.projects.passwc.web.PasswdsController;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.security.test.context.support.WithUserDetails;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(PasswdsController.class)
//@ContextConfiguration(classes = PasswcApplication.class)
//@ExtendWith(SpringExtension.class)
//
//public class PasswdsControllerTest extends AbstractTestNGSpringContextTests {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserService userService;
//
//    @MockBean
//    private PasswdsService passwdsService;
//
//    @MockBean
//    private Authentication authentication;
//
//    @BeforeClass
//    public void setup() {
//        mockMvc = MockMvcBuilders.standaloneSetup(new PasswdsController(passwdsService)).build();
//    }
//
//    @Test
//    @WithUserDetails("testuser")
//    public void testPasswds() throws Exception {
//
//        Mockito.when(authentication.getName()).thenReturn("testuser");
//
//        mockMvc.perform(get("/passwds"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.passwdsList").exists());
//    }
//
//    @Test
//    @WithMockUser
//    public void testShowPasswordForm() throws Exception {
//        mockMvc.perform(get("/passwds/add"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("passwdForm"))
//                .andExpect(model().attributeExists("passwdForm"));
//    }
//
//    @Test
//    @WithMockUser
//    public void testGeneratePassword() throws Exception {
//        Mockito.when(passwdsService.generatePasswd(true, false, false, false, 10)).thenReturn("generatedPassword");
//
//        mockMvc.perform(get("/passwds/generatePassword")
//                        .param("useLower", "true")
//                        .param("useUpper", "false")
//                        .param("useDigits", "false")
//                        .param("usePunctuation", "false")
//                        .param("length", "10"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("generatedPassword"));
//    }
//
//    @Test
//    @WithMockUser(username = "testuser", password = "testuser")
//    public void testSavePasswd_Success() throws Exception {
//        PasswdsDTO passwdForm = new PasswdsDTO();
//        passwdForm.setResourceName("testResource");
//        passwdForm.setPasswd("testPasswd");
//
//        Mockito.when(authentication.getName()).thenReturn("testuser");
//
//        mockMvc.perform(post("/passwds/add")
//                        .flashAttr("passwdForm", passwdForm)
//                        .principal(authentication))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/passwds"));
//
//        Mockito.verify(passwdsService).save(passwdForm, "testuser");
//    }
//
//    @Test
//    @WithMockUser
//    public void testSavePasswd_Error() throws Exception {
//        PasswdsDTO passwdForm = new PasswdsDTO();
//        passwdForm.setResourceName("");
//        passwdForm.setPasswd("");
//
//        mockMvc.perform(post("/passwds/add")
//                        .flashAttr("passwdForm", passwdForm))
//                .andExpect(status().isOk())
//                .andExpect(view().name("passwdForm"))
//                .andExpect(model().attributeHasFieldErrors("passwdForm", "resourceName", "passwd"));
//    }
//}

import com.projects.passwc.DTO.PasswdsDTO;
import com.projects.passwc.DTO.UserRegisterDTO;
import com.projects.passwc.Entitys.User;
import com.projects.passwc.response.PasswdsResponse;
import com.projects.passwc.service.PasswdsService;
import com.projects.passwc.service.UserService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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
    public void testGetAllUserPasswds() throws Exception {
        PasswdsResponse response = new PasswdsResponse();
        response.setPageNumber(0);
        response.setPageSize(7);
        response.setTotalElements(10);
        response.setTotalPages(2);

        Mockito.when(passwdsService.getAllUserPasswds(0, setupUser().getUsername())).thenReturn(response);

        mockMvc.perform(get("/passwds?page=0")
                        .with(user(setupUser())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pageNumber").value(0))
                .andExpect(jsonPath("$.pageSize").value(7))
                .andExpect(jsonPath("$.totalElements").value(10))
                .andExpect(jsonPath("$.totalPages").value(2));
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

        mockMvc.perform(post("/passwds/add")
                        .with(user(setupUser()))
                        .flashAttr("passwdForm", passwdsDTO))
                .andExpect(status().is2xxSuccessful());
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/passwds"));

        Mockito.verify(passwdsService).save(passwdsDTO, "testuser");
    }
}