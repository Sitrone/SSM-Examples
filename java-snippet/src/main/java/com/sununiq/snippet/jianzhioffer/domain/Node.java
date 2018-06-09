package com.sununiq.snippet.jianzhioffer.domain;

public class Node {
    public int value;
    public Node next;

    public Node(int value) {
        this.value = value;
    }

    public Node next(int value) {
        Node node = new Node(value);
        this.next = node;
        return node;
    }
}
