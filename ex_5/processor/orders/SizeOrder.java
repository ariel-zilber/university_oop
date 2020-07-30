package filesprocessing.processor.orders;

import java.io.File;

/**
 * An orderer used for sorting files by absolute name , going from ’a’ to ’z’
 *
 * @author Ariel Zilbershteyin
 */
public class SizeOrder extends Order {


    @Override
    public int compare(File firstFile, File secondFile) {

        // get the sizes of the given files
        long firstSize = firstFile.length();
        long secondSize = secondFile.length();

        // case the files have the same size
        if (firstSize == secondSize) {
            return new AbsOrder().compare(firstFile, secondFile);
        }

        if (firstSize > secondSize) {
            return 1;
        } else {
            return -1;
        }

    }
}
