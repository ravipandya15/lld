package com.example.lld.practise.Coding_Questions.IE.Arrise_Solution_Pragmatic_Play;

class Node {

    int value;
    Node next;

    public Node(int value) {
        this.value = value;
    }

    public Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }
}

public class LinkedList {

    public static void main(String[] args) {
        Node node4 = new Node(1);
        Node node3 = new Node(1, node4);
        Node node2 = new Node(1, node3);
        Node head = new Node(1, node2);

        Node newNode = reverseLinkedlist(head);
        Node updatedNode = addOne(newNode);
        Node addOne = reverseLinkedlist(updatedNode);

        while (addOne != null) {
            System.out.print(addOne.value + " -> ");
            addOne = addOne.next;
        }
    }

    private static Node addOne(Node head) {
        Node cur = head;
        Node prev = null;
        int sum = 0;
        int carry = 1;

        while (head != null && carry > 0) {
            sum = head.value + carry;
            carry = sum / 10;
            sum = sum % 10;

            head.value = sum;
            prev = head;
            head = head.next;
        }
        if (carry > 0) {
            prev.next = new Node(1);
        }
        return cur;
    }

    private static Node reverseLinkedlist(Node head) {
        Node next = null;
        Node prev = null;
        Node cur = head;

        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur =  next;
        }
        return prev;
    }
}
