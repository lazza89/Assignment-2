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
    
    private void checkNull(List<EItem> itemList, User user) throws BillException {
        if(itemList == null) {
            throw new BillException("La lista itemList è uguale a null");
        }
        if(itemList.contains(null)) {
            throw new BillException("La lista itemList contiene un item uguale a null");
        }
        if(user == null) {
            throw new BillException("utente è uguale a null");
        }
    }
    
    public double getOrderPrice(List<EItem> itemList, User user, LocalTime orderTime) throws BillException {
        double total = 0.0d;
       
        checkNull(itemList, user);
       
        for(EItem item: itemList) { 
            total = total + item.getPrice();
        }

        if(total > 1000) {
            total = total * 0.9;
        }
        return total;
    }
}
