package tn.esprit.tpfoyer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.repository.FoyerRepository;
import tn.esprit.tpfoyer.service.FoyerServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TestServiceFoyer {

    @Mock
    private FoyerRepository foyerRepository; // Mocking the repository

    @InjectMocks
    private FoyerServiceImpl foyerService; // Injecting the mocked repository into the service

    private Foyer foyer;

    @BeforeEach
    void setUp() {
        // Initialize a sample Foyer object
        foyer = new Foyer();
        foyer.setIdFoyer(1L);
        foyer.setNomFoyer("Foyer 1");
    }

    // Test for addFoyer method
    @Test
    public void testAddFoyer() {
        // Mocking the save method to return the foyer object
        when(foyerRepository.save(any(Foyer.class))).thenReturn(foyer);

        // Calling the service method
        Foyer addedFoyer = foyerService.addFoyer(foyer);

        // Assertions
        assertNotNull(addedFoyer);
        assertEquals("Foyer 1", addedFoyer.getNomFoyer());
        verify(foyerRepository, times(1)).save(foyer); // Verifying if save method was called once
    }

    // Test for modifyFoyer method
    @Test
    public void testModifyFoyer() {
        // Mocking the save method to return the modified foyer object
        when(foyerRepository.save(any(Foyer.class))).thenReturn(foyer);

        // Modifying the foyer object
        foyer.setNomFoyer("Updated Foyer");

        // Calling the service method
        Foyer updatedFoyer = foyerService.modifyFoyer(foyer);

        // Assertions
        assertNotNull(updatedFoyer);
        assertEquals("Updated Foyer", updatedFoyer.getNomFoyer());
        verify(foyerRepository, times(1)).save(foyer); // Verifying if save method was called once
    }

    // Test for retrieveFoyer method
    @Test
    public void testRetrieveFoyer() {
        // Mocking the repository to return an Optional of Foyer
        when(foyerRepository.findById(1L)).thenReturn(Optional.of(foyer));

        // Calling the service method
        Foyer retrievedFoyer = foyerService.retrieveFoyer(1L);

        // Assertions
        assertNotNull(retrievedFoyer);
        assertEquals("Foyer 1", retrievedFoyer.getNomFoyer());
        verify(foyerRepository, times(1)).findById(1L); // Verifying if findById was called once
    }

    // Test for retrieveAllFoyers method
    @Test
    public void testRetrieveAllFoyers() {
        // Mocking the repository to return a list of foyers
        List<Foyer> foyers = new ArrayList<>();
        foyers.add(foyer);
        when(foyerRepository.findAll()).thenReturn(foyers);

        // Calling the service method
        List<Foyer> allFoyers = foyerService.retrieveAllFoyers();

        // Assertions
        assertNotNull(allFoyers);
        assertEquals(1, allFoyers.size());
        assertEquals("Foyer 1", allFoyers.get(0).getNomFoyer());
        verify(foyerRepository, times(1)).findAll(); // Verifying if findAll was called once
    }

    // Test for removeFoyer method
    @Test
    public void testRemoveFoyer() {
        // Calling the service method
        foyerService.removeFoyer(1L);

        // Verifying if the deleteById method was called once with the correct ID
        verify(foyerRepository, times(1)).deleteById(1L);
    }
}
