import java.util.Scanner;

// This is where the program starts
// Shows the menu and handles what the user wants to do
public class Main {
    private static Library library;
    private static Scanner scanner;

    public static void main(String[] args) {
        library = new Library();
        scanner = new Scanner(System.in);

        System.out.println("====================================");
        System.out.println("  LIBRARY MANAGEMENT SYSTEM");
        System.out.println("====================================\n");

        boolean running = true;

        // Keep showing the menu until user wants to exit
        while (running) {
            displayMenu();

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                System.out.println();

                // Do what the user asked for
                switch (choice) {
                    case 1:
                        addNewBook();
                        break;
                    case 2:
                        removeBook();
                        break;
                    case 3:
                        searchBookById();
                        break;
                    case 4:
                        searchBookByTitle();
                        break;
                    case 5:
                        listAllBooks();
                        break;
                    case 6:
                        listBooksAlphabetically();
                        break;
                    case 7:
                        requestBorrow();
                        break;
                    case 8:
                        processBorrowRequest();
                        break;
                    case 9:
                        borrowBook();
                        break;
                    case 10:
                        returnBook();
                        break;
                    case 11:
                        undoLastAction();
                        break;
                    case 12:
                        displayBorrowQueue();
                        break;
                    case 0:
                        exitSystem();
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }

            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    // Show the menu options
    private static void displayMenu() {
        System.out.println("\n====================================");
        System.out.println("    LIBRARY MANAGEMENT SYSTEM");
        System.out.println("====================================");
        System.out.println("1.  Add a new book");
        System.out.println("2.  Remove a book");
        System.out.println("3.  Search book by ID (Dynamic Array)");
        System.out.println("4.  Search book by title (BST)");
        System.out.println("5.  List all books (Dynamic Array)");
        System.out.println("6.  List all books alphabetically (BST)");
        System.out.println("7.  Request to borrow a book (Queue)");
        System.out.println("8.  Process borrow requests (Queue)");
        System.out.println("9.  Borrow a book");
        System.out.println("10. Return a book");
        System.out.println("11. Undo last action (Stack)");
        System.out.println("12. Display borrow queue");
        System.out.println("0.  Exit");
        System.out.println("====================================");
        System.out.print("Enter your choice: ");
    }

    // Ask user for book details and add it
    private static void addNewBook() {
        System.out.println("=== Add New Book ===");

        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        System.out.print("Enter book author: ");
        String author = scanner.nextLine();

        library.addBook(title, author);
    }

    // Ask for book ID and remove it
    private static void removeBook() {
        System.out.println("=== Remove Book ===");

        System.out.print("Enter book ID to remove: ");
        int bookId = Integer.parseInt(scanner.nextLine());

        library.removeBook(bookId);
    }

    // Search for a book using its ID number
    private static void searchBookById() {
        System.out.println("=== Search Book by ID ===");

        System.out.print("Enter book ID: ");
        int bookId = Integer.parseInt(scanner.nextLine());

        library.searchBookById(bookId);
    }

    // Search for a book using its title
    private static void searchBookByTitle() {
        System.out.println("=== Search Book by Title ===");

        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        library.searchBookByTitle(title);
    }

    // Show all books (in the order they were added)
    private static void listAllBooks() {
        library.listAllBooks();
    }

    // Show all books sorted A to Z by title
    private static void listBooksAlphabetically() {
        library.listBooksAlphabetically();
    }

    // Add a request to the waiting list
    private static void requestBorrow() {
        System.out.println("=== Request to Borrow Book ===");

        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();

        System.out.print("Enter book ID: ");
        int bookId = Integer.parseInt(scanner.nextLine());

        library.requestBorrow(userName, bookId);
    }

    // Help the next person in the waiting list
    private static void processBorrowRequest() {
        System.out.println("=== Process Borrow Request ===");
        library.processBorrowRequest();
    }

    // Let someone borrow a book right now
    private static void borrowBook() {
        System.out.println("=== Borrow Book ===");

        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();

        System.out.print("Enter book ID: ");
        int bookId = Integer.parseInt(scanner.nextLine());

        library.borrowBook(userName, bookId);
    }

    // Someone is bringing back a book
    private static void returnBook() {
        System.out.println("=== Return Book ===");

        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();

        System.out.print("Enter book ID: ");
        int bookId = Integer.parseInt(scanner.nextLine());

        library.returnBook(userName, bookId);
    }

    // Undo the last thing that happened
    private static void undoLastAction() {
        System.out.println("=== Undo Last Action ===");
        library.undoLastAction();
    }

    // Show who's waiting to borrow books
    private static void displayBorrowQueue() {
        library.displayBorrowQueue();
    }

    // Save everything and close the program
    private static void exitSystem() {
        System.out.println("=== Exiting System ===");
        library.saveBooksToFile();
        System.out.println("Thank you for using the Library Management System!");
        System.out.println("Goodbye!");
    }
}
