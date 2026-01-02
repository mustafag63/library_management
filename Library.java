import java.io.*;

// This is the main library system
// It uses 4 different data structures for different tasks
public class Library {
    private DynamicArray<Book> catalog; // All books (keeps order we added them)
    private BST bst; // All books (organized by title for fast search)
    private Queue<BorrowRequest> borrowQueue; // People waiting to borrow books
    private Stack<Action> actionStack; // Remember actions so we can undo them
    private int nextBookId;

    private static final String BOOKS_FILE = "books.txt";

    // Set up a new library
    // Time Complexity: O(n) where n is number of books loaded from file
    public Library() {
        this.catalog = new DynamicArray<>();
        this.bst = new BST();
        this.borrowQueue = new Queue<>();
        this.actionStack = new Stack<>();
        this.nextBookId = 1;
        loadBooksFromFile(); // Load books from file if it exists
    }

    // Add a new book to the library
    // Time Complexity: O(log n) for BST insertion + O(1) amortized for DynamicArray
    public void addBook(String title, String author) {
        Book book = new Book(nextBookId++, title, author);
        catalog.add(book); // Add to array
        bst.insert(book); // Also add to tree
        System.out.println("Book added successfully: " + book);
    }

    // Remove a book from the library
    // Time Complexity: O(n) for searching + removing from array + O(log n) for BST
    // deletion
    public void removeBook(int bookId) {
        Book bookToRemove = null;
        int indexToRemove = -1;

        // Look for the book
        for (int i = 0; i < catalog.size(); i++) {
            if (catalog.get(i).getId() == bookId) {
                bookToRemove = catalog.get(i);
                indexToRemove = i;
                break;
            }
        }

        if (bookToRemove == null) {
            System.out.println("Book with ID " + bookId + " not found.");
            return;
        }

        // Remove from both places
        catalog.remove(indexToRemove);
        bst.delete(bookToRemove.getTitle());
        System.out.println("Book removed successfully: " + bookToRemove);
    }

    // Search for a book by its ID number (looks through the array)
    // Time Complexity: O(n) - linear search through array
    public void searchBookById(int bookId) {
        for (int i = 0; i < catalog.size(); i++) {
            Book book = catalog.get(i);
            if (book.getId() == bookId) {
                System.out.println("Book found: " + book);
                return;
            }
        }
        System.out.println("Book with ID " + bookId + " not found.");
    }

    // Search for a book by title (uses the tree for faster search)
    // Time Complexity: O(log n) average case for BST search
    public void searchBookByTitle(String title) {
        Book book = bst.search(title);
        if (book != null) {
            System.out.println("Book found: " + book);
        } else {
            System.out.println("Book with title '" + title + "' not found.");
        }
    }

    // Show all books in the order we added them
    // Time Complexity: O(n)
    public void listAllBooks() {
        if (catalog.isEmpty()) {
            System.out.println("No books in the catalog.");
            return;
        }

        System.out.println("\n=== All Books (Order of Addition) ===");
        for (int i = 0; i < catalog.size(); i++) {
            System.out.println(catalog.get(i));
        }
    }

    // Show all books sorted alphabetically by title
    // Time Complexity: O(n)
    public void listBooksAlphabetically() {
        bst.inOrderTraversal();
    }

    // Someone wants to borrow a book - add them to the waiting list
    // Time Complexity: O(n) for finding book + O(1) for enqueue
    public void requestBorrow(String userName, int bookId) {
        // Make sure the book exists
        Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("Book with ID " + bookId + " not found.");
            return;
        }

        BorrowRequest request = new BorrowRequest(userName, bookId);
        borrowQueue.enqueue(request);
        System.out.println("Borrow request added to queue: " + request);
    }

    // Process the next person in the waiting list
    // Time Complexity: O(n) for finding book + O(1) for queue operations
    public void processBorrowRequest() {
        if (borrowQueue.isEmpty()) {
            System.out.println("No borrow requests in queue.");
            return;
        }

        BorrowRequest request = borrowQueue.dequeue();
        Book book = findBookById(request.getBookId());

        if (book == null) {
            System.out.println("Book no longer exists.");
            return;
        }

        if (!book.isAvailable()) {
            System.out.println("Book is currently borrowed. Request cannot be processed.");
            System.out.println("Adding request back to queue...");
            borrowQueue.enqueue(request);
            return;
        }

        // Let them borrow it
        book.setAvailable(false);
        Action action = new Action("BORROW", book.getId(), request.getUserName());
        actionStack.push(action);
        System.out.println("Processed: " + request.getUserName() + " borrowed '" + book.getTitle() + "'");
    }

    // Show who's waiting to borrow books
    // Time Complexity: O(n)
    public void displayBorrowQueue() {
        if (borrowQueue.isEmpty()) {
            System.out.println("No pending borrow requests.");
            return;
        }

        System.out.println("\n=== Pending Borrow Requests ===");
        System.out.println("Queue size: " + borrowQueue.size());
        borrowQueue.display();
    }

    // Let someone borrow a book right now (skip the queue)
    // Time Complexity: O(n) for finding book + O(1) for stack push
    public void borrowBook(String userName, int bookId) {
        Book book = findBookById(bookId);

        if (book == null) {
            System.out.println("Book with ID " + bookId + " not found.");
            return;
        }

        if (!book.isAvailable()) {
            System.out.println("Book is currently borrowed.");
            return;
        }

        book.setAvailable(false);
        Action action = new Action("BORROW", bookId, userName);
        actionStack.push(action);
        System.out.println(userName + " borrowed '" + book.getTitle() + "' successfully.");
    }

    // Someone is returning a book
    // Time Complexity: O(n) for finding book + O(1) for stack push
    public void returnBook(String userName, int bookId) {
        Book book = findBookById(bookId);

        if (book == null) {
            System.out.println("Book with ID " + bookId + " not found.");
            return;
        }

        if (book.isAvailable()) {
            System.out.println("Book is not currently borrowed.");
            return;
        }

        book.setAvailable(true);
        Action action = new Action("RETURN", bookId, userName);
        actionStack.push(action);
        System.out.println(userName + " returned '" + book.getTitle() + "' successfully.");
    }

    // Undo the last thing that happened (borrow or return)
    // Time Complexity: O(n) for finding book + O(1) for stack pop
    public void undoLastAction() {
        if (actionStack.isEmpty()) {
            System.out.println("No actions to undo.");
            return;
        }

        Action action = actionStack.pop();
        Book book = findBookById(action.getBookId());

        if (book == null) {
            System.out.println("Book no longer exists. Cannot undo.");
            return;
        }

        if (action.getType().equals("BORROW")) {
            // They borrowed it, so undo = return it
            book.setAvailable(true);
            System.out.println("Undone: " + action.getUserName() + "'s borrow of '" + book.getTitle() + "'");
        } else if (action.getType().equals("RETURN")) {
            // They returned it, so undo = borrow it again
            book.setAvailable(false);
            System.out.println("Undone: " + action.getUserName() + "'s return of '" + book.getTitle() + "'");
        }
    }

    // Helper method to find a book by its ID
    // Time Complexity: O(n)
    private Book findBookById(int bookId) {
        for (int i = 0; i < catalog.size(); i++) {
            Book book = catalog.get(i);
            if (book.getId() == bookId) {
                return book;
            }
        }
        return null;
    }

    // Load books from the file when we start
    // Time Complexity: O(n * log n) where n is number of books
    private void loadBooksFromFile() {
        File file = new File(BOOKS_FILE);
        if (!file.exists()) {
            System.out.println("Books file not found. Starting with empty catalog.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty())
                    continue;

                Book book = Book.fromFileString(line);
                catalog.add(book);
                bst.insert(book);

                // Make sure we don't reuse book IDs
                if (book.getId() >= nextBookId) {
                    nextBookId = book.getId() + 1;
                }
            }
            System.out.println("Loaded " + catalog.size() + " books from file.");
        } catch (IOException e) {
            System.out.println("Error loading books from file: " + e.getMessage());
        }
    }

    // Save all books to the file
    // Time Complexity: O(n)
    public void saveBooksToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(BOOKS_FILE))) {
            for (int i = 0; i < catalog.size(); i++) {
                writer.println(catalog.get(i).toFileString());
            }
            System.out.println("Books saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error saving books to file: " + e.getMessage());
        }
    }

    // How many books do we have?
    // Time Complexity: O(1)
    public int getCatalogSize() {
        return catalog.size();
    }
}
