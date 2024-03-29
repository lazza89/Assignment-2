////////////////////////////////////////////////////////////////////
// [Nicola] [Lazzarin] [1204686]
// [Irene] [Benetazzo] [1223865]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;
import java.time.*;

import it.unipd.mtss.model.User;
import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.ProductType;

public class BillTest {
	BillImpl bill;
	User user;
	List<EItem> itemList;

	@Before
    public void initialize() {
    	bill = new BillImpl();
    	user = new User("Nicola", "Lazzarin", LocalDate.of(2010, 1, 1), "fakemail@gmail.com");
    	itemList = new ArrayList<EItem>(); 
    }
	
    //test per requisito 1
	@Test
    public void testItemSum() throws BillException {
		itemList.add(new EItem(ProductType.Keyboard, "keyboard microsoft", 12.50));
		itemList.add(new EItem(ProductType.Keyboard, "keyboard asus", 20));
		itemList.add(new EItem(ProductType.Motherboard, "motherboard asus", 77.8));
		itemList.add(new EItem(ProductType.Mouse, "mouse microsoft", 30));
		itemList.add(new EItem(ProductType.Processor, "i5 5600k", 355.8));
        
        assertEquals(496.1, bill.getOrderPrice(itemList, user, LocalTime.of(10, 00)), 1e-8);
    }
	
	@Test
    public void testNullItemsList() throws BillException {
        itemList = null;
        
        try {
            bill.getOrderPrice(itemList, user, LocalTime.of(10, 00));
        }catch(BillException e) {
            assertEquals("La lista itemList è uguale a null", e.getMessage());
        }
    }
	
	@Test
    public void testNullItemInItemsList() {
		itemList.add(new EItem(ProductType.Keyboard, "keyboard microsoft", 12.50));
		itemList.add(null);
		itemList.add(new EItem(ProductType.Motherboard, "motherboard asus", 77.8));
        
        try {
            bill.getOrderPrice(itemList, user, LocalTime.of(10, 00));
        }catch(BillException e){
            assertEquals("La lista itemList contiene un item uguale a null", e.getMessage());
        }
    }
	
    @Test
    public void testNullUser() {
		itemList.add(new EItem(ProductType.Keyboard, "keyboard microsoft", 12.50));
        
        try {
            bill.getOrderPrice(itemList, null, LocalTime.of(10, 00));
        }catch(BillException e){
            assertEquals("utente è uguale a null", e.getMessage());
        }
    }

    //test per requisito 2
    @Test
    public void testMoreThan5ProcessoriDiscount() throws BillException {
		itemList.add(new EItem(ProductType.Processor, "i3", 150.4));
		itemList.add(new EItem(ProductType.Processor, "pentium 4", 33.3));
		itemList.add(new EItem(ProductType.Processor, "i5", 140.5));
		itemList.add(new EItem(ProductType.Processor, "i7", 300));
		itemList.add(new EItem(ProductType.Processor, "i9", 230.9));
		itemList.add(new EItem(ProductType.Processor, "amd", 132.7));
        
        assertEquals(971.15, bill.getOrderPrice(itemList, user, LocalTime.of(10, 00)), 1e-8);
    }
    
    //test per requisito 3
    @Test
    public void testMoreThan10MouseGift() throws BillException {
		itemList.add(new EItem(ProductType.Mouse, "mouse microsoft", 30));
		itemList.add(new EItem(ProductType.Mouse, "mouse cinese", 22.3));
		itemList.add(new EItem(ProductType.Mouse, "mouse boh", 11.2));
		itemList.add(new EItem(ProductType.Mouse, "mouse bello potente", 72.9));
		itemList.add(new EItem(ProductType.Mouse, "mouse microsoft tarocco", 10));
		itemList.add(new EItem(ProductType.Mouse, "mouse microsoft", 30));
		itemList.add(new EItem(ProductType.Mouse, "mouse cheap", 6));
		itemList.add(new EItem(ProductType.Mouse, "mouse microsoft", 30));
		itemList.add(new EItem(ProductType.Mouse, "mouse microsoft", 30));
		itemList.add(new EItem(ProductType.Mouse, "mouse microsoft", 30));
		itemList.add(new EItem(ProductType.Mouse, "mouse microsoft", 30));

        assertEquals(296.4, bill.getOrderPrice(itemList, user, LocalTime.of(10, 00)), 1e-8);
    }

    //test per requisito 4
    //se l'articolo meno caro risulta sempre il mouse allora viene fatto il doppio sconto nel totale
    @Test
    public void testMoreThan10MouseWithNMouseAndKeyboardEqualCheapMouse() throws BillException {
        itemList.add(new EItem(ProductType.Mouse, "mouse microsoft", 30));
        itemList.add(new EItem(ProductType.Mouse, "mouse cinese", 22.3));
        itemList.add(new EItem(ProductType.Mouse, "mouse boh", 11.2));
        itemList.add(new EItem(ProductType.Mouse, "mouse bello potente", 72.9));
        itemList.add(new EItem(ProductType.Mouse, "mouse microsoft tarocco", 10));
        itemList.add(new EItem(ProductType.Mouse, "mouse microsoft", 30));
        itemList.add(new EItem(ProductType.Mouse, "mouse cheap", 6));
        itemList.add(new EItem(ProductType.Mouse, "mouse microsoft", 30));
        itemList.add(new EItem(ProductType.Mouse, "mouse microsoft", 30));
        itemList.add(new EItem(ProductType.Mouse, "mouse microsoft", 30));
        itemList.add(new EItem(ProductType.Mouse, "mouse microsoft", 30));
        
        itemList.add(new EItem(ProductType.Keyboard, "keyboard microsoft", 12.5));
        itemList.add(new EItem(ProductType.Keyboard, "keyboard asus", 20));
        itemList.add(new EItem(ProductType.Keyboard, "keyboard microsoft", 12.5));
        itemList.add(new EItem(ProductType.Keyboard, "keyboard asus", 20));
        itemList.add(new EItem(ProductType.Keyboard, "keyboard microsoft", 12.5));
        itemList.add(new EItem(ProductType.Keyboard, "keyboard asus", 20));
        itemList.add(new EItem(ProductType.Keyboard, "keyboard microsoft", 12.5));
        itemList.add(new EItem(ProductType.Keyboard, "keyboard asus", 20));
        itemList.add(new EItem(ProductType.Keyboard, "keyboard microsoft", 12.5));
        itemList.add(new EItem(ProductType.Keyboard, "keyboard asus", 20));
        itemList.add(new EItem(ProductType.Keyboard, "keyboard microsoft", 12.5));
        
        assertEquals(465.4, bill.getOrderPrice(itemList, user, LocalTime.of(10, 00)), 1e-8);
    }
    
    //test per requisito 4
    @Test
    public void testMoreThan10MouseWithNMouseAndKeyboardEqualCheapKeyboard() throws BillException {
        
        itemList.add(new EItem(ProductType.Mouse, "mouse microsoft", 30));
        itemList.add(new EItem(ProductType.Mouse, "mouse cinese", 22.3));
        itemList.add(new EItem(ProductType.Mouse, "mouse boh", 11.2));
        itemList.add(new EItem(ProductType.Mouse, "mouse bello potente", 72.9));
        itemList.add(new EItem(ProductType.Mouse, "mouse microsoft tarocco", 10));
        itemList.add(new EItem(ProductType.Mouse, "mouse microsoft", 30));
        itemList.add(new EItem(ProductType.Mouse, "mouse cheap", 10));
        itemList.add(new EItem(ProductType.Mouse, "mouse microsoft", 30));
        itemList.add(new EItem(ProductType.Mouse, "mouse microsoft", 30));
        itemList.add(new EItem(ProductType.Mouse, "mouse microsoft", 30));
        itemList.add(new EItem(ProductType.Mouse, "mouse microsoft", 30));
        
        itemList.add(new EItem(ProductType.Keyboard, "keyboard cheap", 6));
        itemList.add(new EItem(ProductType.Keyboard, "keyboard asus", 20));
        itemList.add(new EItem(ProductType.Keyboard, "keyboard microsoft", 12.5));
        itemList.add(new EItem(ProductType.Keyboard, "keyboard asus", 20));
        itemList.add(new EItem(ProductType.Keyboard, "keyboard microsoft", 12.5));
        itemList.add(new EItem(ProductType.Keyboard, "keyboard asus", 20));
        itemList.add(new EItem(ProductType.Keyboard, "keyboard microsoft", 12.5));
        itemList.add(new EItem(ProductType.Keyboard, "keyboard asus", 20));
        itemList.add(new EItem(ProductType.Keyboard, "keyboard microsoft", 12.5));
        itemList.add(new EItem(ProductType.Keyboard, "keyboard asus", 20));
        itemList.add(new EItem(ProductType.Keyboard, "keyboard microsoft", 12.5));
        
        assertEquals(458.9, bill.getOrderPrice(itemList, user, LocalTime.of(10, 00)), 1e-8);
    }
    
    //test per requisito 4
    @Test
    public void testEqualsMouseAndKeyboardDiscountCheapMouse() throws BillException {
		itemList.add(new EItem(ProductType.Keyboard, "keyboard microsoft", 12.5));
		itemList.add(new EItem(ProductType.Keyboard, "keyboard asus", 20));
		itemList.add(new EItem(ProductType.Mouse, "mouse cheap", 2.9));
		itemList.add(new EItem(ProductType.Mouse, "mouse microsoft", 30));
		
        assertEquals(62.5, bill.getOrderPrice(itemList, user, LocalTime.of(10, 00)), 1e-8);
    }
    
    //test per requisito 4
    @Test
    public void testEqualsMouseAndKeyboardDiscountCheapKeyboard() throws BillException {
        itemList.add(new EItem(ProductType.Keyboard, "keyboard cheap", 10));
        itemList.add(new EItem(ProductType.Keyboard, "keyboard asus", 20));
        itemList.add(new EItem(ProductType.Mouse, "mouse bello", 12.5));
        itemList.add(new EItem(ProductType.Mouse, "mouse microsoft", 30));
        
        assertEquals(62.5, bill.getOrderPrice(itemList, user, LocalTime.of(10, 00)), 1e-8);
    }

    //test per requisito 5
    @Test
    public void testTotalMoreThan1000Euro() throws BillException{
		itemList.add(new EItem(ProductType.Processor, "i3", 150.4));
		itemList.add(new EItem(ProductType.Keyboard, "marca sconosciuta molto costosa", 400.9));
		itemList.add(new EItem(ProductType.Processor, "i5", 140.5));
		itemList.add(new EItem(ProductType.Mouse, "mouse cinese scammato", 222.4));
		itemList.add(new EItem(ProductType.Mouse, "mouse cinese scammato p2", 300));
		itemList.add(new EItem(ProductType.Motherboard, "asus", 428.9));
		itemList.add(new EItem(ProductType.Processor, "amd", 132.7));
		
        assertEquals(1598.22, bill.getOrderPrice(itemList, user, LocalTime.of(10, 00)), 1e-8);
    }

    //test per requisito 6
    @Test
    public void test30ItemsLimit() {
        for(int i=0; i<31; i++) {
        	itemList.add(new EItem(ProductType.Mouse, "mouse microsoft", 30));
        }
        
        try {
            bill.getOrderPrice(itemList, user, LocalTime.of(10, 00));
        }catch(BillException e){
            assertEquals("Ci sono più di 30 items nella lista itemList", e.getMessage());
        }
    }

    //test per requisito 7
    @Test
    public void testUnder10EuroCommission() throws BillException {
        itemList.add(new EItem(ProductType.Mouse, "mouse cheap", 5));
        
        assertEquals(7, bill.getOrderPrice(itemList, user, LocalTime.of(10, 00)), 1e-8);
    }
    
    //test per requisito 8
    @Test
    public void testFreeOrder() throws BillException {
        itemList.add(new EItem(ProductType.Processor, "i5", 140.5));
        itemList.add(new EItem(ProductType.Processor, "i7", 300));
        itemList.add(new EItem(ProductType.Processor, "i9", 230.9));

        bill.giveaway.r.setSeed(2);
        assertEquals(0, bill.getOrderPrice(itemList, user, LocalTime.of(18, 30)), 1e-8);
    }
}
