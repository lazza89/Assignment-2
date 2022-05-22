////////////////////////////////////////////////////////////////////
// [Nicola] [Lazzarin] [1204686]
// [Irene] [Benetazzo] [1223865]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

public class UserTest {

	User user;

    @Before
    public void initialize() {
        user = new User("Nicola", "Lazzarin", LocalDate.of(1998, 5, 2), "emailfake@gmail.com");
    }

    @Test
    public void testGetName() {
        assertEquals("Nicola", user.getName());
    }
    
    @Test
    public void testGetSurname() {
        assertEquals("Lazzarin", user.getSurname());
    }

    @Test
    public void testGetEmail() {
        assertEquals("emailfake@gmail.com", user.getEmail());
    }

    @Test
    public void testGetDateOfBirth() {
        assertEquals(LocalDate.of(1998, 5, 2), user.getDateOfBirth());
    }
    
    @Test
    public void testIllegalDateOfBirth() {
        try {
            new User("Nicola", "Lazzarin", LocalDate.now().plusDays(20), "fakemail@gmail.com");
        }catch (IllegalArgumentException e) {
            assertEquals("La data di nascita non può essere maggiore di quella attuale", e.getMessage());
        }
}
    
    @Test
    public void testNullDateOfBirth() {
        try {
            new User("Nicola", "Lazzarin", null, "fakemail@gmail.com");
        }catch(IllegalArgumentException e) {
            assertEquals("La data di nascita è null", e.getMessage());
        }
        
    }
}
