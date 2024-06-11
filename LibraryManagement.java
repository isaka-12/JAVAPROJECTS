package JAVAPROJECTS;

import java.util.*;
import java.util.logging.Logger;

// Book class for creating and initializing book object
class Book {
    private String bookTitle;
    private String name;
    private String iSBN;

    // Constructor for book object
    public Book(String bookTitle, String name, String iSBN) {
        this.bookTitle = bookTitle;
        this.name = name;
        this.iSBN = iSBN;
    }

    // Getter methods for making private fields accessible to other classes
    public String getBookTitle() {
        return bookTitle;
    }

    public String getName() {
        return name;
    }

    public String getISBN() {
        return iSBN;
    }


    // Displaying book details by overriding toString
    @Override
    public String toString() {
        return "Book [Book Title = " + bookTitle + ", Author Name = " + name + ", ISBN = " + iSBN  + "]";
    }
}

// Custom Exception for Book Not Found
class BookNotFoundException extends Exception {
    public BookNotFoundException(String message) {
        super(message);
    }
}

// Library class to manage list of book objects
class Library {
    private static final Logger logger = Logger.getLogger(Library.class.getName());
    private List<Book> books;
    private HashMap<String, Book> bookMap;

    // Constructing the array list
    public Library() {
        books = new ArrayList<>();
        bookMap = new HashMap<>();
    }

    // Adding books
    void addBook(Book book) {
        books.add(book);
        bookMap.put(book.getISBN(), book);
        System.out.println("New book added successfully!!! \n" + book);
        logger.info("Book added: " + book);
    }

    // Removing books
    void removeBook(String iSBN) throws BookNotFoundException {
        Book bookToRemove = null;
        for (Book book : books) {
            if (book.getISBN().equals(iSBN)) {
                bookToRemove = book;
                break;
            }
        }
        if (bookToRemove != null) {
            books.remove(bookToRemove);
            bookMap.remove(iSBN);
            System.out.println("Book removed successfully: " + bookToRemove);
            logger.info("Book removed: " + bookToRemove);
        } else {
            throw new BookNotFoundException("BOOK NOT FOUND with ISBN: " + iSBN);
        }
    }

    // Displaying books
    void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    // Searching for books
    public Book findBook(String isbn) {
        return bookMap.get(isbn);
    }

    // Getter for logger
    public Logger getLogger() {
        return logger;
    }
}

// Text-based interface with the main method
public class LibraryManagement {
    public static void main(String[] args) {
        Library library = new Library();

        try (Scanner myScanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("");
                System.out.println("===========LIBRARY MANAGEMENT SYSTEM=========");
                System.out.println("Choose services below");
                System.out.println("1. Add a Book");
                System.out.println("2. Find a Book");
                System.out.println("3. List all Books");
                System.out.println("4. Remove a Book");
                System.out.println("5. Exit");

                System.out.print("\nEnter Option: ");
                
                try {
                    int option = myScanner.nextInt();
                    myScanner.nextLine(); // consume newline

                    try {
                        switch (option) {
                            case 1:
                                System.out.print("Enter book title: ");
                                String bookTitle = myScanner.nextLine();
                                System.out.print("Enter author name: ");
                                String name = myScanner.nextLine();
                                System.out.print("Enter ISBN: ");
                                String iSBN = myScanner.nextLine();
                                Book book = new Book(bookTitle, name, iSBN);
                                library.addBook(book);
                                break;
                            case 2:
                                System.out.print("Enter ISBN to find: ");
                                String searchISBN = myScanner.nextLine();
                                Book foundBook = library.findBook(searchISBN);
                                if (foundBook != null) {
                                    System.out.println("Book found: " + foundBook);
                                } else {
                                    throw new BookNotFoundException("BOOK NOT FOUND with ISBN: " + searchISBN);
                                }
                                break;
                            case 3:
                                System.out.println("Listing all books:");
                                library.displayBooks();
                                break;
                            case 4:
                                System.out.print("Enter ISBN to remove: ");
                                String removeISBN = myScanner.nextLine();
                                library.removeBook(removeISBN);
                                break;
                            case 5:
                                System.out.println("Exiting...thanks for choosing our services");
                                return;
                            default:
                                System.out.println("Invalid option! Please choose again.");
                                break;
                        }
                    } catch (BookNotFoundException e) {
                        System.out.println(e.getMessage());
                        library.getLogger().severe(e.getMessage());
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a number.");
                    myScanner.nextLine(); // clear the invalid input
                }
            }
        }
    }
}
