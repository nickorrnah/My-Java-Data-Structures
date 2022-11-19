package com.company;

public class MyHashTable<Key extends Comparable<Key>,Value> {

    public static void main(String[] args) {

    }

    // Fields
    public MyArrayList<Key> keys;   // Stores the unique keys stored in the hash table.
    public int comparisons;         // The total number of probes made when putting in a new key-value pair.
    public int maxProbe;            // Max number of probes made to put any key-value pair in.

    private int capacity;           // Capacity of the hash table.
    private int size;               // The number of key-value pairs stored in the table.
    private Key[] keyBuckets;       // Stores the keys according to their hash value.
    private Value[] valueBuckets;   // Stores the values according to the hash value of the associated key.

    // Constructor
    public MyHashTable(int capacity) {

        this.capacity = capacity;
        this.size = 0;
        this.comparisons = 0;
        this.maxProbe = 0;

        this.keys = new MyArrayList<Key>();

        this.keyBuckets = (Key[]) new Comparable[capacity];
        this.valueBuckets = (Value[]) new Object[capacity];

    }

    // Hashes an index from the key.
    private int hash(Key key) {
        return(Math.abs(key.hashCode() % capacity));
    }

    // Returns the value associated with the key.
    // Returns null if there is no value associated with the key.
    public Value get(Key key) {

        int currentRecord = 1;

        int index = hash(key);                              // Get index from hashing the key.
        Key entry = keyBuckets[index];                      // Create a new key from that index.

        if (entry == null) {                                // If the spot is empty:

            return null;                                    //      No key is found.

        } else if (entry.compareTo(key) == 0) {             // Else if the key maps to a value:

            return valueBuckets[index];                     //      Return the value.

        } else {                                            // Else, there is a collision:

            boolean looped = false;

            for (int i = index + 1; i < capacity; i++) {    //      Loop through the table.

                entry = keyBuckets[i];

                if (entry == null) {                        //      If the new spot is empty:

                    return null;                            //              There is no value.

                } else if (entry.compareTo(key) == 0) {     //      Else if the new spot has the same key:

                    return valueBuckets[i];                 //               Return the value.

                }

                if (i == capacity - 1 && !looped) {
                    i = -1;
                    looped = true;
                }

            }

        }

        if (currentRecord > maxProbe) {             //      If the current record is greater than the max prob:
            maxProbe = currentRecord;               //              Update maxProbe to the current record.
        }

        return null;

    }

    // Associates the value to key.
    public void put(Key key, Value value) {

        int currentRecord = 1;

        int index = hash(key);                              // Get index from hashing the key.
        Key entry = keyBuckets[index];                      // Create a new key from that index.

        if (entry == null) {                                // If the spot is empty:

            keyBuckets[index] = key;                        //      Fill the spot with the key.
            valueBuckets[index] = value;                    //      Fill the spot with the value.
            keys.insert(key, keys.size());                  //      Add the key to the list.

            size++;
            comparisons++;

        } else if (entry.compareTo(key) == 0) {             // Else if the key already maps to a value:

            valueBuckets[index] = value;                    //      Update it to the new value.

        } else {                                            // Else, there is a collision:

            boolean looped = false;

            for (int i = index + 1; i < capacity; i++) {    //      Loop through the table.

                currentRecord++;
                comparisons++;

                entry = keyBuckets[i];

                if (entry == null) {                        //      If the new spot is empty:

                    keyBuckets[i] = key;                    //              Fill the spot with the key.
                    valueBuckets[i] = value;                //              Fill the spot with the value.
                    keys.insert(key, keys.size());          //              Add the key to the list.

                    size++;

                    break;                                  //              Break out of the loop;

                } else if (entry.compareTo(key) == 0) {     //      Else if the new spot has the same key:

                    valueBuckets[i] = value;                //              Update it to the new value.

                    break;                                  //              Break out of the loop;

                }

                if (i == capacity - 1&& !looped) {
                    i = -1;
                    looped = true;
                }

            }

            comparisons++;

        }

        if (currentRecord > maxProbe) {             //      If the current record is greater than the max prob:
            maxProbe = currentRecord;               //              Update maxProbe to the current record.
        }

    }

    // Returns the number of key-value pairs.
    public int size() {
        return this.size;
    }

    // toString
    public String toString() {

        StringBuilder result = new StringBuilder("[");

        int found = 0;

        for (int i = 0; i < capacity; i++) {

            if (keyBuckets[i] != null) {
                result.append(keyBuckets[i]);
                result.append(":");
                result.append(valueBuckets[i]);

                found++;

                if (found < size) {
                    result.append(", ");
                }
            }

        }

        result.append("]");
        return result.toString();

    }

}
