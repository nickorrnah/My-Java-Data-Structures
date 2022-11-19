package com.company;

public class MyBinarySearchTree<Type extends Comparable<Type>> {

    public static void main(String[] args) {
        MyBinarySearchTree<Integer> test = new MyBinarySearchTree(true);

        System.out.println(test);
        test.add(0);
        System.out.println(test);
        test.add(1);
        System.out.println(test);
        test.add(2);
        System.out.println(test);
        test.add(3);
        System.out.println(test);
        test.add(4);
        System.out.println(test);
        test.add(5);
        System.out.println(test);
        test.add(6);
        System.out.println(test);
        test.add(7);
        System.out.println(test);
        test.add(8);
        System.out.println(test);
        test.add(9);
        System.out.println(test);

//        test.add(5);
//        test.add(3);
//        test.add(7);
//        test.add(1);
//        test.add(4);
//        test.add(6);
//        test.add(8);
//        test.add(2);
//        test.add(0);
//        test.add(9);
//
//        test.remove(0);
//        test.remove(5);
//        test.remove(9);
//        test.remove(7);
//        test.remove(1);
//        test.remove(8);
//        test.remove(4);
//        test.remove(3);
//        test.remove(2);
//        System.out.println(test.size());
//        test.remove(6);
//
        System.out.println(test);
    }

    // Fields
    private Node root;          // The root node of the tree.
    private int size;           // The size of the tree.
    private boolean balancing = true;
    public int rotations;      // The number of rotations the tree made.
    public long comparisons;    // The number of comparisons the tree has made.


    // Constructors
    public MyBinarySearchTree(boolean balancing) {
        //this.balancing = true;
        this.root = null;
        this.size = 0;
        this.rotations = 0;
        this.comparisons = 0;
    }

    public MyBinarySearchTree() {
        this.balancing = false;
        this.root = null;
        this.size = 0;
        this.rotations = 0;
        this.comparisons = 0;
    }

    // Public Methods

    // Add
    // Adds the item to tree.
    public void add(Type item) {

            root = add(item, root);

    }

    // Remove
    // Removes the item from the tree.
    // Takes in an item.
    public void remove(Type item) {
        remove(item, root);
    }

    // Find
    // Takes in an item.
    // Returns the item if it is in the tree.
    // Returns null otherwise.
    public Type find(Type item) {
        return find(item, root);
    }

    // height
    // Returns the height of the root node.
    public int height() {
        if (root != null) {
            return root.height;
        }
        return -1;
    }

    // Size
    // Returns the size of the tree.
    public int size() {
        return size;
    }

    // isEmpty
    // Returns true if the tree is empty; false otherwise.
    public boolean isEmpty() {
        if (size == 0) {return true; }
        return false;
    }

    // toString
    // Returns a String of the contents in the tree in ascending order.
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        result = inOrder(result, root);
        if (result.length() >= 2) {
            result.delete(result.length() - 2, result.length());

        }
        return result + "]";
    }

    // Private Methods

    // add
    // Adds the item to the left or right subtree appropriately.
    // Takes in an item and a subtree to add the item to.
    // Returns the new root of the subtree after the node has been added.
    private Node add(Type item, Node subTree) {

        if (subTree == null) {                              // If the subTree is null.
            subTree = new Node();                           //      Create a new node.
            subTree.item = item;                            //      Add the item to it.
            subTree.height = 0;
            size++;
        } else if (item.compareTo(subTree.item) > 0) {      // If the item is bigger than the subTree's item.
            subTree.right = add(item, subTree.right);       //      Recurse through the right subTree.
            updateHeight(subTree);
        } else if (item.compareTo(subTree.item) < 0) {      // If the item is smaller than the subTree's item.
            subTree.left = add(item, subTree.left);         //      Recurse through the left subTree.
            updateHeight(subTree);
        }

        // Rebalance if necessary.
        if(balancing) {
            subTree = rebalance(subTree);
        }

        return subTree;

    }

    // Remove
    // Removes the item from the tree.
    // Takes in a node and an item.
    // Returns the new root of the sub tree after the removal.
    private Node remove(Type item, Node subTree) {

        if (size == 1) {
            root = null;
            size--;
            return null;
        } else if (subTree == null) {                               // If the subTree is null.
            return null;                                            //      The item does not exist, return null.
        } else if (item.compareTo(subTree.item) > 0) {              // If the item is bigger than the subTree's item.
            subTree.right = remove(item, subTree.right);            //      Recurse through the right subTree.
            updateHeight(subTree);

        } else if (item.compareTo(subTree.item) < 0) {              // If the item is smaller than the subTree's item.
            subTree.left = remove(item, subTree.left);              //      Recurse through the left subTree.
            updateHeight(subTree);

        } else if (item.compareTo(subTree.item) == 0) {             // If the item is found.

            if (subTree.left == null && subTree.right == null) {    // Case with no children.
                subTree = null;
                size--;

            } else if (subTree.left == null) {                      // Case where there is only a right child.

                Type temp = subTree.item;
                subTree.item = subTree.right.item;
                subTree.right.item = temp;
                subTree.right = remove(item, subTree.right);
                updateHeight(subTree);

            } else if (subTree.right == null) {                     // Case where there is only a left child.

                subTree = subTree.left;
                size--;

            } else {                                                // Case where the node has two children.

                Type temp = subTree.item;
                Node travel = subTree.left;

                while(travel.right != null) {
                    travel = travel.right;
                }

                subTree.item = travel.item;                  //      Swap the item with the right child's item.
                travel.item = temp;

                if (subTree.right.left != null && (subTree.right.left.item.compareTo(temp) < 0)) {
                                                                    // If the right child has a left child and the
                                                                    // child is bigger than its parent.

                    //System.out.println("Special");
                    temp = subTree.right.left.item;                 //      Swap the right child with the right child's
                    subTree.right.left.item = subTree.right.item;   //      left child.
                    subTree.right.item = temp;
                    subTree.right.left = remove(item, subTree.right.left);
                    updateHeight(subTree);
                    temp = subTree.item;                            //      Swap the right child with the root of the tree.
                    subTree.item = subTree.right.item;
                    subTree.right.item = temp;


                } else {
                    subTree.left = remove(item, subTree.left);
                    updateHeight(subTree);
                }

            }

        }

        // Rebalance if necessary.
        if(balancing && subTree != null) {
            subTree = rebalance(subTree);
        }

        return subTree;

    }

    // Rotate Right
    // Takes in a node and performs a right rotate on it.
    // Returns the node after the rotation.
    private Node rotateRight(Node node) {

        Node temp = node;
        node = node.left;

        if (node.right != null) {
            temp.left = node.right;
        } else {
            temp.left = null;
        }

        node.right = temp;
        rotations++;

        updateHeight(temp);
        updateHeight(node);

        return node;

    }

    // Rotate Left
    // Takes in a node and performs a left rotate on it.
    // Returns the node after the rotation.
    private Node rotateLeft(Node node) {

        Node temp = node;
        node = node.right;

        if (node.left != null) {
            temp.right = node.left;
        } else {
            temp.right = null;
        }

        node.left= temp;
        rotations++;


        updateHeight(temp);
        updateHeight(node);

        return node;

    }

    // Rebalance
    // Takes in a node and checks to see if it is balanced.
    // Balances the node if needed and returns the balanced node.
    private Node rebalance(Node node) {

        if (node.balanceFactor > 1) {               // If the node is left heavy:

            if (node.left.balanceFactor < 0) {      //      Left-right Case.

                node.left = rotateLeft(node.left);  //          Rotate it left to make it a left-left case.

            }

            node = rotateRight(node);               //      Rotate right

        } else if (node.balanceFactor < -1) {       // If the node is right heavy:

            if (node.right.balanceFactor > 0) {      //      Right-left Case.

                node.right = rotateRight(node.right);//          Rotate it right to make it a right-right case.

            }

            node = rotateLeft(node);               //      Rotate left

        }

        return node;

    }

    // Find
    // Takes in an item and a Node.
    // Searches for the item in said tree and returns the item if found.
    // Returns null otherwise.
    private Type find(Type item, Node subTree) {

        if (subTree == null) {
            comparisons++;
            return null;
        } else if (item.compareTo(subTree.item) > 0) {
            comparisons++;
            return find(item, subTree.right);
        } else if (item.compareTo(subTree.item) < 0) {
            comparisons++;
            return find(item, subTree.left);
        }
        comparisons++;
        return subTree.item;

    }

    private void updateHeight(Node node) {

        if (node.left != null && node.right != null) {

            if (node.left.height >= node.right.height) {
                node.height = node.left.height + 1;
            } else {
                node.height = node.right.height +1;
            }

            node.balanceFactor = node.left.height - node.right.height;

        } else if (node.left != null) {
            node.height = node.left.height + 1;
            node.balanceFactor = node.left.height + 1;
        } else if (node.right != null) {
            node.height = node.right.height + 1;
            node.balanceFactor = (-1) - node.right.height;
        } else {
            node.height = 0;
            node.balanceFactor = 0;
        }

    }

    // inOrder()
    // Returns a String containing the in order traversal of the tree.
    private StringBuilder inOrder(StringBuilder result, Node node) {

        if (node != null) {
            result = inOrder(result, node.left);
            result.append(node + ", ");
            result = inOrder(result , node.right);
        }

        return  result;

    }

    protected class Node {

        // Fields
        public Type item;       // The item the node contains.
        public Node left;       // The left child of the node.
        public Node right;      // The right child of the node.

        public int height;      // The height of the node.
        public int balanceFactor;

        // Constructor
        public Node() {
            this.item = null;
            this.left = null;
            this.right = null;
            this.height = 0;
            this.balanceFactor = 0;
        }

        // Methods

        // toString
        public String toString() {

            if(!balancing) {
                return (item + ":H" + height);
            } else {
                return (item + ":H" + height + ":B" + balanceFactor);
            }

        }

    }

}
