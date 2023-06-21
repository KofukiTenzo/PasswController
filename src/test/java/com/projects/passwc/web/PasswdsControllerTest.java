package com.projects.passwc.web;

import com.projects.passwc.DAO.Passwds;
import com.projects.passwc.data.PasswdsRepository;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class PasswdsControllerTest {

    @Test
    public void shouldShowRecentPasswds() throws Exception {
        List<Passwds> expectedPasswds = createPasswdList(20);

        PasswdsRepository mockRepository = mock(PasswdsRepository.class);
//        when(mockRepository.findPasswds(Long.MAX_VALUE, 20))
//                .thenReturn(expectedPasswds);

        PasswdsController controller = new PasswdsController(mockRepository);

        MockMvc mockMvc = standaloneSetup(controller)
                .setSingleView(
                        new InternalResourceView("/WEB-INF/views/passwds.jsp"))
                .build();

        mockMvc.perform(get("/passwds"))
                .andExpect(view().name("passwds"))
                .andExpect(model().attributeExists("passwdList"))
                .andExpect(model().attribute("passwdList",
                        hasItems(expectedPasswds.toArray())));
    }

    @Test
    public void shouldShowPagedPasswds() throws Exception {
        List<Passwds> expectedPasswds = createPasswdList(50);

        PasswdsRepository mockRepository = mock(PasswdsRepository.class);
//        when(mockRepository.findPasswds(238900, 50))
//                .thenReturn(expectedPasswds);

        PasswdsController controller = new PasswdsController(mockRepository);

        MockMvc mockMvc = standaloneSetup(controller)
                .setSingleView(
                        new InternalResourceView("/WEB-INF/views/passwds.jsp"))
                .build();

        mockMvc.perform(get("/passwds?max=238900&count=50"))
                .andExpect(view().name("passwds"))
                .andExpect(model().attributeExists("passwdList"))
                .andExpect(model().attribute("passwdList",
                        hasItems(expectedPasswds.toArray())));
    }

    @Test
    public void testPasswd() throws Exception {
        Passwds expectedPasswd = new Passwds("Googel", "Qwerty123");

        PasswdsRepository mockRepository = mock(PasswdsRepository.class);
        when(mockRepository.findOne(12345))
                .thenReturn(expectedPasswd);

        PasswdsController controller = new PasswdsController(mockRepository);

        MockMvc mockMvc = standaloneSetup(controller).build();

        mockMvc.perform(get("/passwds/12345"))
                .andExpect(view().name("passwd"))
                .andExpect(model().attributeExists("passwd"))
                .andExpect(model().attribute("passwd", expectedPasswd));
    }

    private List<Passwds> createPasswdList(int count) {
        List<Passwds> passwds = new ArrayList<Passwds>();

        for (int i = 0; i < count; i++) {
            passwds.add(new Passwds("GitHub", "new pass " + i));
        }
        return passwds;
    }
}
