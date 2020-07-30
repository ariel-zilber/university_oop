/**
 * Hash-set  data structure based on  open chaining
 *
 * @author Ariel Zilbershteyin
 */
public class ClosedHashSet extends SimpleHashSet {
	/*----=  data members  =-----*/

	/* Describes the hash set  buckets*/
	private String buckets[];
	private static final String DELETED_VALUE = "This value is deleted";

	/*----=  Constructors  =-----*/

	/**
	 * A default constructor. Constructs a new, empty table with default initial capacity (16),
	 * upper load factor (0.75) and lower load factor (0.25).
	 */
	public ClosedHashSet() {

		super(DEFAULT_HIGHER_CAPACITY, DEFAULT_LOWER_CAPACITY);

		// init the buckets
		buckets = new String[INITIAL_CAPACITY];
	}

	/**
	 * Constructs a new, empty table with the specified load factors,
	 * and the default initial capacity (16).
	 *
	 * @param upperLoadFactor The upper load factor of the hash table.
	 * @param lowerLoadFactor The lower load factor of the hash table.
	 */
	public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor) {
		super(upperLoadFactor, lowerLoadFactor);

		// init the buckets
		buckets = new String[INITIAL_CAPACITY];
	}

	/**
	 * Data constructor - builds the hash set by adding the elements one by one. Duplicate values should be ignored. The new table has the default values of initial capacity (16),
	 * upper load factor (0.75), and lower load factor (0.25).
	 *
	 * @param data Values to add to the set.
	 */
	public ClosedHashSet(java.lang.String[] data) {
		super();

		// init the buckets
		buckets = new String[INITIAL_CAPACITY];

		//adds the elements
		for (String value : data) {
			add(value);
		}
	}

	/*----=  Methods:  =-----*/
	private int getHashIndex(String e, int i) {
		return (e.hashCode() + (i + i * i) / 2) & (capacityMinusOne);
	}

	/*----=  Methods:  =-----*/
	private int getHashIndex(int e, int i) {
		return (e + (i + i * i) / 2) & (capacityMinusOne);
	}

	@Override
	protected void rehash() {
		//create a temporary storage
		String hashTable[] = buckets;

		// create a new table in the appropriate size
		buckets = new String[capacity()];

		// update the table
		for (int i = 0; i < hashTable.length; i++) {

			//checks whenever the bucket is empty
			if (hashTable[i] == null) {
				continue;
			}

			if (hashTable[i] == DELETED_VALUE) {
				continue;
			}
			String key = hashTable[i];


			buckets[clamp(key.hashCode())] = key;
		}
	}

	@Override
	public int capacity() {
		return capacity;
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


		// add a new item
		buckets[clamp(newValue.hashCode())] = newValue;
		increaseSize();

		return true;
	}

	@Override
	public boolean contains(String searchVal) {

		if (searchVal == null)
			return false;

		// look for the value in the hash set
		for (int i = 0; i < this.capacity(); i++) {

			// The index after the hash function
			int hashedIndex = getHashIndex(searchVal, i);

			// checks whenever the value was found
			if (buckets[hashedIndex] != null) {
				String foundValue = buckets[hashedIndex];

				//checks whenever the value was found
				if (foundValue != null) {

					if (foundValue.equals(searchVal)) {
						return true;
					}
				}
			} else {
				return false;
			}

		}
		return false;
	}

	/**
	 * Finds the bucket index of a given value
	 *
	 * @param searchValue the value to search
	 * @return the index of a given value
	 **/
	private int getIndex(String searchValue) {
		int hashedIndex = 0;

		// look for the value in the hash set
		for (int i = 0; i < this.capacity(); i++) {

			// The index after the hash function
			hashedIndex = getHashIndex(searchValue, i);

			// checks whenever the value was found
			if (buckets[hashedIndex] != null) {
				String foundValue = buckets[hashedIndex];

				//checks whenever the value was found
				if (foundValue != null) {

					if (foundValue.equals(searchValue)) {
						return hashedIndex;
					}
				}
			}

		}
		return -1;
	}

	@Override
	protected int clamp(int index) {
		int valueIndex = 0;

		// find the closest suitable value of by the formula:
		// (hash(e)+(i+i^2)/2)  &(tableSize-1)
		for (int i = 0; i < this.capacity(); i++) {

			valueIndex = (index + (i + i * i) / 2) & (capacityMinusOne);

			if (buckets[valueIndex] == null || buckets[valueIndex] == DELETED_VALUE) {
				break;
			}

		}

		return valueIndex;
	}

	@Override
	public boolean delete(String toDelete) {
		//  check null
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
		buckets[getIndex(toDelete)] = DELETED_VALUE;
		decreaseSize(1);

		// checks for resize
		if ((loadFactor < getLowerLoadFactor())) {
			resize(false);
		}

		return true;
	}

}