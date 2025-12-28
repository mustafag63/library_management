// This class represents someone wanting to borrow a book
// It goes into the waiting queue
public class BorrowRequest {
    private String userName; // Who wants to borrow
    private int bookId; // Which book they want
    
    // Create a new borrow request
    // Time Complexity: O(1)
    public BorrowRequest(String userName, int bookId) {
        this.userName = userName;
        this.bookId = bookId;
    }
    
    // Get the person's name
    // Time Complexity: O(1)
    public String getUserName() {
        return userName;
    }
    
    // Get which book they want
    // Time Complexity: O(1)
    public int getBookId() {
        return bookId;
    }
    
    // Show the request in a readable format
    // Time Complexity: O(1)
    @Override
    public String toString() {
        return "User: " + userName + " | Book ID: " + bookId;
    }
}

