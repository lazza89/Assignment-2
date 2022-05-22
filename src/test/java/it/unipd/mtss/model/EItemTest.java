////////////////////////////////////////////////////////////////////
// [Nicola] [Lazzarin] [1204686]
// [Irene] [Benetazzo] [1223865]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class EItemTest {

	EItem item;
    
    @Before
    public void initialize() {
        item = new EItem(ProductType.Mouse, "mouse microsoft", 10);
    }
    
    @Test
    public void testGetItemType() {
        assertEquals(ProductType.Mouse, item.getItemType());
    }

    @Test
    public void testGetName() {
        assertEquals("mouse microsoft", item.getName());
    }

    @Test
    public void testGetPrice() {
        assertEquals(10, item.getPrice(), 1e-8);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetNegativePrice() {
        new EItem(ProductType.Mouse, "mouse microsoft", -10);
    }

}
