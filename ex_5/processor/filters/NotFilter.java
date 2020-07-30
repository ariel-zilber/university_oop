package filesprocessing.processor.filters;

import java.io.File;

/*
 * A filter used to filter in the opposite way of a given filter type
 *
 * @author Ariel Zilbershteyin
 */
public class NotFilter extends Filter {

    /*----=  data members  =-----*/

    // The filter type to decorate
    private Filter positiveFilter;

    /*----=  Constructors  =-----*/


    /**
     * Constructor for the negative evaluation of a given filter
     *
     * @param filter a given filter
     **/
    public NotFilter(Filter filter) {
        this.positiveFilter = filter;
    }

     /*----=  Methods  =-----*/

    @Override
    public boolean checkFile(File file) {
        return !(this.positiveFilter.checkFile(file));
    }
}
