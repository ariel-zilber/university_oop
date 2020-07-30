package filesprocessing.processor;

import filesprocessing.processor.filters.Filter;
import filesprocessing.processor.orders.Order;

import java.util.ArrayList;

/**
 * Representing a command file section.
 * It contains the  a filter,order and Type 1 warnings
 *
 * @author Ariel Zilbershteyin
 */
public class Section {
    /*----=  data members  =-----*/

    // the filter this section contains
    private Filter filter;

    // the order for this section
    private Order order;

    // the type 1 warnings for this section
    private ArrayList<Integer> warnings;

    /*----=  Constructors  =-----*/


    /**
     * A constructor for sections
     *
     * @param filter   the filter of this section
     * @param order    the type of order for this section
     * @param warnings the type 1 warnings for this section
     */
    public Section(Filter  filter, Order order, ArrayList<Integer> warnings) {
        this.filter = filter;
        this.order = order;
        this.warnings = warnings;
    }

    /*----=  Methods  =-----*/


    /**
     * Getter for the section's filter
     *
     * @return the filter of this section
     */
    public Filter getFilter() {
        return this.filter;
    }

    /**
     * Setter for the sections filter
     *
     * @param filter the new filter
     */
    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    /**
     * Getter for the section's order type
     *
     * @return the order type of this section
     */
    public Order getOrder() {
        return this.order;
    }

    /**
     * Getter for the section's type 1 warnings
     *
     * @return the order type of this section
     */
    public ArrayList<Integer> getWarnings() {
        return this.warnings;
    }

    /**
     * Setter for the sections order
     *
     * @param order the new order
     */
    public void setOrder(Order order) {
        this.order = order;
    }

}
