================================================================================
                    LIBRARY MANAGEMENT SYSTEM
                        COMP203 Project
================================================================================

PROJECT INFORMATION
--------------------------------------------------------------------------------
Course:         COMP203 - Data Structures
Project:        Library Management System
Language:       Java
Date:           December 2025

GROUP MEMBERS
--------------------------------------------------------------------------------
Muhammet Gümüş      2311051021
Mustafa Göçmen      2311051039
Tuna Öcal           2311051049


================================================================================
PROJECT OVERVIEW
================================================================================

This is a console-based Library Management System implemented in Java that
demonstrates the practical application of four fundamental data structures:

1. Dynamic Array - For storing and managing the book catalog
2. Binary Search Tree (BST) - For efficient searching and alphabetical sorting
3. Queue - For managing borrow requests (FIFO)
4. Stack - For undo functionality (LIFO)

================================================================================
FILE STRUCTURE
================================================================================

Core Java Files:
----------------
1. Book.java
   - Represents a book with ID, title, author, and availability status
   - Provides methods for string representation and file I/O

2. DynamicArray.java
   - Custom implementation of a dynamic array
   - Automatically grows when capacity is reached
   - Operations: add(), get(), remove(), size()
   - Time Complexity: O(1) amortized for add, O(n) for remove

3. BST.java
   - Binary Search Tree implementation for books organized by title
   - Operations: insert(), search(), inOrderTraversal(), delete()
   - Time Complexity: O(log n) average case, O(n) worst case

4. Queue.java
   - Queue implementation using linked list
   - FIFO (First In, First Out) structure
   - Operations: enqueue(), dequeue(), peek()
   - Time Complexity: O(1) for all operations

5. Stack.java
   - Stack implementation using linked list
   - LIFO (Last In, First Out) structure
   - Operations: push(), pop(), peek()
   - Time Complexity: O(1) for all operations

6. BorrowRequest.java
   - Represents a borrow request (userName, bookId)
   - Used in the Queue for managing waiting list

7. Action.java
   - Represents an action (BORROW or RETURN)
   - Used in the Stack for undo functionality

8. Library.java
   - Main library management class
   - Integrates all data structures
   - Provides all library operations

9. Main.java
   - Console-based user interface
   - Menu-driven system
   - Entry point of the application

Data Files:
-----------
1. books.txt
   - Stores book data (ID, title, author, availability)
   - Format: ID,Title,Author,IsAvailable
   - Automatically loaded on startup

2. users.txt
   - Sample user data for reference
   - Format: ID,Name,Email

================================================================================
FEATURES & DATA STRUCTURES MAPPING
================================================================================

1. DYNAMIC ARRAY OPERATIONS
   ------------------------
   - Add a new book
   - Remove a book
   - Search book by ID (linear search)
   - List all books in order of addition
   
   Why Dynamic Array?
   - Unlike fixed arrays, it can grow/shrink dynamically
   - Maintains insertion order
   - Direct index access O(1)

2. BINARY SEARCH TREE OPERATIONS
   -----------------------------
   - Search book by title (efficient search)
   - List books alphabetically (in-order traversal)
   
   Why BST?
   - Efficient searching O(log n) average case
   - Natural alphabetical ordering via in-order traversal
   - Better than linear search for large datasets

3. QUEUE OPERATIONS
   ----------------
   - Request to borrow a book (add to waiting list)
   - Process borrow requests (serve in FIFO order)
   - Display pending requests
   
   Why Queue?
   - FIFO principle matches real-world waiting lists
   - Fair: first come, first served
   - O(1) enqueue and dequeue operations

4. STACK OPERATIONS
   ----------------
   - Borrow a book (push action to stack)
   - Return a book (push action to stack)
   - Undo last action (pop from stack and revert)
   
   Why Stack?
   - LIFO principle perfect for undo functionality
   - Most recent action is undone first
   - O(1) push and pop operations

================================================================================
HOW TO COMPILE AND RUN
================================================================================

Method 1: Using Command Line
-----------------------------
1. Open terminal/command prompt
2. Navigate to the project directory:
   cd /path/to/data_project

3. Compile all Java files:
   javac *.java

4. Run the program:
   java Main

Method 2: Using IDE (Eclipse, IntelliJ, etc.)
---------------------------------------------
1. Import the project folder into your IDE
2. Ensure all .java files are in the same package/directory
3. Run Main.java

Method 3: Using Visual Studio Code
----------------------------------
1. Open the project folder in VS Code
2. Install Java Extension Pack if not already installed
3. Open Main.java
4. Click "Run" button or press F5

================================================================================
HOW TO USE THE SYSTEM
================================================================================

Main Menu Options:
------------------
1.  Add a new book - Add a book to the catalog
2.  Remove a book - Remove a book by ID
3.  Search book by ID - Find a book using Dynamic Array
4.  Search book by title - Find a book using BST
5.  List all books - Display all books in insertion order
6.  List alphabetically - Display books sorted by title
7.  Request to borrow - Add a request to the queue
8.  Process request - Process the next request in queue
9.  Borrow a book - Borrow a book directly
10. Return a book - Return a borrowed book
11. Undo last action - Undo the last borrow/return
12. Display queue - Show all pending borrow requests
0.  Exit - Save and exit the system

Sample Usage Flow:
------------------
1. Start the program
2. View all books (Option 5)
3. Search for a specific book (Option 3 or 4)
4. Borrow a book (Option 9)
5. View updated book status (Option 5)
6. Return the book (Option 10)
7. If needed, undo the action (Option 11)

================================================================================
DATA PERSISTENCE
================================================================================

The system automatically:
- Loads books from books.txt on startup
- Saves books to books.txt on exit
- Preserves book availability status

File Format:
------------
books.txt: ID,Title,Author,IsAvailable
Example: 1,The Great Gatsby,F. Scott Fitzgerald,true

================================================================================
TIME COMPLEXITY ANALYSIS
================================================================================

Operation                          | Data Structure | Time Complexity
-----------------------------------|----------------|------------------
Add book to catalog                | Dynamic Array  | O(1) amortized
Remove book from catalog           | Dynamic Array  | O(n)
Search book by ID                  | Dynamic Array  | O(n)
Search book by title               | BST            | O(log n) avg, O(n) worst
List books alphabetically          | BST            | O(n)
Add borrow request                 | Queue          | O(1)
Process borrow request             | Queue          | O(1)
Borrow/Return book                 | Stack          | O(1)
Undo last action                   | Stack          | O(1)

================================================================================
DESIGN DECISIONS
================================================================================

1. Custom Dynamic Array Implementation
   - No use of Java's ArrayList to understand resizing mechanism
   - Doubles capacity when full
   - Shrinks when usage drops to 25%

2. BST for Title Search
   - Case-insensitive comparison for user convenience
   - Handles duplicate titles by inserting to right subtree
   - In-order traversal provides natural alphabetical ordering

3. Queue for Fairness
   - FIFO ensures fair distribution of books
   - Requests automatically processed when books available

4. Stack for Undo
   - Only stores the most recent action type and book ID
   - Undo reverses the availability status
   - Limited to borrow/return operations

5. Separation of Concerns
   - Each data structure in its own class
   - Library class orchestrates all operations
   - Main class handles only user interaction

================================================================================
TESTING RECOMMENDATIONS
================================================================================

1. Test Dynamic Array:
   - Add multiple books (test resizing)
   - Remove books from beginning, middle, end
   - Verify size updates correctly

2. Test BST:
   - Search for existing and non-existing titles
   - Verify alphabetical ordering
   - Test with books having similar titles

3. Test Queue:
   - Add multiple requests
   - Process requests in order
   - Verify FIFO behavior

4. Test Stack:
   - Borrow and return multiple books
   - Undo actions and verify state reversal
   - Test undo on empty stack

5. Integration Testing:
   - Borrow all copies of a book
   - Add requests to queue
   - Process queue after returns
   - Test file persistence across sessions

================================================================================
POSSIBLE EXTENSIONS
================================================================================

1. Add user management system
2. Implement due dates for borrowed books
3. Add late fee calculation
4. Support multiple copies of the same book
5. Implement reservation system
6. Add book categories and filters
7. Implement search by author
8. Add database integration (MySQL, PostgreSQL)
9. Create a GUI interface
10. Add reporting and statistics

================================================================================
TROUBLESHOOTING
================================================================================

Problem: Books not loading on startup
Solution: Ensure books.txt is in the same directory as Main.java

Problem: Compilation errors
Solution: Ensure all .java files are in the same directory
          Use: javac *.java to compile all at once

Problem: Cannot undo action
Solution: Undo only works for borrow/return actions, not for add/remove

Problem: Queue not processing request
Solution: Book must be available for request to be processed

================================================================================
REFERENCES & RESOURCES
================================================================================

1. Data Structures and Algorithms in Java - Robert Lafore
2. Introduction to Algorithms - CLRS
3. Java Documentation: https://docs.oracle.com/javase/

================================================================================
NOTES
================================================================================

- All methods include time complexity comments
- Code is well-commented for easy understanding
- Follows Java naming conventions
- Error handling implemented for user inputs
- File I/O for data persistence

================================================================================
                            END OF README
================================================================================

