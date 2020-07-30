package filesprocessing;


import filesprocessing.processor.Parser;
import filesprocessing.processor.Section;
import filesprocessing.processor.exceptions.TypeOneErrors;
import filesprocessing.processor.exceptions.TypeTwoErrors;
import filesprocessing.processor.filters.Filter;
import filesprocessing.processor.orders.Order;

import java.io.File;
import java.util.ArrayList;


/**
 * A program for filtering files in a given directory according to various conditions, and order the filenames
 * that passed the filtering according to various properties
 *
 * @author Ariel Zilbershteyin
 */
public class DirectoryProcessor {


    /*----=  data members  =-----*/

    //  message used when throwing  type 2 exceptions
    private static final String BAD_ARGS_NUM = "Wrong usage! 2 arguments should be received";
    private static final String EMPTY_SRC_DIR = "Source directory does not contain files";
    private static final String MISSING_COMMAND_FILE = "Missing command file";

    /*----=  Methods  =-----*/

    /**
     * @param args the processor arguments where which format was described in the ex description
     * @throws TypeTwoErrors
     */
    public static void main(String[] args) {
        try {
            // checks for the amount of provided parameters
            if (args.length != 2) {
                throw new TypeTwoErrors(BAD_ARGS_NUM);
            }


            File sourceDir = new File(args[0]);
            File commandFile = new File(args[1]);
            File[] fileList = sourceDir.listFiles();

            // checks for the existence of the file
            if (!commandFile.exists()) {
                throw new TypeTwoErrors(MISSING_COMMAND_FILE);
            }

            //checks whenever the directory is empty
            if (fileList == null) {
                throw new TypeTwoErrors(EMPTY_SRC_DIR);
            }
            // run the command file
            executeCommandFile(fileList, commandFile);
        } catch (TypeTwoErrors e) {
            e.printError();
        }
    }


    /**
     * runs the given command file
     *
     * @param commandFile runs the command file
     * @param files
     **/
    private static void executeCommandFile(File[] files, File commandFile) throws TypeTwoErrors {
        Parser parser = Parser.getInstance();
        ArrayList<Section> sections = parser.createSections(commandFile);

        for (int i = 0; i < sections.size(); i++) {
            Filter filter = sections.get(i).getFilter();
            Order order = sections.get(i).getOrder();

            // filters the file array
            File[] filteredArray = filterFiles(files, filter);

            // sorts the array
            order.sort(filteredArray);

            // prints the section's warnings
            printWarnings(sections.get(i));

            // print the list of sorted files for this section
            printFiles(filteredArray);
        }

    }

    /**
     * Filters a list of files based on a given filter
     *
     * @param filter
     * @param fileList
     */
    private static File[] filterFiles(File[] fileList, Filter filter) {
        ArrayList<File> filteredList = new ArrayList<>();

        for (File file : fileList)
            if (filter.checkFile(file) && (!file.isDirectory())) {
                filteredList.add(file);
            }

        return filteredList.toArray(new File[filteredList.size()]);
    }

    /**
     * Prints the names of each file in a given file array
     *
     * @param files
     */
    private static void printFiles(File[] files) {
        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i].getName());
        }
    }

    /**
     * Prints a given section's warnings
     *
     * @param section
     */
    private static void printWarnings(Section section) {
        ArrayList<Integer> warnings = section.getWarnings();

        for (int i = 0; i < warnings.size(); i++) {
            System.err.println("Warning in line " + warnings.get(i));
            try {
                throw new TypeOneErrors(warnings.get(i));
            } catch (TypeOneErrors e) {

            }
        }
    }


}
