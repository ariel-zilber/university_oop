package filesprocessing.processor.filters;


import filesprocessing.processor.exceptions.*;
import filesprocessing.processor.orders.OrderFactory;

/**
 * Creates a filterer (following the Factory design pattern )
 */
public class FilterFactory {

    /*----=  data members  =-----*/

    private static final String NOT_FILTER = "NOT";
    private static final String TAG = "#";
    private static final String BAD_PARAM_NUM = "The total amount of # should not exceed 2";

    /* filter names */
    private static final String ALL = "all";
    private static final String IS_HIDDEN = "hidden";
    private static final String IS_EXECUTABLE = "executable";
    private static final String IS_WRITABLE = "writable";
    private static final String SUFFIX = "suffix";
    private static final String PREFIX = "prefix";
    private static final String CONTAINS = "contains";
    private static final String FILE_NAME = "file";
    private static final String SMALLER_THEN = "smaller_than";
    private static final String BETWEEN = "between";
    private static final String GREATER_THEN = "greater_than";

    // only instance of this class
    private static OrderFactory instance = null;

    /*----=  Constructors  =-----*/

    private FilterFactory() {
    }

    /*----=  Methods  =-----*/
    public static OrderFactory getInstance() {
        return instance;
    }

    /**
     * Creates an filterer with logic based on the given parameters
     *
     * @param filterCommand the filter command as it is appearing on the command text file
     * @return the filterer
     */
    public static Filter createFilter(String filterCommand) throws TypeOneErrors {

        Filter filter;
        String filterType;
        String firstParam = null;
        String secondParam = null;
        boolean negative = false;

        // checks for the #NOT tag
        if (filterCommand.contains(TAG + NOT_FILTER)) {
            filterCommand = filterCommand.substring(0, filterCommand.lastIndexOf(TAG));
            negative = true;
        }

        //count the total amount off # tags appearing
        String[] args = filterCommand.split(TAG);
        int paramCount = args.length - 1;

        // sets the parameters accordingly to the parameter count
        switch (paramCount) {
            case 0:
                filterType = filterCommand;
                break;
            case 1:
                filterType = args[0];
                firstParam = args[1];
                break;
            case 2:
                filterType = args[0];
                firstParam = args[1];
                secondParam = args[2];
                break;
            default:
                throw new TypeTwoErrors(BAD_PARAM_NUM);
        }


        // checks for the appropriate filter type to create
        // a valid command must be given with appropriate number of parameters
        // according to the excersice description
        switch (filterType) {
            case ALL:
                if (paramCount != AllFilter.argumentCount) {
                    throw new TypeOneErrors();
                }

                filter = new AllFilter();
                break;
            case IS_HIDDEN:
                if (paramCount != HiddenFilter.argumentCount) {
                    throw new TypeOneErrors();
                }

                filter = new HiddenFilter(firstParam);

                break;
            case IS_EXECUTABLE:
                if (paramCount != ExecutableFilter.argumentCount) {
                    throw new TypeOneErrors();
                }

                filter = new ExecutableFilter(firstParam);
                break;
            case IS_WRITABLE:
                if (paramCount != WritableFilter.argumentCount) {
                    throw new TypeOneErrors();
                }

                filter = new WritableFilter(firstParam);
                break;
            case SUFFIX:
                if (paramCount != SuffixFilter.argumentCount) {
                    throw new TypeOneErrors();
                }

                filter = new SuffixFilter(firstParam);
                break;
            case PREFIX:
                if (paramCount != PrefixFilter.argumentCount) {
                    throw new TypeOneErrors();
                }


                filter = new PrefixFilter(firstParam);
                break;
            case CONTAINS:
                if (paramCount != ContainsFilter.argumentCount) {
                    throw new TypeOneErrors();
                }

                filter = new ContainsFilter(firstParam);
                break;
            case FILE_NAME:
                if (paramCount != FileFilter.argumentCount) {
                    throw new TypeOneErrors();
                }

                filter = new FileFilter(firstParam);
                break;
            case SMALLER_THEN:

                if (paramCount != SmallerThanFilter.argumentCount) {
                    throw new TypeOneErrors();
                }

                filter = new SmallerThanFilter(Double.valueOf(firstParam));
                break;
            case BETWEEN:
                if (paramCount != BetweenFilter.argumentCount) {
                    throw new TypeOneErrors();
                }

                filter = new BetweenFilter(Double.valueOf(firstParam),
                        Double.valueOf(secondParam));
                break;
            case GREATER_THEN:

                if (paramCount != GreaterThanFilter.argumentCount) {
                    throw new TypeOneErrors();
                }

                filter = new GreaterThanFilter(Double.valueOf(firstParam));
                break;
            default:
                throw new TypeOneErrors();
        }

        // case the negative tag was given flips the filter
        if (negative) {
            filter = new NotFilter(filter);
        }

        return filter;
    }
}
