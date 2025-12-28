// This class represents a book in our library
// Each book has an ID, title, author, and availability status
public class Book {
    private int id;
    private String title;
    private String author;
    private boolean isAvailable;

    // Create a new book (new books start as available)
    // Time Complexity: O(1)
    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    // Get the book's ID
    // Time Complexity: O(1)
    public int getId() {
        return id;
    }

    // Get the book's title
    // Time Complexity: O(1)
    public String getTitle() {
        return title;
    }

    // Get the book's author
    // Time Complexity: O(1)
    public String getAuthor() {
        return author;
    }

    // Check if the book is available to borrow
    // Time Complexity: O(1)
    public boolean isAvailable() {
        return isAvailable;
    }

    // Change whether the book is available or not
    // Time Complexity: O(1)
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    // Show the book's info in a nice format
    // Time Complexity: O(1)
    @Override
    public String toString() {
        String status = isAvailable ? "Available" : "Borrowed";
        return String.format("ID: %d | Title: %s | Author: %s | Status: %s", 
                             id, title, author, status);
    }

    // Format the book data for saving to a file
    // Time Complexity: O(1)
    public String toFileString() {
        return id + "," + title + "," + author + "," + isAvailable;
    }

    // Create a book from a line read from the file
    // Time Complexity: O(1)
    public static Book fromFileString(String line) {
        String[] parts = line.split(",");
        Book book = new Book(Integer.parseInt(parts[0]), parts[1], parts[2]);
        book.setAvailable(Boolean.parseBoolean(parts[3]));
        return book;
    }
}

