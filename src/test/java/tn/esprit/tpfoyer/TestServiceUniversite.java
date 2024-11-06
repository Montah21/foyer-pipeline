package tn.esprit.tpfoyer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.repository.UniversiteRepository;
import tn.esprit.tpfoyer.service.UniversiteServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TestServiceUniversite {

    @Mock
    private UniversiteRepository universiteRepository; // Mock the repository

    @InjectMocks
    private UniversiteServiceImpl universiteService; // Service under test

    private Universite universite;

    @BeforeEach
    void setUp() {
        // Initialize the object to be tested
        universite = new Universite();
        universite.setIdUniversite(1L);
        universite.setNomUniversite("Test University");
    }

    @Test
    public void testAddUniversite() {
        // Mock the save method of the repository
        when(universiteRepository.save(any(Universite.class))).thenReturn(universite);

        // Call the service method
        Universite addedUniversite = universiteService.addUniversite(universite);

        // Assert that the returned Universite is not null and has the expected name
        assertNotNull(addedUniversite);
        assertEquals("Test University", addedUniversite.getNomUniversite());

        // Verify that the repository's save method was called once with the given universite
        verify(universiteRepository, times(1)).save(universite);
    }
}
