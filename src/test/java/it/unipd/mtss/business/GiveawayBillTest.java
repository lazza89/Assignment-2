////////////////////////////////////////////////////////////////////
// [Nicola] [Lazzarin] [1204686]
// [Irene] [Benetazzo] [1223865]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import it.unipd.mtss.model.User;

public class GiveawayBillTest {

	User user;
    GiveawayBillImpl giveaway;
    
    @Before
    public void initialize() {
        user = new User("Nicola", "Lazzarin", LocalDate.of(2009, 1, 1), "fakemail@gmail.com");
        giveaway = new GiveawayBillImpl(); 
    }

    @Test
    public void testEligibleOrder() {
        assertEquals(true, giveaway.isOrderEligible(user, LocalTime.of(18, 00)));
    }
    
    @Test
    public void testUserOver18() {
        user = new User("Nicola", "Lazzarin", LocalDate.of(1998, 1, 1), "fakemail@gmail.com");
        assertEquals(false, giveaway.isOrderEligible(user, LocalTime.of(18, 00)));
    }
    
    @Test
    public void testWrongTimeframe() {
        assertEquals(false, giveaway.isOrderEligible(user, LocalTime.of(15, 30)));
    }
    
    @Test
    public void testUserAlreadySelected() {
        giveaway.selectedUsers.add(user);
        assertEquals(false, giveaway.isOrderEligible(user, LocalTime.of(18, 00)));
    }
    
    @Test
    public void testMoreThan10UsersSelected() {
        for(int i=1; i<10; i++) {
            user = new User("Nicola", "Lazzarin", LocalDate.of(1998, 1, i), "fakemail@gmail.com");
            giveaway.selectedUsers.add(user);
        }
        user = new User("Nicola", "Lazzarin", LocalDate.of(1998, 1, 25), "fakemail@gmail.com");
        assertEquals(false, giveaway.isOrderEligible(user, LocalTime.of(18, 00)));
    }
    
    @Test
    public void testNullUser() {
        user = null;
        try {
            giveaway.giveAwayOrder(null, LocalTime.of(18, 00));
        }catch(IllegalArgumentException e) {
            assertEquals("user è null", e.getMessage());
        }
    }
    
    @Test
    public void testNullOrderTime() {
        try {
            giveaway.giveAwayOrder(user, null);
        }catch(IllegalArgumentException e) {
            assertEquals("orderTime è null", e.getMessage());
        }
    }
}
