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
            System.out.println("查找：输入[query]");
            System.out.println("查找：输入[delete]");
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
                case "query":
                    System.out.println("请输入id:");
                    id = scanner.nextInt();
                    empHashTable.query(id);
                    break;
                case "delete":
                    System.out.println("请输入id:");
                    id = scanner.nextInt();
                    empHashTable.delete(id);
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

    public void add2(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }


        Emp temp;
        if (head.id > emp.id) {
            temp = head;
            head = emp;
            emp.next = temp;
            return;
        }

        Emp currEmp = head;
        while (currEmp.next != null && currEmp.next.id <= emp.id) {
            currEmp = currEmp.next;
        }
        temp = currEmp.next;
        currEmp.next = emp;
        emp.next = temp;
    }

    public void list(int index) {
        if (head == null) {
            System.out.println("this EmpLinkedArray is empty");
            return;
        }
        Emp currEmp = head;
        while (true) {
            System.out.printf("第%d个数组，链表中数据为：", index+1);
            System.out.printf(" => [id=%d,name=%s]", currEmp.id, currEmp.name);
            if (currEmp.next == null) {
                break;
            }
            currEmp = currEmp.next;
        }
        System.out.println("");
    }

    public Emp query(int id) {
        if (head == null) {
            return null;
        }
        Emp currEmp = head;
        while (true) {
            if (currEmp.id == id) {
                break;
            }
            if (currEmp.next == null ) {
                currEmp = currEmp.next;
                break;
            }
            currEmp = currEmp.next;
        }
        return currEmp;
    }


    public void delete(int id) {
        if (head == null) {
            return;
        }
        if (head.id == id) {
            head = head.next;
            return;
        }

        Emp currEmp = head;
        while (true) {
            if (currEmp.next == null ) {
                break;
            }

            if (currEmp.next.id == id) {
                currEmp.next = currEmp.next.next;
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
//        arr[index].add(emp);
        arr[index].add2(emp);
    }

    public void list() {
        for (int i = 0; i < size; i++) {
            arr[i].list(i);
        }
    }

    public void delete(int id) {
        int index = hashFunc(id);
        arr[index].delete(id);
    }

    public void query(int id) {
        int index = hashFunc(id);
        Emp emp = arr[index].query(id);
        if (null != emp) {
            System.out.printf("找到了该雇员，其信息为[id=%d,name=%s]", emp.id, emp.name);
        } else {
            System.out.println("没有找到该雇员");
        }
    }



    public int hashFunc(int id) {
        return id % size;
    }
}
