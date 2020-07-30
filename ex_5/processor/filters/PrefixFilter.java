package filesprocessing.processor.filters;

import java.io.File;

/**
 * A filter used to check for filtering based on the value of the prefix
 *
 * @author Ariel Zilbershteyin
 */

public class PrefixFilter extends Filter {

    /*----=  data members  =-----*/

    // The prefix this filter checks
    private String prefix;

    //The total amount of arguments this filter has
    public static final int argumentCount = 1;

    /*----=  Constructors  =-----*/


    /**
     * Constructor for the prefix filter
     *
     * @param value the value to filter by
     */
    public PrefixFilter(String value) {
        this.prefix = value;
    }

    /*----=  Methods  =-----*/

    @Override
    public boolean checkFile(File file) {

        return file.getName().startsWith(prefix);
    }
}
