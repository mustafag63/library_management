// Binary Search Tree - a smart way to organize books by title
// Books with titles that come earlier alphabetically go to the left
// Books with titles that come later go to the right
public class BST {
    
    // Each spot in the tree holds a book
    private class Node {
        Book book;
        Node left; // Books with earlier titles
        Node right; // Books with later titles
        
        Node(Book book) {
            this.book = book;
            this.left = null;
            this.right = null;
        }
    }
    
    private Node root; // The top of the tree
    
    // Start with an empty tree
    // Time Complexity: O(1)
    public BST() {
        this.root = null;
    }
    
    // Add a book to the tree
    // Time Complexity: O(log n) average case, O(n) worst case
    public void insert(Book book) {
        root = insertRec(root, book);
    }
    
    // Helper method to add a book (uses recursion)
    // Time Complexity: O(log n) average case, O(n) worst case
    private Node insertRec(Node node, Book book) {
        // Found an empty spot - put the book here
        if (node == null) {
            return new Node(book);
        }
        
        // Compare book titles (ignoring uppercase/lowercase)
        int comparison = book.getTitle().toLowerCase().compareTo(node.book.getTitle().toLowerCase());
        
        if (comparison < 0) {
            // This book comes earlier, go left
            node.left = insertRec(node.left, book);
        } else if (comparison > 0) {
            // This book comes later, go right
            node.right = insertRec(node.right, book);
        } else {
            // Same title - we'll add it to the right anyway
            node.right = insertRec(node.right, book);
        }
        
        return node;
    }
    
    // Find a book by its title
    // Time Complexity: O(log n) average case, O(n) worst case
    public Book search(String title) {
        return searchRec(root, title);
    }
    
    // Helper method to search (uses recursion)
    // Time Complexity: O(log n) average case, O(n) worst case
    private Book searchRec(Node node, String title) {
        // Reached the end - book not found
        if (node == null) {
            return null;
        }
        
        int comparison = title.toLowerCase().compareTo(node.book.getTitle().toLowerCase());
        
        if (comparison == 0) {
            // Found it!
            return node.book;
        } else if (comparison < 0) {
            // Look in the left side
            return searchRec(node.left, title);
        } else {
            // Look in the right side
            return searchRec(node.right, title);
        }
    }
    
    // Show all books in alphabetical order
    // Time Complexity: O(n) - visits all nodes
    public void inOrderTraversal() {
        if (root == null) {
            System.out.println("No books in the catalog.");
            return;
        }
        System.out.println("\n=== Books in Alphabetical Order ===");
        inOrderTraversalRec(root);
    }
    
    // Helper to show books alphabetically (uses recursion)
    // Time Complexity: O(n)
    private void inOrderTraversalRec(Node node) {
        if (node != null) {
            inOrderTraversalRec(node.left); // Show left side first
            System.out.println(node.book); // Then this book
            inOrderTraversalRec(node.right); // Then right side
        }
    }
    
    // Get all books as an array in alphabetical order
    // Time Complexity: O(n)
    public DynamicArray<Book> getBooksInOrder() {
        DynamicArray<Book> books = new DynamicArray<>();
        getBooksInOrderRec(root, books);
        return books;
    }
    
    // Helper to collect books in order
    // Time Complexity: O(n)
    private void getBooksInOrderRec(Node node, DynamicArray<Book> books) {
        if (node != null) {
            getBooksInOrderRec(node.left, books);
            books.add(node.book);
            getBooksInOrderRec(node.right, books);
        }
    }
    
    // Remove a book from the tree
    // Time Complexity: O(log n) average case, O(n) worst case
    public boolean delete(String title) {
        int sizeBefore = getSize();
        root = deleteRec(root, title);
        return getSize() < sizeBefore;
    }
    
    // Helper to remove a book (uses recursion)
    // Time Complexity: O(log n) average case, O(n) worst case
    private Node deleteRec(Node node, String title) {
        if (node == null) {
            return null;
        }
        
        int comparison = title.toLowerCase().compareTo(node.book.getTitle().toLowerCase());
        
        if (comparison < 0) {
            node.left = deleteRec(node.left, title);
        } else if (comparison > 0) {
            node.right = deleteRec(node.right, title);
        } else {
            // Found the book to delete
            
            // If it has one child or no children
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            
            // If it has two children, replace with the next book in order
            node.book = minValue(node.right);
            node.right = deleteRec(node.right, node.book.getTitle());
        }
        
        return node;
    }
    
    // Find the smallest book title in a part of the tree
    // Time Complexity: O(log n) average case, O(n) worst case
    private Book minValue(Node node) {
        Book minBook = node.book;
        while (node.left != null) {
            minBook = node.left.book;
            node = node.left;
        }
        return minBook;
    }
    
    // Count how many books are in the tree
    // Time Complexity: O(n)
    public int getSize() {
        return getSizeRec(root);
    }
    
    // Helper to count books
    // Time Complexity: O(n)
    private int getSizeRec(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + getSizeRec(node.left) + getSizeRec(node.right);
    }
    
    // Remove all books from the tree
    // Time Complexity: O(1)
    public void clear() {
        root = null;
    }
}

