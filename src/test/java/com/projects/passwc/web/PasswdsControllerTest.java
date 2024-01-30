package com.projects.passwc.web;

import com.projects.passwc.DAO.Passwds;
import com.projects.passwc.data.PasswdsRepository;
import com.projects.passwc.DTO.PasswdForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.security.Principal;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PasswdsControllerTest {

//    @Test
//    public void shouldShowRecentPasswds() throws Exception {
//        List<Passwds> expectedPasswds = createPasswdList(20);
//
//        PasswdsRepository mockRepository = mock(PasswdsRepository.class);
////        when(mockRepository.findPasswds(Long.MAX_VALUE, 20))
////                .thenReturn(expectedPasswds);
//
//        PasswdsController controller = new PasswdsController(mockRepository);
//
//        MockMvc mockMvc = standaloneSetup(controller)
//                .setSingleView(
//                        new InternalResourceView("/WEB-INF/views/passwds.jsp"))
//                .build();
//
//        mockMvc.perform(get("/passwds"))
//                .andExpect(view().name("passwds"))
//                .andExpect(model().attributeExists("passwdList"))
//                .andExpect(model().attribute("passwdList",
//                        hasItems(expectedPasswds.toArray())));
//    }

//    @Test
//    public void shouldShowPagedPasswds() throws Exception {
//        List<Passwds> expectedPasswds = createPasswdList(50);
//
//        PasswdsRepository mockRepository = mock(PasswdsRepository.class);
////        when(mockRepository.findPasswds(238900, 50))
////                .thenReturn(expectedPasswds);
//
//        PasswdsController controller = new PasswdsController(mockRepository);
//
//        MockMvc mockMvc = standaloneSetup(controller)
//                .setSingleView(
//                        new InternalResourceView("/WEB-INF/views/passwds.jsp"))
//                .build();
//
//        mockMvc.perform(get("/passwds?max=238900&count=50"))
//                .andExpect(view().name("passwds"))
//                .andExpect(model().attributeExists("passwdList"))
//                .andExpect(model().attribute("passwdList",
//                        hasItems(expectedPasswds.toArray())));
//    }

//    @Test
//    public void testPasswd() throws Exception {
//        Passwds expectedPasswd = new Passwds("Googel", "Qwerty123");
//
//        PasswdsRepository mockRepository = mock(PasswdsRepository.class);
//        when(mockRepository.findOne(12345))
//                .thenReturn(expectedPasswd);
//
//        PasswdsController controller = new PasswdsController(mockRepository);
//
//        MockMvc mockMvc = standaloneSetup(controller).build();
//
//        mockMvc.perform(get("/passwds/12345"))
//                .andExpect(view().name("passwd"))
//                .andExpect(model().attributeExists("passwd"))
//                .andExpect(model().attribute("passwd", expectedPasswd));
//    }
//
//    private List<Passwds> createPasswdList(int count) {
//        List<Passwds> passwds = new ArrayList<Passwds>();
//
//        for (int i = 0; i < count; i++) {
//            passwds.add(new Passwds("GitHub", "new pass " + i));
//        }
//        return passwds;
//    }


    @Mock
    private PasswdsRepository passwdsRepository;

    @InjectMocks
    private PasswdsController passwdsController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(passwdsController).build();
    }

    @Test
    public void showPasswordForm_ShouldRenderPasswdFormView() throws Exception {
        mockMvc.perform(get("/passwds/addPasswd"))
                .andExpect(status().isOk())
                .andExpect(view().name("passwdForm"))
                .andExpect(model().attributeExists("passwdForm"));
    }

    @Test
    public void generatePassword_ShouldReturnGeneratedPassword() throws Exception {
        String generatedPassword = "Abc123@";
        PasswdForm passwdForm = new PasswdForm();

        when(passwdsRepository.findAllUserPasswds(any())).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/passwds/generatePassword")
                        .param("useLower", "true")
                        .param("useUpper", "true")
                        .param("useDigits", "true")
                        .param("usePunctuation", "true")
                        .param("length", "8")
                        .flashAttr("passwdForm", passwdForm))
                .andExpect(status().isOk())
                .andExpect(content().string(generatedPassword));
    }

    @Test
    public void savePasswd_ShouldRedirectToPasswds() throws Exception {
        PasswdForm passwdForm = new PasswdForm();
        Principal principal = () -> "user1";

        when(passwdsRepository.save(any())).thenReturn(new Passwds());

        mockMvc.perform(post("/passwds/addPasswd")
                        .flashAttr("passwdForm", passwdForm)
                        .principal(principal))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/passwds"));
    }

    @Test
    public void savePasswd_WithErrors_ShouldRenderPasswdFormView() throws Exception {
        PasswdForm passwdForm = new PasswdForm();
        passwdForm.setResourceName("Test Resource"); // Add some valid data

        mockMvc.perform(post("/passwds/addPasswd")
                        .flashAttr("passwdForm", passwdForm))
                .andExpect(status().isOk())
                .andExpect(view().name("passwdForm"));
    }
}
