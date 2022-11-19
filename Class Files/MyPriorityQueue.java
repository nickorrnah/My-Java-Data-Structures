package com.company;

public class MyPriorityQueue <Type extends Comparable<Type>> {

    public static void main(String[] args) {

    }

    // Constructor
    public MyPriorityQueue() {
        this.heap = new MyArrayList<>();
    }

    // Fields
    private MyArrayList<Type> heap = new MyArrayList<>();

    // Insert
    // Inserts the item at the end of the array list.
    // Calls bubbleUp to move the item to the correct location.
    public void insert(Type item) {

        heap.insert(item, heap.size());

        if (heap.size() > 1) {
            bubbleUp();
        }

    }

    // Remove Min
    // Removes the first element and corrects the invariant.
    // Returns the removed element.
    public Type removeMin() {

        Type temp = heap.get(0);                            // Swap the first and last elements in the heap.
        heap.set(0, heap.get(heap.size() - 1));
        heap.set(heap.size() - 1, temp);

        Type removed = heap.remove(heap.size() - 1);  // Remove the last element in the heap.

        if (!heap.isEmpty()) {                              // If the heap is not empty.
            sinkDown();
        }

        return removed;

    }

    // Min
    // Returns the first element
    public Type min() {
        return heap.get(0);
    }

    // Size
    // Returns the size of the heap.
    public int size() {
        return heap.size();
    }

    // Is Empty
    // Returns true if the heap is empty; otherwise returns false.
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    // To String
    // Returns the heap's toString.
    public String toString() {
        return heap.toString();
    }

    // BubbleUp
    // Shifts the last element up to a position where it belongs
    // to correct the heap invariant.
    private void bubbleUp() {

        int index = heap.size() - 1;

        while (heap.get(index).compareTo(heap.get(parent(index))) < 0) {      // If the last node is less than its parent.
            Type temp = heap.get(parent(index));
            heap.set(parent(index), heap.get(index));
            heap.set(index, temp);

            index = parent(index);
        }

    }

    // Sink Down
    // Shifts the first element down to a position where it belongs
    // to correct the heap invariant.
    private void sinkDown() {

        int index = 0;

        // While the parent is greater than either of its children.
        while ((heap.get(index).compareTo(heap.get(left(index))) > 0) || (heap.get(index).compareTo(heap.get(right(index))) > 0)) {

            // If the left child is the smaller child.
            if (heap.get(right(index)).compareTo(heap.get(left(index))) > 0) {
                Type temp = heap.get(index);                    // Swap the parent and the left child.
                heap.set(index, heap.get(left(index)));
                heap.set(left(index), temp);

                index = left(index);                            // Update the index to be the left child.
            }
            // If the right child is the smaller child.
            else {
                Type temp = heap.get(index);                    // Swap the parent and the right child.
                heap.set(index, heap.get(right(index)));
                heap.set(right(index), temp);

                index = right(index);                           // Update the index to be the right child.
            }

        }

    }

    // Parent
    // Returns the index of the parent node in the
    // heap of the index passed in.
    private int parent(int index) {

        if (index == 0) {               // If the node is the root node.
            return 0;
        } else if (index % 2 == 0) {    // If the index is even.
            return ((index / 2) - 1);
        } else {                        // The index is odd.
            return (index / 2);
        }

    }

    // Left
    // Returns the index of the left child node in
    // the heap of the index passed in.
    private int left(int index) {

        if (((index * 2) + 1) >= heap.size()) {
            return index;
        }
        return ((index * 2) + 1);

    }

    // Right
    // Returns the index of the right child node in
    // the heap of the index passed in.
    private int right(int index) {

        if (((index * 2) + 2) >= heap.size()) {
            return index;
        }
        return ((index * 2) + 2);

    }

}
