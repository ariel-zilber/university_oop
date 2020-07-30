package filesprocessing.processor.filters;

import filesprocessing.processor.exceptions.TypeOneErrors;

import java.io.File;

/**
 * A filter used to check for execution permissions for the current user
 *
 * @author Ariel Zilbershteyin
 */
public class ExecutableFilter extends Filter {

    /*----=  data members  =-----*/

    // number of arguments for this filter
    public static final int argumentCount = 1;

    // The execution permission
    private boolean execPermission;

    /*----=  Constructors  =-----*/

    /**
     * Constructor for the filter who matches files that have YES/No execution permission
     *
     * @param value the status (YES/NO) of the execution permission
     **/
    public ExecutableFilter(String value) throws TypeOneErrors {

        //checks whenever the given input is legal
        if (value.equals("YES"))
            execPermission = true;
        else if (value.equals("NO"))
            execPermission = false;
        else
            throw new TypeOneErrors();

    }

   /*----=  Methods  =-----*/

    @Override
    public boolean checkFile(File file) {
        return file.canExecute() == execPermission;
    }
}
