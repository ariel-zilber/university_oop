package filesprocessing.processor.orders;

import java.io.File;

/**
 * An orderer  used for sorting files by file type, going from ’a’ to ’z’
 *
 * @author Ariel Zilbershteyin
 */
public class TypeOrder extends Order {

    /**
     * @param file some file to get the extension from
     * @return the file extension
     */
    private String getExtension(File file) {
        String fileName = file.getName();
        String extension = "";
        int i = fileName.lastIndexOf('.');

        if (i >= 0) {
            extension = fileName.substring(i + 1);
        }

        return extension;
    }

    @Override
    public int compare(File firstFile, File secondFile) {

        // get the extension for the provided files
        String firstPath = getExtension(firstFile);
        String secondPath = getExtension(secondFile);

        int compareRes = firstPath.compareTo(secondPath);

        //case the files have the same same
        if (compareRes == 0) {
            return new AbsOrder().compare(firstFile, secondFile);
        }


        return compareRes;
    }
}
