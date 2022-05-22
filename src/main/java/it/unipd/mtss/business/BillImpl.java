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
    
    GiveawayBillImpl giveaway;
    
    public BillImpl() {
        giveaway = new GiveawayBillImpl();
    }

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
        if(itemList.size() > 30) {
            throw new BillException("Ci sono più di 30 items nella lista itemList");
        }
    }
    
    public double getOrderPrice(List<EItem> itemList, User user, LocalTime orderTime) throws BillException {
        double total = 0.0d;

        int countMouse = 0;
        double mouseMenoCaro = 0.0d;
       
        checkNull(itemList, user);
       
        for(EItem item: itemList) {
            if(item.getItemType() == ProductType.Mouse) {
                countMouse++;
                if(mouseMenoCaro > item.getPrice() || mouseMenoCaro == 0.0d) {
                    mouseMenoCaro = item.getPrice();
                }
            }

            total = total + item.getPrice();
        }

        if(countMouse > 10) {
            total = total - mouseMenoCaro;
        }

        if(total > 1000) {
            total = total * 0.9;
        }

        if(total < 10) {
            total += 2;
        }
        
        if(giveaway.giveAwayOrder(user, orderTime)) {
            return 0;
        }  

        return total;
    }
}
