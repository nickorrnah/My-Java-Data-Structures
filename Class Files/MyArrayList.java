package com.company;


public class MyArrayList<Type extends Comparable<Type>> {

    public static void main(String[] args) {

    }

    // Fields
    long comparisons;   // Tracks the number of comparisons done.
    private int capacity;       // Maximum size.
    private int size;           // Number of elements currently stored in the arrayList.
    Object[] list;

    // Constructor
    // Constructs a new MyArrayList Object of specified type.
    public MyArrayList() {

        this.comparisons = 0;
        this.capacity = 16;
        this.size = 0;
        this.list = (Type[]) new Comparable[capacity];

    }

    // Insert
    // Inserts the item at position index.
    // Shuffles all other elements down one position.
    public void insert(Type item, int index) {

        if (this.size + 1 >= this.capacity) { resize(); }

        if (index == 0 && this.size == 0) {

            this.list[index] = item;
            this.size++;

        } else if (index < 0 || (index > this.size) || (this.list[index - 1] == null)) {

            // If index is out of bounds, do nothing.

        } else {

            this.size++;
            Type temp;
            Type temp2 = item;

            for (int i = index; i < this.size; i++) {

                temp = (Type)this.list[i];
                this.list[i] = temp2;
                temp2 = temp;
            }

            this.list[this.size] = temp2;

        }

    }

    // Remove
    // Removes the element at the specified index.
    // Returns the element that was removed.
    // Elements after the removed element are shuffled down into place.
    // If the specified index is out of bounds; returns null.
    public Type remove(int index) {
        if (index < 0 || index >= this.size) {
            return null;
        } else {
            Type removed = (Type)this.list[index];

            for (int i = index; i < this.size - 1; i++) {
                this.list[i] = this.list[i + 1];
            }

            this.list[size - 1] = null;
            this.size--;
            return removed;
        }
    }

    // Contains
    // Searches the list to see if the array contains a specified item.
    // Returns true if the item is in the list and false otherwise.
    public boolean contains(Type item) {

        for (int i = 0; i < this.size; i++) {
            comparisons++;
            if (get(i).compareTo(item) == 0) { return true; }
        }

        return false;

    }

    // Index Of
    // Searches through the array to see if it contains the specified item.
    // Returns the index of the item if it is found.
    // Returns -1 otherwise.
    public int indexOf(Type item) {

        for (int i = 0; i < this.size; i++) {
            if (get(i).compareTo(item) == 0) { return i; }
        }
        return -1;
    }

    // Get
    // Returns the element stored at the specified index.
    // Returns null if the given index is out of bounds.
    public Type get(int index) {
        if (index < 0 || index >= this.size) {
            return null;
        } else {
            return ((Type)this.list[index]);
        }
    }

    // Set
    // Updates the element stored at the specified index to be the specified item.
    // Does nothing if the given index is out of bounds.
    public void set(int index, Type item) {
        if (index < 0 || index >= this.size) {
            // Do nothing.
        } else {
            this.list[index] = item;
        }
    }

    // Size
    // Returns the number of elements in the array.
    public int size() {
        return this.size;
    }

    // Is Empty
    // Returns true if the array contains no elements.
    // Otherwise; returns false.
    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        }
        return false;
    }

    // toString
    // Returns a string that has the contents of the list.
    // separated by commas and enclosed in brackets.
    public String toString() {
        String result = "[";
        for (int i = 0; i < size; i++) {

            result += this.list[i];

            if (i < this.size - 1) {
                result += ", ";
            }

        }
        result += "]";
        return result;
    }

    // resize
    // Doubles the capacity of the list.
    // Called by insert automatically when necessary.
    public void resize() {
        Comparable[] resizedArray = (Type[]) new Comparable[2 * capacity];

        for (int i = 0; i < this.size; i++) {
            resizedArray[i] = get(i);
        }

        this.list = resizedArray;
        this.capacity *= 2;

    }

    // Sort
    // Sorts the list in ascending order.
    // Sorts using insertion sort.
    public void sort() {
        for (int i = 1; i < this.size; i++) {
            for (int j = i; j > 0 && get(j).compareTo(get(j - 1)) <= -1; j--) {
                swap(j, j - 1);
            }
        }
    }

    // Swap
    // Swaps the indexes of the two given elements.
    public void swap(int indexOne, int indexTwo){
        Type temp = get(indexOne);
        Type temp2 = get(indexTwo);

        set(indexTwo, temp);
        set(indexOne, temp2);

    }

}
