package com.study.structure.linkedlist;

import java.util.Stack;

public class SingleLinkedList {
    public static void main(String[] args) {
        // 1. 创建英雄
        HeroNode songjiang = new HeroNode(1, "宋江");
        HeroNode lujunyi = new HeroNode(2, "卢俊义");
        HeroNode wuyong = new HeroNode(3, "吴用");
        HeroNode likui = new HeroNode(4, "李逵");

//        SingleHeroNode singleHeroNode = new SingleHeroNode();
//        singleHeroNode.add(songjiang);
//        singleHeroNode.add(likui);
//        singleHeroNode.add(lujunyi);
//        singleHeroNode.add(wuyong);

//        singleHeroNode.addByOrder(songjiang);
//        singleHeroNode.addByOrder(likui);
//        singleHeroNode.addByOrder(lujunyi);
//        singleHeroNode.addByOrder(wuyong);
//
//        HeroNode linchong = new HeroNode(4, "林冲");
//        singleHeroNode.update(linchong);
//
//        singleHeroNode.delete(2);
//        singleHeroNode.list();
//        System.out.printf("length: %d \n", singleHeroNode.getLength());
//        System.out.println("倒数第1个节点是：" + singleHeroNode.getLastIndexNode(1));
//
//        singleHeroNode.reserveNode();
//        singleHeroNode.list();
//        System.out.println("=================================================");
//        singleHeroNode.print();
//        singleHeroNode.print2();

        System.out.println("==================================");
        SingleHeroNode singleHeroNode1 = new SingleHeroNode();
        singleHeroNode1.addByOrder(songjiang);
        singleHeroNode1.addByOrder(wuyong);
//
        SingleHeroNode singleHeroNode2 = new SingleHeroNode();
        singleHeroNode2.addByOrder(lujunyi);
        singleHeroNode2.addByOrder(likui);

        singleHeroNode1.union(singleHeroNode2);
        singleHeroNode1.list();
//        singleHeroNode2.list();
    }

}


class SingleHeroNode {
    private HeroNode headHeroNode = new HeroNode(-1, null, null);
    public SingleHeroNode() {
    }

    // 1.添加到后面
    public void add(HeroNode heroNode) {
        HeroNode temp =  headHeroNode;

        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    // 2.按序添加
    public void addByOrder(HeroNode heroNode) {
        HeroNode temp =  headHeroNode;
        while (true) {
            if (temp.next == null) {
                temp.next = heroNode;
                break;
            }
            if (heroNode.no < temp.next.no) {
                heroNode.next = temp.next;
                temp.next = heroNode;
                break;
            } else if (heroNode.no == temp.next.no) {
                System.out.printf("no[%d] 已经存在", heroNode.no);
                break;
            }
            temp = temp.next;
        }
    }

    // 3. 遍历
    public void list() {
        HeroNode temp =  headHeroNode.next;
        if (temp == null) {
            System.out.println("链表为空~~~");
            return;
        }
        while (true) {
            System.out.println("hero: " + temp.toString());
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
    }

    // 4. 修改
    public void update(HeroNode newHeroNode) {
        HeroNode temp =  headHeroNode;
        while (true) {
            if (newHeroNode.no == temp.no) {
                temp.name = newHeroNode.name;
                System.out.printf("no[%d] 已经修改\n", newHeroNode.no);
                break;
            }

            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
    }

    // 5. 删除
    public void delete(int no) {
        HeroNode temp =  headHeroNode;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                temp.next = temp.next.next;
                System.out.printf("no[%d] 已经删除\n", no);
                break;
            }
            temp = temp.next;
        }
    }

    // 6. 单链表的length
    public int getLength() {
        HeroNode node = headHeroNode;
        int length = 0;
        while (node.next != null) {
            length ++;
            node = node.next;
        }
        return length;
    }

    // 7. 查找链表倒数第k个节点
    public HeroNode getLastIndexNode(int index) {
        // 1. 获得节点个数
        int length = getLength();

        if (index <= 0 || index > length) {
            System.out.printf("out of index: %d\n", index);
            throw new RuntimeException("out of index");
        }

        HeroNode node = headHeroNode.next;
        for (int i = 0; i < length - index; i ++) {
            node = node.next;
        }
        return node;
    }

    /**
     * 反转链表
     */
    public void reserveNode() {
        if (headHeroNode.next == null || headHeroNode.next.next == null) {
            return;
        }
        HeroNode head = new HeroNode(0, "");
        HeroNode curr = headHeroNode.next;
        HeroNode next;
        while (curr != null) {
            next = curr.next;
            curr.next = head.next;
            head.next = curr;
            curr = next;
        }
        headHeroNode.next = head.next;
    }

    public void print() {
        // 打印链表
        Stack<HeroNode> heroNodes = new Stack<HeroNode>();
        HeroNode curr = headHeroNode.next;
        while (curr != null) {
            heroNodes.push(curr);
            curr = curr.next;
        }

        while (heroNodes.size() > 0) {
            System.out.println(heroNodes.pop());
        }
    }
    // 不推荐， 破坏了原有的链表结构
    public void print2() {
        reserveNode();
        HeroNode curr = headHeroNode.next;
        while (curr != null) {
            System.out.println(curr);
            curr = curr.next;
        }
    }

    public void union(SingleHeroNode singleHeroNode) {
        HeroNode curr = singleHeroNode.headHeroNode.next;
        HeroNode next;
        while (curr != null) {
            next = curr.next;
            addByOrder(curr);
            curr = next;
        }
    }



}


class HeroNode {
    int no;
    String name;
    HeroNode next;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }


    public HeroNode(int no, String name, HeroNode next) {
        this.no = no;
        this.name = name;
        this.next = next;
    }


    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}