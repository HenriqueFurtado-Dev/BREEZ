package br.com.breez.breez.service;

import br.com.breez.breez.model.EmergencyContact;
import br.com.breez.breez.repository.EmergencyContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmergencyServiceTest {

    @Mock
    private EmergencyContactRepository repo;

    private EmergencyService emergencyService;

    @BeforeEach
    void setUp() {
        emergencyService = new EmergencyService(repo);
    }

    @Test
    void getAllContacts_ShouldReturnListOfContacts() {
        // Arrange
        EmergencyContact contact1 = new EmergencyContact();
        contact1.setName("SAMU");
        contact1.setPhoneNumber("192");

        EmergencyContact contact2 = new EmergencyContact();
        contact2.setName("Bombeiros");
        contact2.setPhoneNumber("193");

        when(repo.findAll()).thenReturn(Arrays.asList(contact1, contact2));

        // Act
        List<EmergencyContact> result = emergencyService.getAllContacts();

        // Assert
        assertEquals(2, result.size());
        assertEquals("SAMU", result.get(0).getName());
        assertEquals("192", result.get(0).getPhoneNumber());
        assertEquals("Bombeiros", result.get(1).getName());
        assertEquals("193", result.get(1).getPhoneNumber());
    }

    @Test
    void getQuickGuide_ShouldReturnListOfInstructions() {
        // Act
        List<String> guide = emergencyService.getQuickGuide();

        // Assert
        assertEquals(5, guide.size());
        assertTrue(guide.contains("Hidrate-se imediatamente com água ou soro."));
        assertTrue(guide.contains("Procure sombra ou ambiente fresco."));
    }

    @Test
    void getEmergencyNumber_ShouldReturnSAMUNumber() {
        // Act
        String number = emergencyService.getEmergencyNumber();

        // Assert
        assertEquals("192", number);
    }
} 