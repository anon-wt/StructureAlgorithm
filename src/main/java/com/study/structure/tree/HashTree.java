package com.study.structure.tree;

import java.util.Scanner;

public class HashTree {
    public static void main(String[] args) {


        EmpHashTable empHashTable = new EmpHashTable(7);
        Scanner scanner = new Scanner(System.in);
        String key;
        int id;
        String name;
        Emp emp;

        while (true) {
            System.out.println("添加：输入[add]");
            System.out.println("遍历：输入[list]");
            System.out.println("退出：输入[exit]");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("请输入id:");
                    id = scanner.nextInt();
                    System.out.println("请输入name:");
                    name = scanner.next();
                    emp = new Emp(id, name);
                    empHashTable.add(emp);
                    System.out.println("添加成功");
                    break;
                case "list":
                    empHashTable.list();
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;
            }
        }


    }


}
class Emp {
    public Emp next;

    public int id;
    public String name;


    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class EmpLinkedArray {
    private Emp head;

    // 1. add
    public void add(Emp emp) {
        if (head == null) {
            head = emp;
        } else {
            Emp currEmp = head;
            while (true) {
                if (currEmp.next == null) {
                    currEmp.next = emp;
                    break;
                }
                currEmp = currEmp.next;
            }
        }
    }

    public void list() {
        if (head == null) {
            System.out.println("this EmpLinkedArray is empty");
            return;
        }
        Emp currEmp = head;
        while (true) {
            System.out.printf(" => [id=%d,name=%s]", currEmp.id, currEmp.name);
            if (currEmp.next == null) {
                break;
            }
            currEmp = currEmp.next;
        }

    }

}

class EmpHashTable {
    private EmpLinkedArray[] arr;
    private int size;

    public EmpHashTable(int size) {
        this.size= size;
        this.arr = new EmpLinkedArray[size];

        for (int i = 0; i < size; i++) {
            arr[i] = new EmpLinkedArray();
        }

    }

    public void add(Emp emp) {
        int index = hashFunc(emp.id);
        arr[index].add(emp);
    }

    public void list() {
        for (int i = 0; i < size; i++) {
            System.out.printf("第%d个数组，链表中数据为：", i+1);
            arr[i].list();
            System.out.println();

        }
    }


    public int hashFunc(int id) {
        return id % size;
    }
}
