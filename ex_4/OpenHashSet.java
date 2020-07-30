
/**
 * Hash-set  data structure based on  open chaining
 *
 * @author Ariel Zilbershteyin
 */
public class OpenHashSet extends SimpleHashSet {
    /*----=  data members  =-----*/

    /* Describes the hash set  buckets*/
    private StringChain buckets[];

    /*----=  Constructors  =-----*/

    /**
     * Constructs a new, empty table with the specified load factors,
     * and the default initial capacity (16).
     *
     * @param upperLoadFactor The upper load factor of the hash table.
     * @param lowerLoadFactor The lower load factor of the hash table.
     **/
    public OpenHashSet(float upperLoadFactor,
                       float lowerLoadFactor) {

        super(upperLoadFactor, lowerLoadFactor);
        buckets = new StringChain[INITIAL_CAPACITY];
    }

    /**
     * A default constructor. Constructs a new,
     * empty table with default initial capacity (16),
     * upper load factor (0.75) and lower load factor (0.25).
     **/
    public OpenHashSet() {
        super(DEFAULT_HIGHER_CAPACITY, DEFAULT_LOWER_CAPACITY);
        buckets = new StringChain[INITIAL_CAPACITY];
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one.
     * Duplicate values should be ignored.
     * The new table has the default values of initial capacity (16),
     * upper load factor (0.75), and lower load factor (0.25).
     *
     * @param data Values to add to the set.
     */
    public OpenHashSet(java.lang.String[] data) {
        super();
        buckets = new StringChain[INITIAL_CAPACITY];

        // add the elements
        for (String value : data) {
            add(value);
        }
    }

    /*----=  Methods:  =-----*/

    @Override
    public int capacity() {
        return capacity;
    }

    protected int clamp(int index) {

        // hash(e) & (tableSize-1)
        return index & (capacityMinusOne);
    }

    @Override
    public boolean add(String newValue) {


        // check duplicates
        if (contains(newValue)) {
            return false;
        }

        // gets the load factor
        float loadFactor = (float) (size()+1 )/ capacity();

        // checks for resize
        if (loadFactor > getUpperLoadFactor()) {
            resize(true);
        }

        // init the bucket case it is empty
        if (buckets[clamp(newValue.hashCode())] == null) {
            buckets[clamp(newValue.hashCode())] = new StringChain();
        }

        // add a new item
        buckets[clamp(newValue.hashCode())].add(newValue);
        increaseSize();

        return true;
    }

    @Override
    protected void rehash() {

        //create a temporary storage
        StringChain temp[] = new StringChain[capacity()];

        for (StringChain bucket : buckets) {

            // goes to the next bucket whenever empty
            if (bucket == null) {
                continue;
            }

            int bucketSize = bucket.size();

            // puts the keys to new suitable buckets according to the hash function
            for (int i = 0; i < bucketSize; i++) {
                String key = bucket.get(i);

                if (temp[clamp(key.hashCode())] == null) {
                    temp[clamp(key.hashCode())] = new StringChain();
                }

                temp[clamp(key.hashCode())].add(key);
            }
        }

        // update the table
        buckets = temp;
    }

    @Override
    public boolean contains(String searchVal) {

        if (searchVal == null) return false;

        //get the index of the index of the key
        int index = searchVal.hashCode();
        StringChain bucket = buckets[clamp(index)];

        if (bucket == null) {
            return false;
        }

        return bucket.contains(searchVal);
    }

    @Override
    public boolean delete(String toDelete) {
        //  checks null string
        if (toDelete == null) {
            return false;
        }

        // check duplicates
        if (!contains(toDelete)) {
            return false;
        }

        // gets the load factor
        float loadFactor = (float) (size()-1 )/ capacity();

        // delete the item
        buckets[clamp(toDelete.hashCode())].remove(toDelete);
        decreaseSize(1);


        // checks for resize
        if ((loadFactor < getLowerLoadFactor())) {
            resize(false);
        }


        return true;
    }


}
