import java.util.Iterator;
import java.util.LinkedList;

/**
 * A wrapper class for a String Linked List
 *
 * * @author Ariel Zilbershteyin
 */
public class StringChain implements Iterable<String> {
    /*----=  data members  =-----*/

    private LinkedList<String> stringLinkedList;

    /*----=  Constructors  =-----*/

    /**
     * Constructs an empty wrapper to  String linked list.
     */
    public StringChain() {
        this.stringLinkedList = new LinkedList<String>();
    }


    /**
     * Constructs a  wrapper to  String linked list.
     * @param stringLinkedList the string linked list to wrap
     */
    public StringChain(LinkedList<String> stringLinkedList) {
        this.stringLinkedList = stringLinkedList;
    }

    /*----=  Methods:  =-----*/

    /**
     * Returns true if this list contains the specified string.
     *
     * @param value string whose presence in this list is to be tested
     * @return true if this list contains the specified string
     */
    public boolean contains(String value) {
        return stringLinkedList.contains(value);
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param e string  to be appended to this list
     * @return true if the the string was added
     */
    public boolean add(String e)
    {
        return stringLinkedList.add(e);
    }

    /**
     * Removes the first occurrence of the specified string from this list,
     * if it is present.If this list does not contain the string, it is
     * unchanged.
     *
     * @param e string to be removed from this list, if present
     * @return true if this list contained the specified string
     */
    public boolean remove(String e) {
        return stringLinkedList.remove(e);
    }

    /**
     * Returns the number of strings in this list.
     *
     * @return the number of strings in this list
     */
    public int size() {
        return stringLinkedList.size();
    }

    /**
     * Returns the string at the specified position in this list.
     *
     * @param index index of the string to return
     * @return the string at the specified position in this list
     */
    public String get(int index) {
        return stringLinkedList.get(index);
    }


    @Override
    public Iterator<String> iterator() {
        return stringLinkedList.iterator();
    }
}
