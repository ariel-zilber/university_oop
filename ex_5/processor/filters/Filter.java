package filesprocessing.processor.filters;

import java.io.File;

/**
 * An super class used for identifying filter types
 *
 * @author Ariel Zilbershteyin
 */
public abstract class Filter{

     /*----=  Methods  =-----*/

    /**
     * The file type to check
     *
     * @param file
     */
    public abstract boolean checkFile(File file);

}
