// Queue - like waiting in line at a store
// First person in line is the first person served (FIFO)
public class Queue<T> {
    
    // Each item in the queue is a Node
    private class Node {
        T data;
        Node next;
        
        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
    
    private Node front; // Start of the line
    private Node rear; // End of the line
    private int size;
    
    // Create an empty queue
    // Time Complexity: O(1)
    public Queue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }
    
    // Add someone to the back of the line
    // Time Complexity: O(1)
    public void enqueue(T data) {
        Node newNode = new Node(data);
        
        if (isEmpty()) {
            // First person in line
            front = newNode;
            rear = newNode;
        } else {
            // Add to the back
            rear.next = newNode;
            rear = newNode;
        }
        
        size++;
    }
    
    // Remove and serve the person at the front
    // Time Complexity: O(1)
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty. Cannot dequeue.");
        }
        
        T data = front.data;
        front = front.next;
        size--;
        
        // If queue is now empty
        if (front == null) {
            rear = null;
        }
        
        return data;
    }
    
    // Look at who's at the front without removing them
    // Time Complexity: O(1)
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty. Cannot peek.");
        }
        return front.data;
    }
    
    // Is the queue empty?
    // Time Complexity: O(1)
    public boolean isEmpty() {
        return front == null;
    }
    
    // How many people in line?
    // Time Complexity: O(1)
    public int size() {
        return size;
    }
    
    // Show everyone in the queue
    // Time Complexity: O(n)
    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }
        
        Node current = front;
        System.out.print("Front -> ");
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("Rear");
    }
    
    // Clear the entire queue
    // Time Complexity: O(1)
    public void clear() {
        front = null;
        rear = null;
        size = 0;
    }
}

