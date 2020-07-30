package filesprocessing.processor.orders;

import filesprocessing.processor.exceptions.TypeOneErrors;

/**
 * Creates a sorter (following the Factory design pattern ).
 */
public class OrderFactory {

    /*----=  data members  =-----*/

    private static OrderFactory instance = null;

    //tags
    private final static String REVERSE = "REVERSE";
    private final static String TAG = "#";

    // order types
    private final static String ABS = "abs";
    private final static String SIZE = "size";
    private final static String TYPE = "type";


    /*----=  Constructors  =-----*/

    private OrderFactory() {
    }

    /*----=  Methods  =-----*/
    public static OrderFactory getInstance() {
        return instance;
    }


    /**
     * Creates an sorter with logic based on the given parameters
     *
     * @param orderCommand the type of order to create
     * @return the sorter
     */
    public static Order createOrder(String orderCommand) throws TypeOneErrors {

        Order order;
        boolean reversed = false;

        //  the arguments given
        String commandArgs[] = orderCommand.split(TAG);
        String orderType = commandArgs[0];


        // checks  for valid amount of parameters
        if (commandArgs.length == 2) {
            if (commandArgs[1].equals(REVERSE)) {
                reversed = true;
            } else {
                throw new TypeOneErrors();
            }
        }

        // checks for the appropriate order type to create
        switch (orderType) {
            case ABS:
                order = new AbsOrder();
                break;
            case SIZE:
                order = new SizeOrder();
                break;
            case TYPE:
                order = new TypeOrder();
                break;
            default:
                throw new TypeOneErrors();
        }

        // reverses the order case the #REVESE tage was provided
        if (reversed) {
            order = new ReversedOrder(order);
        }

        return order;
    }
}
