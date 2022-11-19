package com.company;

public class TransposeList <Type extends Comparable<Type>>{

    // Fields
    protected MyLinkedList<Type> list;

    // Constructor
    public TransposeList() {
        this.list = new MyLinkedList<>();
    }

    // Methods

    public void add(Type item) {

        list.first();

        if (list.size() == 0) {
            list.addBefore(item);
        } else if (list.size == 1) {
            list.addAfter(item);
        } else {
            list.current = list.last;
            list.addAfter(item);
        }

    }

    public Type remove(Type item) {
        list.first();

        while (list.current() != null)  {

            if (list.current().compareTo(item) == 0) {
                return (list.remove());
            }

            list.next();

        }

        return null;
    }

    public Type find(Type item) {

        list.first();
        boolean isFirst = true;

        while(list.current() != null) {

            if (list.current().compareTo(item) == 0 && !isFirst) {

                Type result = list.current();
                list.swap(list.previous, list.current);
                return result;

            } else if (list.current().compareTo(item) == 0) {
                return list.current();
            }

            isFirst = false;
            list.next();

        }

        return null;
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public String toString() {
        return list.toString();
    }
}
