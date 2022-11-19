package com.company;

public class MyOrderedList <Type extends Comparable<Type>> {

    public static void main(String[] args) {

    }

    // Fields
    MyArrayList<Type> list = new MyArrayList<>();
    long comparisons;                        // Number of comparisons the list has made.

    // Constructor
    public MyOrderedList() {
        this.comparisons = 0;
    }

    // Add
    // Adds the item to the position in the list it belongs.
    // Takes in an item.
    public void add(Type item) {

        list.insert(item, list.size());      // Add the item to the end of the list.
        comparisons++;

        for (int i = list.size() - 1; i >= 1 ; i--) {

            comparisons++;

            if (list.get(i).compareTo(list.get(i - 1)) < 1) {
                list.swap(i, (i - 1));
            } else { break; }

        }
    }

    // Remove
    // Removes the item from the list if found.
    // Takes in an item.
    public void remove(Type item) {
        for (int i = 0; i < list.size() ; i++) {                // Traverse the list.

            if (list.get(i).compareTo(item) == 0) {             // Branch if the item is found.

                for (int j = i; j < list.size() - 1; j++) {     // Remove the item and shuffle the remaining elements.
                    list.set(j, list.get(j + 1));
                }

            }

        }

        MyArrayList<Type> temp = new MyArrayList<>();

        for (int i = 0; i < list.size() - 1; i++) {
            temp.insert(list.get(i), i);
        }

        list = temp;

    }

    // Binary Search
    // Uses a binary search algorithm to search through the array and find an item.
    // Returns the found item; returns false otherwise.
    public Type binarySearch(Type item){

        int left = 0;
        int right = list.size();

        if (left == right) {
            return null;
        }

        while (left + 1 < right) {                                  // Binary search loop.

            int middle = ((left + right) / 2);

            if(list.get(middle).compareTo(item) == 0) {
                comparisons++;
                return list.get(middle);
            } else if (list.get(middle).compareTo(item) < 0) {
                left = middle;
            } else {
                right = middle;
            }

            comparisons++;

        }

        if (list.get(left).compareTo(item) == 0) {                  // Edge case scenario where the item
            return list.get(0);                                     // is the first item in the list.
        }

        return null;

    }

    // Size
    // Returns the size of the list.
    public int size() {
        return list.size();
    }

    // isEmpty
    // Returns true if the size is 0; otherwise returns false.
    public boolean isEmpty() {

        if(list.size() == 0) {
            return true;
        }
        return false;

    }

    // Get
    // Returns the element stored at the given index.
    // Returns NULL if the index is out of bounds.
    public Type get(int index) {
        return list.get(index);
    }

    // toString
    public String toString() {
        return list.toString();
    }

}
