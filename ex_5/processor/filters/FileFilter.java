package filesprocessing.processor.filters;

import java.io.File;

/**
 * A filter used to check whenever a value equals the file name (excluding path)
 *
 * @author Ariel Zilbershteyin
 */

public class FileFilter extends Filter {

    /*----=  data members  =-----*/

    // The file name to filter by
    private String fileName;

    // number of arguments for this filter
    public static final int argumentCount = 1;

    /*----=  Constructors  =-----*/

    /**
     * Constructor for the filter who matches files by a given name
     *
     * @param value the value files names are check to contain
     **/
    public FileFilter(String value) {
        this.fileName = value;
    }


    /*----=  Methods  =-----*/

    @Override
    public boolean checkFile(File file) {
        return file.getName().equals(fileName);
    }
}
