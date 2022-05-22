////////////////////////////////////////////////////////////////////
// [Nicola] [Lazzarin] [1204686]
// [Irene] [Benetazzo] [1223865]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import java.time.LocalTime;
import it.unipd.mtss.model.User;

public interface GiveawayBill {
    boolean giveAwayOrder(User user, LocalTime orderTime);
}
