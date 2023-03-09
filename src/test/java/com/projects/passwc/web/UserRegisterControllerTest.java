package com.projects.passwc.web;

import com.projects.passwc.data.UserRegisterRepository;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class UserRegisterControllerTest {
    @Test
    public void shouldShowRegistration() throws Exception{
        UserRegisterRepository mockRepository = mock(UserRegisterRepository.class);

        UserRegisterController controller = new UserRegisterController(mockRepository);

        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(get("/user/register"))
                .andExpect(view().name("registerForm"));
    }

//    @Test
//    public void shouldProcessRegistration() throws Exception{
//        PasswcUserRepository mockRepository = mock(PasswcUserRepository.class);
//
//        PasswcUser unsaved = new PasswcUser("admin", "qwerty");
//        PasswcUser saved = new PasswcUser(24L, "admin", "qwerty");
//        when(mockRepository.save(unsaved)).thenReturn(saved);
//
//        PasswcUserController controller = new PasswcUserController(mockRepository);
//        MockMvc mockMvc = standaloneSetup(controller).build();
//
//        (mockMvc.perform(post("/user/register")))
//                .param("username", "admin")
//                .param("password", "qwerty")
//                .andExpect(redirectedUrl("/user/admin"));
//
//        verify(mockRepository, atLeastOnce()).save(unsaved);
//    }

}
