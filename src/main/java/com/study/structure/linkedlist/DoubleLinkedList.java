package com.study.structure.linkedlist;

public class DoubleLinkedList {
    public static void main(String[] args) {
        DoubleLinkedNode doubleLinkedNode = new DoubleLinkedNode();

        HeroNode2 songjiang = new HeroNode2(1, "宋江");
        HeroNode2 lujunyi = new HeroNode2(2, "卢俊义");
        HeroNode2 wuyong = new HeroNode2(3, "吴用");
        HeroNode2 likui = new HeroNode2(4, "李逵");

//        doubleLinkedNode.add(songjiang);
//        doubleLinkedNode.add(likui);
//        doubleLinkedNode.add(lujunyi);
//        doubleLinkedNode.add(wuyong);
//        doubleLinkedNode.list();
        doubleLinkedNode.addByOrder(songjiang);
        doubleLinkedNode.addByOrder(likui);
        doubleLinkedNode.addByOrder(lujunyi);
        doubleLinkedNode.addByOrder(wuyong);
        doubleLinkedNode.list();

        doubleLinkedNode.delete(2);
        doubleLinkedNode.list();

        HeroNode2 wuyong2 = new HeroNode2(3, "吴用用");

        doubleLinkedNode.update(wuyong2);
        doubleLinkedNode.list();


    }

}


class DoubleLinkedNode {
    private HeroNode2 headNode = new HeroNode2(-1, "");

    public DoubleLinkedNode() {
    }

    public HeroNode2 getHeadNode() {
        return headNode;
    }


    // 1. 新增
    public void add(HeroNode2 heroNode) {
        HeroNode2 tmp = headNode;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.next = heroNode;
        heroNode.pre = tmp;
    }

    public void addByOrder(HeroNode2 heroNode) {
        HeroNode2 tmp = headNode;
        while (true) {
            if (tmp.next == null) {
                heroNode.pre = tmp;
                tmp.next = heroNode;
                break;
            }

            if (tmp.no == heroNode.no) {
                System.out.printf("this no[%d] is exist!\n", heroNode.no);
                break;
            } else if (tmp.next.no > heroNode.no ) {
                heroNode.pre = tmp;
                heroNode.next = tmp.next;
                tmp.next.pre = heroNode;
                tmp.next = heroNode;
                break;
            }

            tmp = tmp.next;
        }



    }


    // 2. 删除
    public void delete(int no) {
        if (headNode.next == null) {
            System.out.println("this is empty list");
            return;
        }

        HeroNode2 tmp = headNode.next;
        boolean flag = false;
        while (tmp.next != null) {
            if (tmp.no == no) {
                flag = true;
                break;
            }
            tmp = tmp.next;
        }

        if (flag) {
            tmp.pre.next = tmp.next;
            tmp.next.pre = tmp.pre;
        }
    }

    // 3. 修改
    public void update(HeroNode2 heroNode2) {
        HeroNode2 tmp = headNode.next;
        boolean flag = false;
        while (tmp.next != null) {
            if (tmp.no == heroNode2.no) {
                flag = true;
                break;
            }
            tmp = tmp.next;
        }

        if (flag) {
            tmp.name = heroNode2.name;
        }
    }

    // 4. list
    public void list() {
        HeroNode2 tmp = headNode.next;
        while (tmp != null) {
            System.out.println("this node: " + tmp);
            tmp = tmp.next;
        }


    }



}


class HeroNode2 {
    int no;
    String name;
    HeroNode2 next;
    HeroNode2 pre;

    public HeroNode2(int no, String name) {
        this.no = no;
        this.name = name;
    }



    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}