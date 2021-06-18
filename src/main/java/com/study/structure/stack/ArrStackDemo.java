package com.study.structure.stack;

import java.util.Scanner;

public class ArrStackDemo {
    public static void main(String[] args) {
        char key = ' ';
        ArrStack arrStack = new ArrStack(4);
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
                        arrStack.pop();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'u':
                    int data = scanner.nextInt();
                    arrStack.push(data);
                    break;
                case 's':
                    arrStack.show();
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

class ArrStack {
    private int maxSize;
    private int[] arr;
    private int top = -1;

    public ArrStack(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == maxSize -1;
    }

    public void push(int data) {
        if(isFull()) {
            System.out.println("this stack is full ... ");
            return;
        }

        top ++;
        arr[top] = data;
    }

    public void pop() throws Exception {
        if(isEmpty()) {
            throw new Exception("this stack is empty ... ");
        }
        top --;
    }

    public void show() {
        if(isEmpty()) {
            System.out.println("this stack is empty...");
            return;
        }
        for(int i = top; i >= 0; i -- ) {
            System.out.printf("第%d位数为%d \n", i, arr[i]);
        }
    }


}


