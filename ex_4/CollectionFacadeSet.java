
import java.util.TreeSet;

/**
 * Wraps an underlying Collection and serves to both simplify its API and give it a common type with the
 * implemented SimpleHashSets.
 *
 * @author Ariel Zilbershteyin
 */
public class CollectionFacadeSet implements SimpleSet {
    private int size;

    /*----=  data members  =-----*/
    protected java.util.Collection<java.lang.String> collection;

    /*----=  Constructors  =-----*/

    /**
     * Creates a new facade wrapping the specified collection.
     *
     * @param collection The Collection to wrap.
     */
    public CollectionFacadeSet(java.util.Collection<java.lang.String> collection) {

        this.collection = collection;

        // get the size of the  unique values
        TreeSet<String> temp = new TreeSet<>(collection);
        size = temp.size();
    }

    /*----=  Methods:  =-----*/

    @Override
    public boolean add(java.lang.String newValue) {

        if (newValue == null) {
            return false;
        }

        if (collection.contains(newValue)) {
            return false;
        }

        // add the value to the collection
        size += 1;
        return collection.add(newValue);

    }

    @Override
    public boolean contains(java.lang.String searchVal) {

        if (searchVal == null) {
            return false;
        }

        // returns the default suitable method of the collection
        return collection.contains(searchVal);
    }

    @Override
    public boolean delete(java.lang.String toDelete) {

        if (toDelete == null) {
            return false;
        }

        // check whenever the item exists
        if (!collection.contains(toDelete)) {
            return false;
        }

        // remove all the duplicates
        while (collection.contains(toDelete)) {
            collection.remove(toDelete);
        }

        //remove the value from the collection
        size -= 1;
        return true;
    }

    @Override
    public int size() {

        return size;
    }
}
