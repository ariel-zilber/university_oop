package filesprocessing.processor.orders;

import java.io.File;


/**
 * A sorter used for sorting files by absolute name , going from ’a’ to ’z’
 *
 * @author Ariel Zilbershteyin
 */
public class ReversedOrder extends Order {

    /*----=  data members  =-----*/

    private Order positiveOrder;
    private static int  NEGATIVE=-1;

    /*----=  Constructors  =-----*/

    /**
     * Constructor for this class
     *
     * @param order the order which order to flip
     */
    public ReversedOrder(Order order) {
        this.positiveOrder = order;

    }

    /*----=  Methods  =-----*/

    @Override
    public int compare(File firstFile, File secondFile) {
        return NEGATIVE*positiveOrder.compare(firstFile, secondFile);
    }
}
