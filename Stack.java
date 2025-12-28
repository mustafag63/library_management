// Stack - like a pile of plates
// Last plate you put on top is the first one you take off (LIFO)
public class Stack<T> {
    
    // Each item in the stack is a Node
    private class Node {
        T data;
        Node next;
        
        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
    
    private Node top; // The top of the stack
    private int size;
    
    // Create an empty stack
    // Time Complexity: O(1)
    public Stack() {
        this.top = null;
        this.size = 0;
    }
    
    // Put something on top of the stack
    // Time Complexity: O(1)
    public void push(T data) {
        Node newNode = new Node(data);
        newNode.next = top;
        top = newNode;
        size++;
    }
    
    // Take the top item off the stack
    // Time Complexity: O(1)
    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty. Cannot pop.");
        }
        
        T data = top.data;
        top = top.next;
        size--;
        return data;
    }
    
    // Look at the top item without removing it
    // Time Complexity: O(1)
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty. Cannot peek.");
        }
        return top.data;
    }
    
    // Is the stack empty?
    // Time Complexity: O(1)
    public boolean isEmpty() {
        return top == null;
    }
    
    // How many items in the stack?
    // Time Complexity: O(1)
    public int size() {
        return size;
    }
    
    // Show all items in the stack
    // Time Complexity: O(n)
    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return;
        }
        
        Node current = top;
        System.out.println("=== Stack (Top to Bottom) ===");
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }
    
    // Clear everything from the stack
    // Time Complexity: O(1)
    public void clear() {
        top = null;
        size = 0;
    }
}

