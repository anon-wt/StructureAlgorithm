package com.study.structure.stack;

import java.util.Scanner;

public class LinkedStackDemo {
    public static void main(String[] args) {
        LinkedStack linkedStack = new LinkedStack();
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("o(pop): 弹出栈");
            System.out.println("u(push): 压入栈");
            System.out.println("s(show): 显示栈");
            System.out.println("e(exit): 退出");
            key = scanner.next().charAt(0);
            switch (key) {
                case 'o':
                    try {
                        linkedStack.pop();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'u':
                    int data = scanner.nextInt();
                    linkedStack.push(data);
                    break;
                case 's':
                    linkedStack.show();
                    break;
                case 'e':
                    loop = false;
                    break;
                default:
                    break;


            }
        }



    }
}



class LinkedStack {
    private Node head;

    public LinkedStack() {
        head = new Node(-1);
    }

    public boolean isEmpty() {
       return head.getNext() == null;
    }

    public void push (int no) {
        Node node = new Node(no);
        node.setNext(head.getNext());
        head.setNext(node);
    }

    public void pop() throws Exception {
        if (isEmpty()) {
            throw new Exception("this stack is empty");
        }
        Node tmp = head;
        head.setNext(head.getNext().getNext());
    }


    public void show() {
        Node temp = head;
        while (true) {
            if (temp != head) {
                System.out.printf("this node is %d \n", temp.getNo());
            }
            if (temp.getNext() == null) {
                break;
            }
            temp = temp.getNext();
        }
    }
    

}



class Node {
    private int no;
    private Node next;

    public Node(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
