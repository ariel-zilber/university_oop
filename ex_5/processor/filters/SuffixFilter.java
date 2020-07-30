package filesprocessing.processor.filters;


import java.io.File;

/**
 * A filter used to check for filtering based on the value of the suffix
 *
 * @author Ariel Zilbershteyin
 */
public class SuffixFilter extends Filter {

    /*----=  data members  =-----*/

    // The suffix to filter
    private String suffix;

    //The total amount of arguments this filter has
    public static final int argumentCount = 1;

    /*----=  Constructors  =-----*/

    /**
     * The constructor for the suffix filter
     *@param value the value to filter
     * */
    public SuffixFilter(String value) {
        this.suffix = value;
    }

    @Override
    public boolean checkFile(File file) {
        return file.getName().endsWith(suffix);
    }
}
