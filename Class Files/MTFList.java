package com.company;

public class MTFList<Type extends Comparable<Type>> {

    // Fields
    protected MyLinkedList<Type> list;

    // Constructor
    public MTFList() {
        this.list = new MyLinkedList<>();
    }

    // Methods

    public void add(Type item) {

        list.first();
        list.addBefore(item);

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

                Type result = list.remove();
                list.first();
                add(result);
                return result;

            } else if (list.current().compareTo(item) == 0) {
                return list.first();
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
