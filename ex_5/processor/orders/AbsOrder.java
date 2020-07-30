package filesprocessing.processor.orders;


import java.io.File;

/**
 * An orderer used for sorting files by file size, going from smallest to largest
 *
 * @author Ariel Zilbershteyin
 */
public class AbsOrder extends Order {

    @Override
    public int compare(File firstFile, File secondFile) {

        // get the absolute path for the given files
        String firstPath = firstFile.getAbsolutePath();
        String secondPath = secondFile.getAbsolutePath();

        return firstPath.compareTo(secondPath);
    }
}
