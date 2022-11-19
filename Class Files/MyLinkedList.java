package com.company;

public class MyLinkedList<Type extends Comparable<Type>> {

    public static void main(String[] args) {

    }

    // Fields
    Node first;         // The first node in the linkedList.
    Node current;       // The current node in the linkedList.
    Node previous;      // The node immediately previous to the current node.
    Node last;

    long comparisons;   // The number of comparisons the list has done.
    int size;           // The size of the list.

    // Constructor
    public MyLinkedList() {

        this.first = null;
        this.current = null;
        this.last = null;
        this.size = 0;
        this.comparisons = 0;

    }

    // addBefore
    // Adds the specified item before the previous node.
    // Places the item between the current and previous node.
    // If the current node is null, the new element is added in the last node.
    // If the current node is the first node then the new element becomes the
    // new first node.
    public void addBefore(Type item) {

        if (first == null && size == 0) {

            first = new Node(item, current);
            previous = first;
            last = first;
            size++;

        } else if (current == first) {

            addAfter(item);
            current = current.next;
            swap(first, current);

        } else {

            Node newNode = new Node(item, current);
            previous.next = newNode;
            previous = previous.next;
            size++;

        }

    }

    // addAfter
    // Adds the item after the current node.
    // Adds the item between the current node and its next node.
    // If the current node is null; does nothing.
    public void addAfter(Type item) {

        if (current == null) {
            // Do nothing.
        } else if (current.next == null) {

            current.next = new Node(item, null);
            last = current.next;
            size++;

        } else {

            current.next = new Node(item,current.next);
            size++;

        }

    }

    // Remove
    // Removes the current node  and returns the element in
    // the removed node.
    // If the current node is null; does nothing and returns null.
    // The current node will be updated to the node after
    // the removed node.
    public Type remove() {

        if (current == null) {
            return null;
        } else {

            Type result = current.item;

            if (current == first) {

                current = first.next;
                first = current;
                previous = null;
                size--;

                if (size == 1) {
                    last = first;
                } else if (size == 0) {
                    last = null;
                }

            } else {

                previous.next = current.next;
                current = current.next;
                size--;

            }

            return result;

        }

    }

    // Current
    // Returns the item stored in the current node.
    // Returns null if current is null.
    public Type current() {

        if (current == null) {
            return null;
        } else {
            return current.item;
        }

    }

    // First
    // Sets the current node to be the first node.
    // Returns the item stored in the current node after the update.
    // Returns null if first is null.
    public Type first() {

        if (first == null) {
            return null;
        } else {
            this.current = this.first;
            return current.item;
        }

    }

    // Next
    // Sets the current node to be the next node in the list.
    // Returns the item in current after the update.
    // Returns null if the current is null.
    public Type next() {

        if (this.current == null) {
            return null;
        } else if (current.next != null) {
            this.previous = this.current;       // Updating the previous node.
            this.current = this.current.next;   // Updating the current node.
            return this.current.item;
        } else {
            previous = current;
            current = current.next;
            return null;
        }

    }

    // Contains
    // Searches the nodes for the item and returns true if found.
    // Returns false if item is not found.
    public boolean contains(Type item) {

        Node scanner = this.first;

        while (scanner != null) {

            comparisons++;

            if (scanner.item.compareTo(item) == 0) {
                return true;
            } else {
                scanner = scanner.next;
            }

        }

        return false;

    }

    // Size
    // Returns the number of elements in the list
    public int size() {
        return this.size;
    }

    // isEmpty
    // Returns true if the list is empty.
    // Returns false if the list is not empty.
    public boolean isEmpty() {

        if(size == 0) {
            return true;
        } else {
            return false;
        }

    }

    // Sort
    // Sorts the list in ascending order.
    // Uses the insertion sort algorithm.
    public void sort()  {

        Node scanner = first;

        for (int i = 1; i < size; i++) {

            Type previousItem = scanner.item;
            Node previousNode = scanner;

            scanner = scanner.next;

            for (int j = i; j > 0 && scanner.item.compareTo(previousItem) <= -1; j--) {
                swap(previousNode, scanner);
            }

        }

    }

    // Swap
    // Swaps the items contained in the nodes.
    // Takes in two nodes.
    public void swap(Node nodeOne, Node nodeTwo) {

        Type tempOne = nodeOne.item;
        Type tempTwo = nodeTwo.item;

        nodeOne.item = tempTwo;
        nodeTwo.item = tempOne;

    }

    // toString
    public String toString() {
        String result = "[";
        Node scanner = first;

        while (scanner != null) {

            result += scanner.toString();

            if (scanner.next != null) {
                result += ", ";
            }

            scanner = scanner.next;

        }
        result += "]";
        return result;
    }

    // Protected Node Class
    protected class Node {

        // Fields
        private Type item;
        private Node next;

        // Constructor
        public Node(Type item, Node next) {
            this.item = item;
            this.next = next;
        }

        // toString
        public String toString(){
            return this.item.toString();
        }

    }

}
