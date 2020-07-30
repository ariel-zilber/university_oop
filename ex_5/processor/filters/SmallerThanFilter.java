package filesprocessing.processor.filters;


import filesprocessing.processor.exceptions.TypeOneErrors;

import java.io.File;

/**
 * A filter used to check whenever File size is strictly less than the given number of kiloBytes
 * <p>
 * * @author Ariel Zilbershteyin
 */
public class SmallerThanFilter extends Filter {

    /*----=  data members  =-----*/

    // The minimal size acceptable by this filter
    private double maxSize;

    //The total amount of arguments this filter has
    public static final int argumentCount = 1;

    // The total amount of bytes in kilo Bytes
    private final static int BYTES_IN_KILOBYTE = 1024;

    /*----=  Constructors  =-----*/


    /**
     * Constructor for the filter who matches files whose sizes are larger than a given value in KiloBytes
     *
     * @param value the largest size a file can have(inclusive) in KiloBytes
     **/
    public SmallerThanFilter(double value) throws TypeOneErrors {

        // The value most be non-negative
        if (value < 0) {
            throw new TypeOneErrors();
        }

        this.maxSize = value;
    }

    /*----=  Methods  =-----*/

    @Override
    public boolean checkFile(File file) {

        // convert to kilobytes
        double sizeInKByte = (double) file.length() / BYTES_IN_KILOBYTE;
        return sizeInKByte < maxSize;
    }
}
