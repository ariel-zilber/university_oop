package filesprocessing.processor.filters;

import java.io.File;

/**
 * Filter type that is used when all files are matched
 *
 * @author Ariel Zilbershteyin
 */
public class AllFilter extends Filter {

    /*----=  data members  =-----*/

    // number of arguments for this filter
    public static final int argumentCount = 0;


    /*----=  Constructors =-----*/

    /**
     * Constructor for the filter who matches all file types
     **/
    public AllFilter() {
    }

    /*----=  methods =-----*/

    @Override
    public boolean checkFile(File file) {
        return true;
    }
}
