////////////////////////////////////////////////////////////////////
// [Nicola] [Lazzarin] [1204686]
// [Irene] [Benetazzo] [1223865]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import java.util.List;
import java.time.LocalTime;
import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.ProductType;
import it.unipd.mtss.model.User;

public class BillImpl implements Bill {
    
    public double getOrderPrice(List<EItem> itemList, User user, LocalTime orderTime) throws BillException {
        double total = 0.0d;   
       
        for(EItem item: itemList) {
            total = total + item.getPrice();
        }
        
        if(total > 1000) {
            total = total * 0.9;
        }
        return total;
    }
}
