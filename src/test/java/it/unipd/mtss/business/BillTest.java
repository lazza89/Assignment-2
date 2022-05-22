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
}
