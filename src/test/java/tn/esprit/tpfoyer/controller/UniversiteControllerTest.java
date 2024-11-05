package tn.esprit.tpfoyer.controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.tpfoyer.control.UniversiteRestController;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.service.IUniversiteService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UniversiteControllerTest {
    private MockMvc mockMvc;

    @Mock
    private IUniversiteService universiteService;

    @InjectMocks
    private UniversiteRestController universiteController;

    private Universite universite;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(universiteController).build();
        universite = new Universite(1L, "University ABC", "123 Street", null);
    }

    @Test
    void testCreateUniversite() throws Exception {
        when(universiteService.addUniversite(any(Universite.class))).thenReturn(universite);

        mockMvc.perform(post("/universite")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nomUniversite\":\"University ABC\",\"adresse\":\"123 Street\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nomUniversite").value("University ABC"))
                .andExpect(jsonPath("$.adresse").value("123 Street"));

        verify(universiteService, times(1)).addUniversite(any(Universite.class));
    }

    @Test
    void testGetUniversiteById() throws Exception {
        when(universiteService.retrieveUniversite(1L)).thenReturn(universite);

        mockMvc.perform(get("/universite/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomUniversite").value("University ABC"))
                .andExpect(jsonPath("$.adresse").value("123 Street"));

        verify(universiteService, times(1)).retrieveUniversite(1L);
    }

    @Test
    void testDeleteUniversite() throws Exception {
        doNothing().when(universiteService).removeUniversite(1L);

        mockMvc.perform(delete("/universite/1"))
                .andExpect(status().isNoContent());

        verify(universiteService, times(1)).removeUniversite(1L);
    }
}
