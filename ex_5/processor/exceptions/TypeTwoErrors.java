package filesprocessing.processor.exceptions;

/**
 * Exception representing type two error as was described  in the excersice
 *
 * @author Ariel Zilbershteyin
 */
public class TypeTwoErrors extends RuntimeException {

    /*----=  data members  =-----*/

    // the  start of the message line
    private final static String ERROR_START = "ERROR: ";

    // the message to print
    private String msg;

    /*----=  Constructors  =-----*/

    /**
     * Constructor for Type 2 message
     *
     * @param msg the message to print
     **/
    public TypeTwoErrors(String msg) {
        this.msg = msg;
    }


    /*----=  Methods  =-----*/

    /**
     * Prints the error type
     */
    public void printError() {
        System.err.println(ERROR_START + this.msg);
    }
}
