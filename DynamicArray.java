// This is our custom array that can grow automatically
// Unlike normal arrays, this one gets bigger when we add too many items
public class DynamicArray<T> {
    private Object[] array;
    private int size; // How many items we currently have
    private int capacity; // How many items we can hold
    private static final int INITIAL_CAPACITY = 10;

    // Start with an empty array
    // Time Complexity: O(1)
    public DynamicArray() {
        this.capacity = INITIAL_CAPACITY;
        this.array = new Object[capacity];
        this.size = 0;
    }

    // Add a new item to the array
    // If array is full, we make it bigger first
    // Time Complexity: O(1) amortized, O(n) worst case when resizing
    public void add(T element) {
        if (size == capacity) {
            resize(); // Make the array bigger
        }
        array[size++] = element;
    }

    // Get an item at a specific position
    // Time Complexity: O(1)
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) array[index];
    }

    // Remove an item at a specific position
    // Time Complexity: O(n) worst case - need to shift elements
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        T removedElement = (T) array[index];
        
        // Move all items after this one to the left
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        
        array[--size] = null;
        
        // If array is mostly empty, make it smaller to save memory
        if (size > 0 && size == capacity / 4) {
            shrink();
        }
        
        return removedElement;
    }

    // How many items do we have?
    // Time Complexity: O(1)
    public int size() {
        return size;
    }

    // Is the array empty?
    // Time Complexity: O(1)
    public boolean isEmpty() {
        return size == 0;
    }

    // Make the array bigger (double the size)
    // Time Complexity: O(n) - need to copy all elements
    private void resize() {
        capacity *= 2;
        Object[] newArray = new Object[capacity];
        
        // Copy everything to the new bigger array
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        
        array = newArray;
    }

    // Make the array smaller (half the size)
    // Time Complexity: O(n) - need to copy all elements
    private void shrink() {
        capacity /= 2;
        Object[] newArray = new Object[capacity];
        
        // Copy everything to the new smaller array
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        
        array = newArray;
    }

    // Find where an item is located
    // Time Complexity: O(n) worst case - may need to check all elements
    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element)) {
                return i;
            }
        }
        return -1; // Not found
    }

    // Remove everything
    // Time Complexity: O(1)
    public void clear() {
        array = new Object[INITIAL_CAPACITY];
        size = 0;
        capacity = INITIAL_CAPACITY;
    }
}

