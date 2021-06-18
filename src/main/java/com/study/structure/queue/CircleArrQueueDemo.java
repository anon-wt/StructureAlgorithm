package com.study.structure.queue;

import java.util.Scanner;

public class CircleArrQueueDemo {
    public static void main(String[] args) {
        CircleArrQueueQueue queue = new CircleArrQueueQueue(4);
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
 * 环形队列
 */
class CircleArrQueueQueue {
    private int maxArrSize; // 队列最大容量maxSize -1， 有一个作为标志位
    private int front;      // 指针头 头数据的下标
    private int rear;       // 指针尾 尾数据的后一位下标
    private int[] arr;

    public CircleArrQueueQueue(int maxArrSize) {
        this.maxArrSize = maxArrSize;
        arr = new int[maxArrSize];
    }

    public boolean isFull() {
        return (rear + 1 ) % maxArrSize == front;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void addQueue(int n) {
        if(isFull()) {
            System.out.println("队列已满... 无法添加");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) % maxArrSize;
    }

    public int getQueue() {
        if(isEmpty()) {
            System.out.println("队列已经空，无法获取数据");
            throw new RuntimeException("队列已经空，无法获取数据");
        }
        int value = arr[front];  // 0
        front = (front + 1) % maxArrSize;
        return value;
    }

    public void showQueue() {
        for (int i = front; i < front + size(); i++) {
            int index = i % maxArrSize;
            System.out.printf("arr[%d]数据为：%d\n", index, arr[index]);
        }
    }

    /**
     * rear = 0 maxArrSize = 4 front = 0
     * @return
     */
    public int size() {
        return (rear + maxArrSize - front) % maxArrSize;
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