package filesprocessing.processor.filters;

import java.io.File;

/**
 * A filter used to check whenever a value is contained in the file name (excluding path)
 *
 * @author Ariel Zilbershteyin
 */
public class ContainsFilter extends Filter {

    /*----=  data members  =-----*/

    // The value to filter
    private String value;

    // number of arguments for this filter
    public static final int argumentCount = 1;

    /*----=  Constructors  =-----*/

    /**
     * Constructor for the filter who matches files which's name contain a given substring
     *
     * @param value the value files names are check to contain
     **/
    public ContainsFilter(String value) {
        this.value = value;
    }

   /*----=  Methods  =-----*/

    @Override
    public boolean checkFile(File file) {
        return file.getName().contains(value);
    }
}
