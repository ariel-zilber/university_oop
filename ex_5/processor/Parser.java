package filesprocessing.processor;

import filesprocessing.processor.exceptions.TypeOneErrors;
import filesprocessing.processor.exceptions.TypeTwoErrors;
import filesprocessing.processor.filters.AllFilter;
import filesprocessing.processor.filters.Filter;
import filesprocessing.processor.filters.FilterFactory;
import filesprocessing.processor.orders.AbsOrder;
import filesprocessing.processor.orders.Order;
import filesprocessing.processor.orders.OrderFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Representing a parser that turns a command file into sections
 *
 * @author Ariel Zilbershteyin
 */
public class Parser {

    /*----=  data members  =-----*/

    //tags
    private static final String FILTER = "FILTER";
    private static final String ORDER = "ORDER";

    //error messages
    private static final String IO = "I/O error";
    private static final String ABS = "abs";
    private static final String BAD_FORMAT_ERROR = "Bad format of Commands File";
    private static final String BAD_SUB_SECTION = "Bad subsection name";
    private static final String EMPTY = "";

    // the section length as was given is the excersice description
    private static final int LENGTH_OF_SECTION = 4;


    // the only instance of this class
    private static Parser obj;

    /*----=  Constructors  =-----*/
    private Parser() {
    }

    /*----=  Methods  =-----*/
    public static Parser getInstance() {
        if (obj == null) {
            obj = new Parser();
        }
        return obj;
    }

    /**
     * Creates sections from a provided command file
     *
     * @param commandFile the file to turn into a ArrayList of sections
     */
    public ArrayList<Section> createSections(File commandFile) throws TypeTwoErrors {

        try {
            ArrayList<Section> section = new ArrayList<Section>();
            ArrayList<Integer> warnings = new ArrayList<>();
            Filter filter = null;
            Order order = null;

            // given line in the command file
            String sectionLine;

            // how many skipped lines occurred(orders without value)
            int skippedLines = 0;

            // reads lines from the command file
            Scanner scanner = new Scanner(commandFile);

            for (int line = 1; scanner.hasNextLine() || line % LENGTH_OF_SECTION != 1; line++) {

                // checks for EOF
                if (scanner.hasNextLine()) {
                    sectionLine = scanner.nextLine();
                } else {
                    sectionLine = EMPTY;
                }

                // goes to the appropriate section
                switch (line % LENGTH_OF_SECTION) {
                    case 1:
                        if (!sectionLine.equals(FILTER)) {
                            throw new TypeTwoErrors(BAD_SUB_SECTION);
                        }
                        break;
                    case 2:
                        if (!scanner.hasNextLine()) {
                            throw new TypeTwoErrors(BAD_FORMAT_ERROR);
                        } else {
                            try {
                                filter = FilterFactory.createFilter(sectionLine);
                            } catch (TypeOneErrors e) {
                                warnings.add((line - skippedLines));

                                filter = new AllFilter();
                            }
                        }
                        break;
                    case 3:
                        if (!sectionLine.equals(ORDER)) {
                            throw new TypeTwoErrors(BAD_SUB_SECTION);
                        }
                        break;
                    case 0:
                        if (sectionLine.equals(EMPTY)) {
                            sectionLine = ABS;
                        }

                        try {
                            order = OrderFactory.createOrder(sectionLine);
                        } catch (TypeOneErrors e) {
                            order = new AbsOrder();
                            if (sectionLine.equals(FILTER)) {
                                line++;
                                skippedLines++;
                            } else {
                                warnings.add((line - skippedLines));
                            }
                        }
                        section.add(new Section(filter, order, warnings));
                        warnings = new ArrayList<>();

                        break;
                }

            }
            return section;


        } catch (IOException d) {
            throw new TypeTwoErrors(IO);
        }

    }


}
