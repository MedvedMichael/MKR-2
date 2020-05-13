package com.company;

public class Node {
    String value = "";
    Node left, right, prev;

    Node(String value) {
        this.value = value;
    }



    Node(String value, Node prev) {
        this.prev = prev;
        this.value = value;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }
}
