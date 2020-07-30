/**
 * This class represents a library, which hold a collection of books.
 * Patrons can register at the library to be able to check out books,
 * if a copy of the requested book is available.
 */
class Library {

    /**
     * The book capacity of this book.
     */
    final int bookCapacity;

    /**
     * The borrowed books capacity of this Library.
     */
    final int borrowedBooksCapacity;

    /**
     * The patron capacity of this Library.
     */
    final int patronCapacity;

    /**
     * The books currently kept in the library
     */
    Book books[];

    /**
     * The patrons currently registered in the library
     */
    Patron registeredPatrons[];


   /*----=  Constructors  =-----*/

    /**
     * Creates a new library with the given parameters.
     *
     * @param maxBookCapacity   The maximal number of books this library can hold.
     * @param maxBorrowedBooks  The maximal number of books this library allows a
     *                          single patron to borrow at the same time.
     * @param maxPatronCapacity The maximal number of registered patrons this library can handle.
     */
    Library(int maxBookCapacity, int maxBorrowedBooks, int maxPatronCapacity) {
        this.bookCapacity = maxBookCapacity;
        this.borrowedBooksCapacity = maxBorrowedBooks;
        this.patronCapacity = maxPatronCapacity;

        this.books = new Book[bookCapacity];
        this.registeredPatrons = new Patron[patronCapacity];
    }

    /*----=  Instance Methods  =-----*/

    /**
     * Adds the given book to this library, if there is place available,
     * and it isn't already in the library.
     *
     * @param book The book to add to this library.
     * @return a non-negative id number for the book if there was a spot and the book was successfully added,
     * or if the book was already in the library; a negative number otherwise.
     */
    int addBookToLibrary(Book book) {
        int bookSpot = -1;

        //checks for available spot and whenever the book was already registered
        for (int i = 0; i < bookCapacity; i++) {
            if (books[i] == book) {
                return i;
            }

            if (books[i] == null) {
                bookSpot = i;
            }
        }

        // add the book to the library
        if (bookSpot != -1) {
            books[bookSpot] = book;
        }
        return bookSpot;
    }

    /**
     * Returns true if the given number is an id of some book in the library,
     * false otherwise.
     *
     * @param bookId The id to check.
     * @return true if the given number is an id of some book in the library,
     * false otherwise.
     */
    boolean isBookIdValid(int bookId) {

        if (bookId < 0 || bookId >= bookCapacity) {
            return false;
        } else if (books[bookId] == null) {
            return false;
        }
        return true;
    }


    /**
     * Returns the non-negative id number of the given book if he is owned by this library,
     * -1 otherwise.
     *
     * @param book The book for which to find the id number.
     * @return a non-negative id number of the given book if he
     * is owned by this library, -1 otherwise.
     */
    int getBookId(Book book) {
        for (int i = 0; i < bookCapacity; i++) {
            if (books[i] == book) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Returns true if the book with the given id is available,
     * false otherwise.
     *
     * @param bookId The id number of the book to check.
     * @return true if the book with the given id is available,
     * false otherwise.
     */
    boolean isBookAvailable(int bookId) {

        if (!isBookIdValid(bookId)) {
            return false;
        }

        return books[bookId].currentBorrowerId == -1;
    }

    /*
    * Registers the given Patron to this library,
    * if there is a spot available.
    * @param patron The patron to register to this library.
    * @return a non-negative id number for the patron if there was a spot and the patron was successfully registered,
    * a negative number otherwise.
    * */
    int registerPatronToLibrary(Patron patron) {
        int spot = -1;

        //checks for available spot and whenever the patron was already registered
        for (int i = 0; i < patronCapacity; i++) {
            if (registeredPatrons[i] == patron) {
                return i;
            }
            if (registeredPatrons[i] == null) {
                spot = i;
            }
        }

        // register the patrons  to the library
        if (spot != -1) {
            registeredPatrons[spot] = patron;
        }

        return spot;
    }

    /**
     * Returns true if the given number is an id of a patron in the library,
     * false otherwise.
     *
     * @param patronId The id to check.
     * @return true if the given number is an id of a patron in the library,
     * false otherwise.
     **/
    boolean isPatronIdValid(int patronId) {
        if (patronId < 0 || patronId >= patronCapacity) {
            return false;
        } else if (registeredPatrons[patronId] == null) {
            return false;
        }

        return true;
    }

    /*
    * Returns the non-negative id number of the given patron if he is registered to this library,
    * -1 otherwise.
    * @param patron The patron for which to find the id number.
    * @return a non-negative id number of the given patron if he is registered to this library, -1 otherwise.
    * */
    int getPatronId(Patron patron) {

        //check if the patron is registered to this library
        for (int i = 0; i < patronCapacity; i++) {
            if (registeredPatrons[i] == patron) {
                return i;
            }
        }
        return -1;
    }

    /*
     *  Marks the book with the given id number as borrowed by the patron with the given patron id,
     *  if this book is available,
     *  the given patron isn't already borrowing the maximal number of books allowed,
     *  and if the patron will enjoy this book.
     *  @param bookId The id number of the book to borrow.
     *  @param patronId The id number of the patron that will borrow the book.
     *  @return true if the book was borrowed successfully, false otherwise.
    * */
    boolean borrowBook(int bookId, int patronId) {

        if ((!isBookIdValid(bookId)) || ((!isPatronIdValid(patronId)))) {
            return false;
        }

        if (books[bookId].getCurrentBorrowerId() == -1) {

            if ((!registeredPatrons[patronId].willEnjoyBook(books[bookId]))) {
                return false;
            }

            if (borrowedBooksCapacity <= registeredPatrons[patronId].borrowedBooks) {

                return false;
            }

            //borrow the book
            books[bookId].setBorrowerId(patronId);
            registeredPatrons[patronId].borrowedBooks += 1;

            return true;
        }

        return false;
    }

    /**
     * Return the given book.
     *
     * @param bookId The id number of the book to return.
     */
    void returnBook(int bookId) {

        int borrowerID = books[bookId].currentBorrowerId;
        if (borrowerID != -1) {
            registeredPatrons[borrowerID].borrowedBooks -= 1;
            books[bookId].currentBorrowerId = -1;
        }
    }

    /**
     * Suggest the patron with the given id the book he will enjoy the most,
     * out of all available books he will enjoy,
     * if any such exist.
     *
     * @param patronId The id number of the patron to suggest the book to.
     * @return The available book the patron with the given id will enjoy the most.
     * Null if no book is available.
     */
    Book suggestBookToPatron(int patronId) {
        Book suggestedBook = null;
        int suggestedBookValue = -1;

        // find the the most suited book in the library for thr current patron
        for (int i = 0; i < bookCapacity; i++) {

            if (isBookAvailable(i) && suggestedBookValue < books[i].getLiteraryValue()) {
                if (registeredPatrons[patronId].willEnjoyBook(books[i])) {
                    suggestedBook = books[i];
                    suggestedBookValue = books[i].getLiteraryValue();
                }
            }
        }
        return suggestedBook;
    }

}
