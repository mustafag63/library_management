// This class keeps track of what actions users do (borrow or return books)
// We use this to undo actions if needed
public class Action {
    private String type; // Can be "BORROW" or "RETURN"
    private int bookId; // Which book was involved
    private String userName; // Who did the action

    // Create a new action record
    // Time Complexity: O(1)
    public Action(String type, int bookId, String userName) {
        this.type = type;
        this.bookId = bookId;
        this.userName = userName;
    }

    // Get what type of action this was
    // Time Complexity: O(1)
    public String getType() {
        return type;
    }

    // Get which book was involved
    // Time Complexity: O(1)
    public int getBookId() {
        return bookId;
    }

    // Get who did the action
    // Time Complexity: O(1)
    public String getUserName() {
        return userName;
    }

    // Show the action in a readable way
    // Time Complexity: O(1)
    @Override
    public String toString() {
        return type + " - Book ID: " + bookId + " by " + userName;
    }
}
