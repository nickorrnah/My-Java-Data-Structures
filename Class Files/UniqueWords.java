package com.company;

import java.io.IOException;

public class  UniqueWords {

    public UniqueWords() throws IOException {

        this.book = new BookReader("./WarAndPeace.txt");
        //this.book = new BookReader("D:/icnic/Projects/School Projects/TCSS 342/Unique Words Assignment/src/com/company/test.txt");
        //this.book = new BookReader("D:/icnic/Projects/School Projects/TCSS 342/Unique Words Assignment/src/com/company/WarAndPeace.txt");
        addUniqueWordsToHashTable();

    }

    public static void main(String[] args) throws IOException {
        new UniqueWords();
    }

    // Fields
    BookReader book;

    // Add Unique Words to Linked List
    // Adds all the unique words stored in book to a new Linked List
    public void addUniqueWordsToLinkedList() {

        MyLinkedList<String> uniqueList = new MyLinkedList<>();
        long startTime = System.nanoTime();

        book.words.current = book.words.first;

        while(book.words.current != null) {                     // Loop until we are at the end of the list.

            if (!uniqueList.contains(book.words.current())) {   // If the word is not in the uniqueList:
                uniqueList.addBefore(book.words.current());     // add the word to the uniqueList.
            }

            book.words.next();

        }

        long endTime = System.nanoTime();
        long durationAdd = ((endTime - startTime) / 1000000000);

        startTime = System.nanoTime();
        uniqueList.sort();
        endTime = System.nanoTime();
        long durationSort = ((endTime - startTime) / 1000000);

        System.out.println("Adding unique words to a linked list... in " + durationAdd + " seconds.");
        System.out.println(uniqueList.size() + " unique words were found.");
        System.out.println(uniqueList.comparisons + " comparisons were made.");
        System.out.println("Insertion sorting linked list... in " + durationSort + " milliseconds.");
        System.out.println();

    }

    // Add Unique Words to Hash Table.
    // Adds all the unique words stored in book to a new Hash Table.
    public void addUniqueWordsToHashTable() {

        MyHashTable<String, String> uniqueHash = new MyHashTable<>(32768);

        long startTime = System.nanoTime();

        book.words.current = book.words.first;

        while(book.words.current != null) {                         // Loop until we are at the end of the list.

            if (uniqueHash.get(book.words.current()) == null) {     // If the word is not in the uniqueHash:
                uniqueHash.put(book.words.current(), "");                //       add the word to the uniqueHash.
            }

            book.words.next();

        }

        long endTime = System.nanoTime();
        long duration = ((endTime - startTime) / 1000000);

        System.out.println("Adding unique words to a hash table... in " + duration + " milliseconds.");
        System.out.println(uniqueHash.size() + " unique words.");
        System.out.println("The hash table made " + uniqueHash.comparisons + " comparisons.");
        System.out.println(uniqueHash.maxProbe + " max probe.");

        startTime = System.nanoTime();
        uniqueHash.toString();
        endTime = System.nanoTime();
        duration = ((endTime - startTime) / 1000000);

        System.out.println("Extracting the key-value pairs... in " + duration + " milliseconds.");

    }

//    // Add Unique Words to Binary Search Tree
//    // Adds all the unique words stored in book to a new Binary Search Tree.
//    public void addUniqueWordsToBST() {
//
//        MyBinarySearchTree<String> uniqueBST = new MyBinarySearchTree<>();
//
//        long startTime = System.nanoTime();
//
//        book.words.current = book.words.first;
//
//        while(book.words.current != null) {                         // Loop until we are at the end of the list.
//
//            if (uniqueBST.find(book.words.current()) == null) {     // If the word is not in the uniqueBST:
//                uniqueBST.add(book.words.current());                //       add the word to the uniqueBST.
//            }
//
//            book.words.next();
//
//        }
//
//        long endTime = System.nanoTime();
//        long duration = ((endTime - startTime) / 1000000);
//
//        System.out.println("Adding unique words to a binary search tree... in " + duration + " milliseconds.");
//        System.out.println(uniqueBST.size() + " unique words.");
//        System.out.println("The binary search tree had a height of " + uniqueBST.height() + " and made "
//                            + uniqueBST.comparisons + " comparisons.");
//
//        startTime = System.nanoTime();
//        uniqueBST.toString();
//        endTime = System.nanoTime();
//        duration = ((endTime - startTime) / 1000000);
//
//        System.out.println("Traversing the binary search tree... in " + duration + " milliseconds.");
//
//    }

    // Add Unique Words to Binary Search Tree
    // Adds all the unique words stored in book to a new Binary Search Tree.
//    public void addUniqueWordsToAVL() {
//
//        MyBinarySearchTree<String> uniqueBST = new MyBinarySearchTree<>(true);
//
//        long startTime = System.nanoTime();
//
//        book.words.current = book.words.first;
//
//        while(book.words.current != null) {                         // Loop until we are at the end of the list.
//
//            if (uniqueBST.find(book.words.current()) == null) {     // If the word is not in the uniqueBST:
//                uniqueBST.add(book.words.current());                //       add the word to the uniqueBST.
//            }
//
//            book.words.next();
//
//        }
//
//        long endTime = System.nanoTime();
//        long duration = ((endTime - startTime) / 1000000);
//
//        System.out.println("Adding unique words to an AVL binary search tree... in " + duration + " milliseconds.");
//        System.out.println(uniqueBST.size() + " unique words.");
//        System.out.println("The AVL binary search tree had a height of " + uniqueBST.height() + ", made "
//                + uniqueBST.comparisons + " comparisons, and made " + uniqueBST.rotations + " rotations.");
//
//        startTime = System.nanoTime();
//        uniqueBST.toString();
//        endTime = System.nanoTime();
//        duration = ((endTime - startTime) / 1000000);
//
//        System.out.println("Traversing the AVL tree... in " + duration + " milliseconds.");
//
//    }

    // Add Unique Words to Array List
    // Adds all the unique words stored in book to a new Array List
//    public void addUniqueWordsToArrayList() {
//        MyArrayList<String> uniqueList = new MyArrayList<>();
//        long startTime = System.nanoTime();
//        int uniqueWords = 0;
//
//        book.words.current = book.words.first;
//
//        while(book.words.current != null) {                             // Loop until we are at the end of the list.
//
//            if (!uniqueList.contains(book.words.current())) {           // If the word is not in the uniqueList:
//                uniqueList.insert(book.words.current(), uniqueWords);   // add the word to the uniqueList.
//                uniqueWords++;
//            }
//
//            book.words.next();
//
//        }
//
//        long endTime = System.nanoTime();
//        long durationAdd = ((endTime - startTime) / 1000000000);
//
//        startTime = System.nanoTime();
//        uniqueList.sort();
//        endTime = System.nanoTime();
//        long durationSort = ((endTime - startTime) / 1000000000);
//
//        System.out.println("Adding unique words to an array list... in " + durationAdd + " seconds.");
//        System.out.println(uniqueList.size() + " unique words were found.");
//        System.out.println(uniqueList.comparisons + " comparisons were made.");
//        System.out.println("Insertion sorting array list... in " + durationSort + " seconds.");
//        System.out.println();
//
//    }

    // Add Unique Words to Ordered List
    // Adds all the unique words stored in book to a new Ordered List
//    public void addUniqueWordsToOrderedList() {
//
//        MyOrderedList<String> uniqueList = new MyOrderedList<>();
//        long startTime = System.nanoTime();
//
//        book.words.first();
//
//        while(book.words.current() != null) {                       // Loop until we are at the end of the list.
//
//            if (!uniqueList.binarySearch(book.words.current())) {   // If the word is not in the uniqueList:
//                uniqueList.add(book.words.current());               // add the word to the uniqueList.
//            }
//
//            book.words.next();
//
//        }
//
//        long endTime = System.nanoTime();
//        long durationAdd = ((endTime - startTime) / 1000000000);
//
//        System.out.println("Adding unique words to an array list... in " + durationAdd + " seconds.");
//        System.out.println(uniqueList.size() + " unique words were found.");
//        System.out.println(uniqueList.comparisons + " comparisons were made.");
//        System.out.println();
//
//    }
//
}
