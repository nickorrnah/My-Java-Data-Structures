package com.company;

import java.io.*;

public class BookReader {


    public static void main(String[] args) throws IOException {

    }

    // Fields
    String book;                                                        // The book as a String.
    public MyLinkedList<String> words = new MyLinkedList<>();                  // A list of all the words in the book.
    public MyLinkedList<String> wordsAndSeparators = new MyLinkedList<>();     // A list of all the words and separators in the book.

    // Constructor
    // Calls readBook on the given filename, then calls parseWords.
    // Takes in a String.
    public BookReader(String filename) throws IOException {

        readBook(filename);
        parseWords();

    }

    // Read Book
    // Reads the contents of the file into the book.
    // Takes in a file.
    public void readBook(String filename) throws IOException {

        long startTime = System.nanoTime();

        FileReader fr = new FileReader(filename);
        BufferedReader reader = new BufferedReader(fr);

        StringBuffer bookString = new StringBuffer();

        long characterCount = 0;
        int c = 0;

        while((c = reader.read()) != -1) {      // Read the file char by char.

            char character = (char) c;

            bookString.append(character);       // Add the char to the bookString.
            characterCount++;

        }

        book = bookString.toString();           // Update book to be bookString.

        long endTime = System.nanoTime();
        long duration = ((endTime - startTime) / 1000000);

        System.out.println ("Reading input file \"" + filename + "\"... " + characterCount +
                            " characters read in " + duration + " milliseconds.");
        System.out.println();

    }

    // Parse Words
    // Scans book for words.
    public void parseWords() throws IOException {

        long startTime = System.nanoTime();
        long wordCount = 0;
        StringBuffer currentWord = new StringBuffer();
        StringBuffer currentSep = new StringBuffer();
        StringBuffer bookBuffer = new StringBuffer(book);

        for (int i = 0; i < bookBuffer.length(); i++) {

            Character ch = bookBuffer.charAt(i);

            if ((ch.compareTo('A') >= 0 && ch.compareTo('Z') <= 0) || (ch.compareTo('a') >= 0 && ch.compareTo('z') <= 0)
                    || (ch.compareTo('0') >= 0 && ch.compareTo('9') <= 0) || ch.equals('\'')) {

                currentWord.append(ch);                                 // If currentChar is part of a word, add it to the current word.

                if (!currentSep.toString().equals("")) {            // Make sure the currentSep is not an empty String.

                    wordsAndSeparators.addBefore(currentSep.toString());    // If currentChar is not part of a word, add the word to words and separators.
                    currentSep.delete(0, currentSep.length());              // Reset currentSep.

                }

            } else if (!((ch.compareTo('A') >= 0 && ch.compareTo('Z') <= 0) || (ch.compareTo('a') >= 0 && ch.compareTo('z') <= 0)
                    || (ch.compareTo('0') >= 0 && ch.compareTo('9') <= 0) || ch.equals('\''))) {

                currentSep.append(ch);

                wordsAndSeparators.addBefore(currentSep.toString());
                currentSep.delete(0, currentSep.length());

                if (!currentWord.toString().equals("")) {            // Make sure the currentWord is not an empty String.

                    words.addBefore(currentWord.toString());                // If currentChar is not part of a word, add the word to words.
                    wordsAndSeparators.addBefore(currentWord.toString());   // If currentChar is not part of a word, add the word to words and separators.
                    currentWord.delete(0, currentWord.length());            // Reset currentWord.

                    wordCount++;                                            // Increment word count.
                }

            }

        }

        if (!(currentWord.toString().equals(""))) {                   // Edge case scenario where the last character is a word letter.

            currentWord.append(bookBuffer.charAt(bookBuffer.length() - 1));
            words.addBefore(currentWord.toString());
            wordsAndSeparators.addBefore(currentWord.toString());
            wordCount++;

        } else {              // Edge case scenario where the last character is a separator.

            currentSep.append(bookBuffer.charAt(bookBuffer.length() - 1));
            wordsAndSeparators.addBefore(currentSep.toString());

        }

        long endTime = System.nanoTime();
        long duration = ((endTime - startTime) / 1000000);

        System.out.println("Finding words and adding them to a linked list... in " + duration + " milliseconds.");
        System.out.println("The linked list has a length of " + wordCount + ".");
        System.out.println();

    }

}


