package com.study.structure.linkedlist;

public class Josepfu {
    public static void main(String[] args) throws Exception {
        CircleSingleLink circleSingleLink = new CircleSingleLink();
        circleSingleLink.add(5);
        circleSingleLink.show();
        circleSingleLink.countBoy(1, 2, 5);

    }


}


class CircleSingleLink {
    private Boy first;

    public CircleSingleLink() {
    }

    public void add(int num) throws Exception {
        if (num < 1) {
            throw new Exception("CircleSingleLink num must be bigger 1");
        }

        Boy curr = null;
        for (int i = 1; i <= num; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.setNext(first);
                curr = first;
            } else {
                curr.setNext(boy);
                boy.setNext(first);
                curr = boy;
            }
        }
    }

    public void show() throws Exception {
        Boy curr = first;
        if (curr == null) {
            throw new Exception("CircleSingleLink is empty");
        }
        while (true) {
            System.out.printf("this boy no is [%d] \n", curr.getNo());
            if (curr.getNext() == first) {
                break;
            }
            curr = curr.getNext();
        }
    }

    /**
     * 约瑟夫问题
     * @param start 起始位置
     * @param slip 数几个
     * @param num 总数
     */
    public void countBoy(int start, int slip, int num) {
        if (start < 1 || start > num) {
            System.out.printf("起始位置应在1-%d 之间\n", num);
        }
        // 1. 设置一个first位置， 以及一个before 位置， first 位置数据将被丢弃， before 位置在first 位置之前
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }

        // 2. 选择起始位置
        for (int i = 0; i < start -1 ; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        // 3. 根据滑动步伐
        while (true) {
            if (helper == first) {
                break;
            }
            for (int i = 0; i < slip -1 ; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("输出的节点no is [%d] \n", first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("输出的最后一个节点no is [%d] \n", first.getNo());
    }
}

class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
