package filesprocessing.processor.filters;


import filesprocessing.processor.exceptions.TypeOneErrors;

import java.io.File;

/*
 * A filter used to check for wri ting permissions for the current user
 *
 * @author Ariel Zilbershteyin
 */
public class WritableFilter extends Filter {

    /*----=  data members  =-----*/

    // The permission to filter
    private boolean writePermission;

    // The total amount of arguments this filter takes
    public static final int argumentCount = 1;

    /*----=  Constructors  =-----*/

    /**
     * Constructor for the filter who matches files whose have YES/No writing permission
     *
     * @param value the status (YES/NO) of the writing permission
     **/
    public WritableFilter(String value) throws TypeOneErrors {

        //checks whenever the given input is legal
        if (value.equals("YES"))
            writePermission = true;
        else if (value.equals("NO"))
            writePermission = false;
        else
            throw new TypeOneErrors();

    }

    /*----=  Methods  =-----*/

    @Override
    public boolean checkFile(File file) {
        return file.canWrite() == writePermission;
    }
}
