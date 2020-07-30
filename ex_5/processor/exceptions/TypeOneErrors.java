package filesprocessing.processor.exceptions;

/**
 * Exception representing type one error as
 * was described  in the excersice
 *
 * @author Ariel Zilbershteyin
 */
public class TypeOneErrors extends Exception {

    /*----=  data members  =-----*/

    // the template for this message
    private static final String ERROR = "Warning in line: ";

    /*----=  Constructors  =-----*/


    /**
     * The default constructor for this class
     */
    public TypeOneErrors() {
    }


    /**
     * A constructor for this class with a custom message
     *
     * @param error the message to throw
     */
    public TypeOneErrors(String error) {
        super(error);
    }


    /**
     * Prints a waring in the format "Warning in line<the line>" as was described
     * in the excersice description
     *
     * @param line the line the error occurs
     */
    public TypeOneErrors(int line) {
        super(ERROR + line);
    }

}
