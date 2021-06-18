package com.study.structure.queue;

import java.util.Scanner;

public class ArrQueueDemo {
    public static void main(String[] args) {
        ArrQueue queue = new ArrQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("a(add): 增加队列");
            System.out.println("h(head): 显示队列头");
            System.out.println("g(get): 获取队列");
            System.out.println("e(exit): 退出");

            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    int num = scanner.nextInt();
                    queue.addQueue(num);
                    break;
                case 'h':
                    queue.headQueue();
                    break;
                case 'g':
                    queue.getQueue();
                    break;
                case 'e':
                    scanner.remove();
                    loop = false;
                    break;

            }

        }

    }
}

/**
 * 一次性队列
 */
class ArrQueue {
    private int maxArrSize; // 数组最大容量
    private int front;      // 指针头 头数据的前一位下标
    private int rear;       // 指针尾 尾数据的下标
    private int[] arr;

    public ArrQueue(int maxArrSize) {
        this.maxArrSize = maxArrSize;
        front = -1;
        rear = -1;
        arr = new int[maxArrSize];
    }

    public boolean isFull() {
        return rear == maxArrSize - 1;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void addQueue(int n) {
        if(isFull()) {
            System.out.println("队列已满... 无法添加");
            return;
        }
        rear ++;
        arr[rear] = n;
    }

    public int getQueue() {
        if(isEmpty()) {
            System.out.println("队列已经空，无法获取数据");
            throw new RuntimeException("队列已经空，无法获取数据");
        }
        front ++;
        return arr[front];
    }

    public void showQueue() {
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d数据为：%d\n", i, arr[i]);
        }
    }

    public void headQueue() {
        if(isEmpty()) {
            System.out.println("队列已经空，无法获取数据");
            throw new RuntimeException("队列已经空，无法获取数据");
        }
        int head = arr[front + 1];
        System.out.printf("头部数据为：%d\n",  head);
    }


}
