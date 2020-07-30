package filesprocessing.processor.filters;

import filesprocessing.processor.exceptions.TypeOneErrors;

import java.io.File;

/**
 * A filter used to check for hidden files
 *
 * @author Ariel Zilbershteyin
 */
public class HiddenFilter extends Filter{

    /*----=  data members  =-----*/

    //The total amount of arguments this filter has
    public static final int argumentCount = 1;

    // The file flag to filter by
    private boolean hiddenStatus;

    /*----=  Constructors  =-----*/

    /**
     * Constructor for the filter who matches files whose have YES/No hidden status
     *
     * @param value the status (YES/NO) of the hidden status
     **/
    public HiddenFilter(String value) throws TypeOneErrors {

        //checks whenever the given input is legal
        if (value.equals("YES"))
            hiddenStatus = true;
        else if (value.equals("NO"))
            hiddenStatus = false;
        else
            throw new TypeOneErrors();


    }

    /*----=  Methods  =-----*/

    @Override
    public boolean checkFile(File file) {
        return file.isHidden() == hiddenStatus;
    }
}
