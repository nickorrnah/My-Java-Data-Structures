package com.company;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class HuffmanEncoder {

    public static void main(String[] args) throws IOException {
        // write your code here
        HuffmanEncoder test = new HuffmanEncoder(true);
    }

    // Fields
    private String inputFileName;                           // Input file name.
    private String outputFileName;                          // Output file name.
    private String codesFileName;                           // Codes file name.

    private boolean wordCodes;                              // Determines if the encoder is using character codes or word/separator codes.

    private BookReader book;                                // A book reader that is initialized with inputFileName.

    //private MyOrderedList<FrequencyNode> frequencies;     // Ordered list that stores the frequencies of each char in the input file.
    //private MyOrderedList<CodeNode> codes;                // Ordered list that stores the codes assigned to each character by the Huffman Algorithm.

    protected MyHashTable<String, Integer> frequenciesHash;   // A hash table that stores the frequency of each word/separator in the file.
    protected MyHashTable<String, String> codesHash;          // A hash table that stores the codes assigned to each word/separator by the Huffman algorithm.

    private HuffmanNode huffmanTree;                        // The root HuffmanNode of the Huffman tree.

    private byte[] encodedText;                             // The encoded binary string stored as an array of bytes.

    // Constructor
    public HuffmanEncoder(boolean wordCodes) throws IOException {

        this.inputFileName = "./WarAndPeace.txt";
        //this.inputFileName = "./test.txt";
        this.outputFileName = "WarAndPeace-compressed.bin";
        this.codesFileName = "WarAndPeace-codes.txt";

        this.book = new BookReader(inputFileName);

        //this.frequencies = new MyOrderedList<FrequencyNode>();
        //this.codes = new MyOrderedList<CodeNode>();

        this.frequenciesHash = new MyHashTable<String, Integer>(32768);
        this.codesHash = new MyHashTable<String, String>(32768);

        this.wordCodes = true;

        countFrequency();
        buildTree();
        encode();
        writeFiles();

    }

    public HuffmanEncoder() throws IOException {

        this.inputFileName = "./WarAndPeace.txt";
        //this.inputFileName = "./test.txt";
        this.outputFileName = "WarAndPeace-compressed.bin";
        this.codesFileName = "WarAndPeace-codes.txt";

        this.book = new BookReader(inputFileName);

        //this.frequencies = new MyOrderedList<FrequencyNode>();
        //this.codes = new MyOrderedList<CodeNode>();

        this.frequenciesHash = new MyHashTable<String, Integer>(32768);
        this.codesHash = new MyHashTable<String, String>(32768);

        this.wordCodes = true;

        countFrequency();
        buildTree();
        encode();
        writeFiles();

    }

    // Count Frequency
    // Counts the frequency of each char in the book and
    // stores it in frequencies.
    private void countFrequency() {

        long start = System.nanoTime();

        book.wordsAndSeparators.first();

        while (book.wordsAndSeparators.current() != null) {

            String nodeWord = book.wordsAndSeparators.current();

            if (frequenciesHash.get(nodeWord) != null) {       // If the char is already in frequenciesHash, increase the count.

                frequenciesHash.put(nodeWord, (frequenciesHash.get(nodeWord) + 1));
                //System.out.println(nodeWord + " " + frequenciesHash.get(nodeWord));

            } else {                                            // Word is not in list, make a new node.

                frequenciesHash.put(nodeWord, 1);

            }

            book.wordsAndSeparators.next();

        }

        long finish = System.nanoTime();

        System.out.println("Counting frequencies of words and separators... " + frequenciesHash.size() + " unique words and separators found in "
                            + ((finish - start) / 1000000) + " milliseconds.");
        System.out.println();

    }

    // Build Tree
    // Builds the Huffman tree and extracts the codes from it.
    // Stores the codes in codes.
    public void buildTree() {

        MyPriorityQueue<HuffmanNode> queue = new MyPriorityQueue();
        MyLinkedList<String> addedWords = new MyLinkedList<>();

        book.wordsAndSeparators.first();

        while (book.wordsAndSeparators.current() != null) {     // Create a HuffmanNode for each word and add them to a priority queue.

            String word = book.wordsAndSeparators.current();

            if (!(addedWords.contains(word))) {
                queue.insert(new HuffmanNode(word, frequenciesHash.get(word)));
                addedWords.addBefore(word);
            }

            book.wordsAndSeparators.next();

        }

        long start = System.nanoTime();

        // Merge HuffMan Nodes until only a single tree remains.
        while (queue.size() > 1) {      // Loop while the queue is not empty.

            HuffmanNode node = new HuffmanNode(queue.removeMin(), queue.removeMin());
            queue.insert(node);
            // Creates a new HuffmanNode that points to the least weighted HuffmanNodes.
            // Removes the two least-weighted Huffman nodes from the queue.
            // Adds the new Huffman Node to the queue.

            huffmanTree = node; // Update huffmanTree to hold the root of the huffmanTree.

        }

        extractCodes(huffmanTree, "");

        long end = System.nanoTime();

        System.out.println("Building a Huffman tree and reading codes... in " + ((end - start) / 1000000) + " milliseconds.");
        System.out.println();

    }

    // Extract Codes
    // Recursive method that traverses the Huffman tree to extract the codes stored in it.
    // Takes in a HuffmanNode and a String.
    private void extractCodes(HuffmanNode root, String code) {

        if (root.left != null){
            extractCodes(root.left, code + "0");
        }

        if (root.word != null) {
            codesHash.put(root.word, code);
        }

        if (root.right != null){
            extractCodes(root.right, code + "1");
        }

    }

    // Encode
    // Uses the book and codes to create encodedText.
    public void encode() {

        StringBuilder encodedBook = new StringBuilder();

        long start = System.nanoTime();

        book.wordsAndSeparators.first();

        while (book.wordsAndSeparators.current() != null) {

            String word = book.wordsAndSeparators.current();
            encodedBook.append(codesHash.get(word));
            book.wordsAndSeparators.next();

        }

        MyArrayList<Byte> byteList = new MyArrayList<>();
        int index = 0;

        for (int i = 0; i < encodedBook.length(); i += 8) {

            byte b;

            if ((encodedBook.length() - i) >= 8) {
                b = (byte) Integer.parseInt(encodedBook.substring(i, i + 8), 2);
            } else {
                b = (byte) Integer.parseInt(encodedBook.substring(i, encodedBook.length()), 2);
            }

            byteList.insert(b, index);
            index++;

        }

        encodedText = new byte[byteList.size()];

        for (int i = 0; i < byteList.size(); i++) {
            encodedText[i] = byteList.get(i);
        }

        long end = System.nanoTime();

        System.out.println("Encoding message... in " + ((end - start) / 1000000) + " milliseconds.");
        System.out.println();

    }

    // Write Files
    // Writes the contents of encodedText to the outputFileName and
    // the contents of codes to codesFileName.
    public void writeFiles() throws IOException {

        long start = System.nanoTime();

        File outputFile = new File("./WarAndPeace-compressed.bin");
        FileOutputStream outputStream = new FileOutputStream(outputFile);
        outputStream.write(encodedText);

        FileWriter writer = new FileWriter(codesFileName);
        writer.write(codesHash.toString());

        writer.flush();
        writer.close();

        long end = System.nanoTime();

        System.out.println("Writing compressed file... " + encodedText.length + " bytes written in " + ((end - start) / 1000000) + " milliseconds.");

    }

    // PROTECTED CLASSES:

//    // Protected FrequencyNode class.
//    protected class FrequencyNode implements Comparable<FrequencyNode> {
//
//        // Fields
//        public Character character;     // Character that this count is for.
//        public Integer count;           // The count for this character.
//
//        // Constructor
//        public FrequencyNode(char ch) {
//            this.character = ch;
//            this.count = 1;
//        }
//
//        // Methods
//        @Override
//        public int compareTo(FrequencyNode other) {
//            return character - other.character;
//        }
//
//        public String toString() {
//            return ("" + character + ":" + count);
//        }
//
//    }

    // Protected HuffmanNode Class.
    protected class HuffmanNode implements Comparable<HuffmanNode> {

        // Fields
        public Character character;     // The character that this node stores.
                                            // Only leaves store characters.
                                            // Internal nodes have null characters.

        public Integer weight;          // The weight of the Huffman tree rooted at this node.
        public HuffmanNode left;        // The root of the left subtree.
        public HuffmanNode right;       // The root of the right subtree.

        public String word;             // The word that this node stores.

        // Constructors.

        // Creates the initial leaf node for characters.
        public HuffmanNode(char character, Integer weight) {
            this.character = character;
            this.weight = weight;
        }

        // Creates the initial leaf node for words.
        public HuffmanNode(String word, Integer weight) {
            this.word = word;
            this.weight = weight;
        }

        // Used when merging two nodes.
        public HuffmanNode(HuffmanNode left, HuffmanNode right) {

                this.weight = left.weight + right.weight;
                this.left = left;
                this.right = right;

            }

        // Methods
        public int compareTo(HuffmanNode other) {
            return (weight - other.weight);
        }

        public String toString() {

            if (!wordCodes) {
                return ("" + character + ":" + weight);
            } else {
                return ("" + word + ":" + weight);
            }

        }

    }

//    // Protected CodeNode class.
//    protected class CodeNode implements Comparable<CodeNode> {
//
//        // Fields
//        public Character character;     // The character that this node stores the code for.
//        public String code;             // The code assigned to this character.
//
//        // Constructor
//        public CodeNode(char character, String code) {
//            this.character = character;
//            this.code = code;
//        }
//
//        // Methods
//        @Override
//        public int compareTo(CodeNode other) {
//            return (character - other.character);
//        }
//
//        public String toString() {
//            return ("" + character + ":" + code);
//        }
//
//    }

}
