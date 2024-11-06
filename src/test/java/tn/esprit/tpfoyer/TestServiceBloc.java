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
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.repository.BlocRepository;
import tn.esprit.tpfoyer.service.BlocServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TestServiceBloc {

    @Mock
    private BlocRepository blocRepository; // Mocking the repository

    @InjectMocks
    private BlocServiceImpl blocService; // Injecting the mocked repository into the service

    private Bloc bloc;

    @BeforeEach
    void setUp() {
        // Initialize a sample Bloc object
        bloc = new Bloc();
        bloc.setIdBloc(1L);
        bloc.setNomBloc("Bloc 1");
        bloc.setCapaciteBloc(100);
    }

    // Test for addBloc method
    @Test
    public void testAddBloc() {
        // Mocking the save method to return the bloc object
        when(blocRepository.save(any(Bloc.class))).thenReturn(bloc);

        // Calling the service method
        Bloc addedBloc = blocService.addBloc(bloc);

        // Assertions
        assertNotNull(addedBloc);
        assertEquals("Bloc 1", addedBloc.getNomBloc());
        assertEquals(100, addedBloc.getCapaciteBloc());
        verify(blocRepository, times(1)).save(bloc); // Verifying if save method was called once
    }

    // Test for modifyBloc method
    @Test
    public void testModifyBloc() {
        // Mocking the save method to return the modified bloc object
        when(blocRepository.save(any(Bloc.class))).thenReturn(bloc);

        // Modifying the bloc object
        bloc.setNomBloc("Updated Bloc");

        // Calling the service method
        Bloc updatedBloc = blocService.modifyBloc(bloc);

        // Assertions
        assertNotNull(updatedBloc);
        assertEquals("Updated Bloc", updatedBloc.getNomBloc());
        verify(blocRepository, times(1)).save(bloc); // Verifying if save method was called once
    }

    // Test for retrieveBloc method
    @Test
    public void testRetrieveBloc() {
        // Mocking the repository to return an Optional of Bloc
        when(blocRepository.findById(1L)).thenReturn(Optional.of(bloc));

        // Calling the service method
        Bloc retrievedBloc = blocService.retrieveBloc(1L);

        // Assertions
        assertNotNull(retrievedBloc);
        assertEquals("Bloc 1", retrievedBloc.getNomBloc());
        verify(blocRepository, times(1)).findById(1L); // Verifying if findById was called once
    }

    // Test for retrieveAllBlocs method
    @Test
    public void testRetrieveAllBlocs() {
        // Mocking the repository to return a list of blocs
        List<Bloc> blocs = new ArrayList<>();
        blocs.add(bloc);
        when(blocRepository.findAll()).thenReturn(blocs);

        // Calling the service method
        List<Bloc> allBlocs = blocService.retrieveAllBlocs();

        // Assertions
        assertNotNull(allBlocs);
        assertEquals(1, allBlocs.size());
        assertEquals("Bloc 1", allBlocs.get(0).getNomBloc());
        verify(blocRepository, times(1)).findAll(); // Verifying if findAll was called once
    }

    // Test for retrieveBlocsSelonCapacite method
    @Test
    public void testRetrieveBlocsSelonCapacite() {
        // Mocking the repository to return a list of blocs
        List<Bloc> blocs = new ArrayList<>();
        blocs.add(bloc);
        when(blocRepository.findAll()).thenReturn(blocs);

        // Calling the service method
        List<Bloc> blocsSelonCapacite = blocService.retrieveBlocsSelonCapacite(50L);

        // Assertions
        assertNotNull(blocsSelonCapacite);
        assertEquals(1, blocsSelonCapacite.size());
        assertEquals("Bloc 1", blocsSelonCapacite.get(0).getNomBloc());
        verify(blocRepository, times(1)).findAll(); // Verifying if findAll was called once
    }

    // Test for trouverBlocsSansFoyer method
    @Test
    public void testTrouverBlocsSansFoyer() {
        // Mocking the repository to return a list of blocs without foyer
        List<Bloc> blocs = new ArrayList<>();
        blocs.add(bloc);
        when(blocRepository.findAllByFoyerIsNull()).thenReturn(blocs);

        // Calling the service method
        List<Bloc> blocsSansFoyer = blocService.trouverBlocsSansFoyer();

        // Assertions
        assertNotNull(blocsSansFoyer);
        assertEquals(1, blocsSansFoyer.size());
        verify(blocRepository, times(1)).findAllByFoyerIsNull(); // Verifying if the method was called
    }

    // Test for trouverBlocsParNomEtCap method
    @Test
    public void testTrouverBlocsParNomEtCap() {
        // Mocking the repository to return a list of blocs matching name and capacite
        List<Bloc> blocs = new ArrayList<>();
        blocs.add(bloc);
        when(blocRepository.findAllByNomBlocAndCapaciteBloc(anyString(), anyLong())).thenReturn(blocs);

        // Calling the service method
        List<Bloc> blocsByNameAndCap = blocService.trouverBlocsParNomEtCap("Bloc 1", 100L);

        // Assertions
        assertNotNull(blocsByNameAndCap);
        assertEquals(1, blocsByNameAndCap.size());
        verify(blocRepository, times(1)).findAllByNomBlocAndCapaciteBloc("Bloc 1", 100L); // Verifying if the method was called
    }

    // Test for removeBloc method
    @Test
    public void testRemoveBloc() {
        // Calling the service method
        blocService.removeBloc(1L);

        // Verifying if the deleteById method was called once with the correct ID
        verify(blocRepository, times(1)).deleteById(1L);
    }
}
