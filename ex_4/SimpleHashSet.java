
/**
 * A superclass for implementations of hash-sets implementing the SimpleSet interface.
 */
public abstract class SimpleHashSet implements SimpleSet {
    /*----=  data members  =-----*/

    /**
     * Describes the higher load factor of a newly created hash set
     */
    protected static float DEFAULT_HIGHER_CAPACITY = 0.75f;

    /**
     * Describes the lower load factor of a newly created hash set
     */
    protected static float DEFAULT_LOWER_CAPACITY = 0.25f;

    /**
     * Describes the capacity of a newly created hash set
     */
    protected static int INITIAL_CAPACITY = 16;

    /**
     * Describes the capacity of the set minus one
     */
    protected int capacityMinusOne;

    /**
     * Describes the current lower bound for the load factor
     */
    protected float lowerLoadFactor;

    /**
     * Describes the current lower bound for the load factor
     */
    protected float upperLoadFactor;

    /**
     * Describes the current capacity of the hash set
     */
    protected int capacity;

    /**
     * Describes the amount of occupied space in the hash set
     */
    protected int size;

    /*----=  Constructors  =-----*/

    /**
     * Constructs a new hash set with the default capacities given
     * in DEFAULT_LOWER_CAPACITY and DEFAULT_HIGHER_CAPACITY
     */
    public SimpleHashSet() {
        this.lowerLoadFactor = DEFAULT_LOWER_CAPACITY;
        this.upperLoadFactor = DEFAULT_HIGHER_CAPACITY;
        this.capacity = INITIAL_CAPACITY;
        this.capacityMinusOne = capacity - 1;
        this.size = 0;
    }

    /**
     * Constructs a new hash set with capacity INITIAL_CAPACITY
     *
     * @param upperLoadFactor the upper load factor before rehashing
     * @param lowerLoadFactor the lower load factor before rehashing
     **/
    SimpleHashSet(float upperLoadFactor, float lowerLoadFactor) {
        this.lowerLoadFactor = lowerLoadFactor;
        this.upperLoadFactor = upperLoadFactor;
        this.capacity = INITIAL_CAPACITY;
        this.capacityMinusOne = capacity - 1;
        this.size = 0;
    }

    /*----=  Methods:  =-----*/

    /**
     * @return The current capacity (number of cells) of the table.
     **/
    public abstract int capacity();

    /**
     * Clamps hashing indices to fit within the current table
     * capacity (see the exercise description for details)
     *
     * @param index the index before clamping
     * @return an index properly clamped
     */
    protected abstract int clamp(int index);

    /**
     * @return The lower load factor of the table.
     */
    protected float getLowerLoadFactor() {
        return this.lowerLoadFactor;
    }

    /**
     * @return The higher load factor of the table.
     */
    protected float getUpperLoadFactor() {
        return this.upperLoadFactor;
    }

    /**
     * Increase the value of size by 1.
     */
    protected void increaseSize() {
        this.size += 1;
    }

    /**
     * Decrease the value of size.
     *
     * @param amount the amount to decrease by
     */
    protected void decreaseSize(int amount) {
        this.size -= amount;
    }

    /**
     * Rehashes the values to a new hash data set
     */
    protected abstract void rehash();


    /**
     * Resizes  the size of the hash set.
     *
     * @param add enlarges the hash set  when true and reduces otherwise
     */
    protected void resize(boolean add) {

        if (add) {
            this.capacity = this.capacity * 2;
            this.capacityMinusOne = this.capacity - 1;
        } else if (this.capacity == 1) {

            this.capacity = 1;
            this.capacityMinusOne = 0;

        } else {

            this.capacity = this.capacity / 2;
            this.capacityMinusOne = this.capacity - 1;
        }

        // rehashes stored data
        rehash();

    }



    @Override
    public abstract boolean add(String newValue);

    @Override
    public abstract boolean contains(String searchVal);

    @Override
    public abstract boolean delete(String toDelete);

    @Override
    public int size() {
        return size;
    }


}
