////////////////////////////////////////////////////////////////////
// [Nicola] [Lazzarin] [1204686]
// [Irene] [Benetazzo] [1223865]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business.exception;

public class BillException extends Throwable {
    private static final long serialVersionUID = 1L;

    public BillException(String msg){
        super(msg);
    }
}
