package com.study.structure.tree;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);

        System.out.println("顺序二叉树前序排序-----------------------");
        arrBinaryTree.preOrder(0);
        System.out.println("顺序二叉树中序排序-----------------------");
        arrBinaryTree.infixOrder(0);
        System.out.println("顺序二叉树后序排序-----------------------");
        arrBinaryTree.postOrder(0);

    }

}


class ArrBinaryTree {
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder(int index) {
        if(arr == null || arr.length == 0) {
            return;
        }

        System.out.println(arr[index]);

        if((index * 2 + 1) < arr.length) {
            preOrder(index * 2 + 1);
        }

        if((index * 2 + 2) < arr.length) {
            preOrder(index * 2 + 2);
        }
    }

    public void infixOrder(int index) {
        if(arr == null || arr.length == 0) {
            return;
        }

        if((index * 2 + 1) < arr.length) {
            infixOrder(index * 2 + 1);
        }

        System.out.println(arr[index]);

        if((index * 2 + 2) < arr.length) {
            infixOrder(index * 2 + 2);
        }

    }

    public void postOrder(int index) {
        if(arr == null || arr.length == 0) {
            return;
        }

        if((index * 2 + 1) < arr.length) {
            postOrder(index * 2 + 1);
        }

        if((index * 2 + 2) < arr.length) {
            postOrder(index * 2 + 2);
        }

        System.out.println(arr[index]);
    }

}
