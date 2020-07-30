package filesprocessing.processor.filters;

import filesprocessing.processor.exceptions.TypeOneErrors;

import java.io.File;

/**
 * A filter used to check whenever File size is between a given range (inclusive) in KiloBytes
 *
 * @author Ariel Zilbershteyin
 */
public class BetweenFilter extends Filter {

    /*----=  data members  =-----*/

    // size range
    private double minSize;
    private double maxSize;

    // the amount of bytes in a kilobyte(used for converting later)
    private final static int BYTES_IN_KILOBYTE = 1024;

    // number of arguments for this filter
    public static final int argumentCount = 2;

    /*----=  Constructors  =-----*/

    /**
     * Constructor for the filter who matches files whose sizes are between a given range in KiloBytes
     *
     * @param largestSize  the largest size a file can have(inclusive) in KiloBytes
     * @param smallestSize the smallest size a file can have(inclusive) in KiloBytes
     **/
    public BetweenFilter(double smallestSize, double largestSize) throws TypeOneErrors {

        // check for the validity of the size range
        if (largestSize <= smallestSize) {
            throw new TypeOneErrors();
        }

        // checks for the validity of the size
        if (smallestSize < 0 || largestSize < 0) {
            throw new TypeOneErrors();
        }

        this.minSize = smallestSize;
        this.maxSize = largestSize;
    }

   /*----=  Methods  =-----*/

    @Override
    public boolean checkFile(File file) {

        // convert to kilobytes
        double sizeInKByte = (double) file.length() / BYTES_IN_KILOBYTE;
        return (minSize <= sizeInKByte && sizeInKByte <= maxSize);

    }
}
