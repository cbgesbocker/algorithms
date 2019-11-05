package LinkedLists;

import java.util.HashSet;

public class LinkedListQuiz {
    public static void deleteDuplicated(Node n) {
        HashSet<Integer> set = new HashSet<Integer>();
        Node previous = null;
        while (n != null) {
            if (set.contains(n.data)) {
                previous.next = n.next;
            } else {
                set.add(n.data);
                previous = n;
            }
            n = n.next;
        }
    }



    public static void main(String args[]) {
        Node node1 = new Node(1, null);
        Node node2 = new Node(2, node1);
        Node node3 = new Node(3, node2);
        Node node4 = new Node(3, node3);

        LinkedListQuiz.deleteDuplicated(node4);
        System.out.println(node4);
    }
}

class Node {
    Node next = null;
    int data;

    public Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}
