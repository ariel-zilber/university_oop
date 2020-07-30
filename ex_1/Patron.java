/**
 * This class represents a library patron that has a name and assigns values to
 * different literary aspects of books.
 */
class Patron {

    /** The first name of the patron. */
    final String firstName;

    /** The Last name of the patron. */
    final String lastName;

    /** The comic tendency of the patron. */
    int comicTendency;

    /** The dramatic tendency of the patron. */
    int dramaticTendency;

    /** The educational  tendency of the patron. */
    int educationalTendency;

    /** The Personal enjoyment threshold. */
    final int enjoymentThreshold;

    /** The number of currently borrowed books by the patron    */

    int borrowedBooks ;
   /*----=  Constructors  =-----*/

    /**
     * Creates a new book with the given characteristic.
     * @param patronFirstName          The first name of the patron.
     * @param patronLastName           The last name of the patron.
     * @param comicTendency            The weight the patron assigns to the comic aspects of books.
     * @param dramaticTendency         The weight the patron assigns to the dramatic aspects of books.
     * @param educationalTendency      The weight the patron assigns to the educational aspects of books.
     * @param patronEnjoymentThreshold The minimal literary value a book must have for this patron to enjoy it.
     */
    Patron(String patronFirstName, String patronLastName, int comicTendency, int dramaticTendency,
           int educationalTendency, int patronEnjoymentThreshold) {

        this.firstName = patronFirstName;
        this.lastName = patronLastName;
        this.comicTendency = comicTendency;
        this.dramaticTendency = dramaticTendency;
        this.educationalTendency = educationalTendency;
        this.enjoymentThreshold = patronEnjoymentThreshold;
        this.borrowedBooks=0;
    }

   /*----=  Instance Methods  =-----*/

    /**
     * Returns a string representation of the patron,
     * which is a sequence of its first and last name,
     * separated by a single white space.
     * For example, if the patron's first name is "Ricky" and his last name is "Bobby",
     * this method will return the String "Ricky Bobby".
     * @return the String representation of this patron.
     **/
    String stringRepresentation() {
        return firstName + " " + lastName;
    }

    /**
     * Returns the literary value this patron assigns to the given book.
     * @param book The book to asses.
     * @return The literary value this patron assigns to the given book.
     */
    int getBookScore(Book book) {
        return book.educationalValue * educationalTendency + book.dramaticValue * dramaticTendency + book.comicValue * comicTendency;
    }

    /**
     * Returns true of this patron will enjoy the given book, false otherwise.
     * @param book The book to asses.
     * @return true of this patron will enjoy the given book, false otherwise.
     */
    boolean willEnjoyBook(Book book) {
        return getBookScore(book) >= this.enjoymentThreshold;
    }
}
