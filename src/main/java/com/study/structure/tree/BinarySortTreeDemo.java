package com.study.structure.tree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i : arr) {
            binarySortTree.add(new BSTNode(i));
        }

        binarySortTree.infixSearch();
    }


}
class BinarySortTree {
    private BSTNode root;

    public BinarySortTree() {
    }

    public BinarySortTree(BSTNode root) {
        this.root = root;
    }

    public BSTNode getRoot() {
        return root;
    }

    public void setRoot(BSTNode root) {
        this.root = root;
    }
    // 1. add
    public void add(BSTNode node) {
        if(root == null) {
            this.setRoot(node);
        } else {
            root.add(node);
        }
    }

    public BSTNode query(int value) {
        if(root == null) {
            System.out.println("该节点为空");
            return null;
        } else {
            return root.query(value);
        }
    }

    // 2. infixSearch
    public void infixSearch() {
        if(root == null) {
            System.out.println("根节点为空");
        } else {
            root.infixSearch();
        }
    }



}

class BSTNode {
    private int value;
    private BSTNode left;
    private BSTNode right;


    public BSTNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BSTNode getLeft() {
        return left;
    }

    public void setLeft(BSTNode left) {
        this.left = left;
    }

    public BSTNode getRight() {
        return right;
    }

    public void setRight(BSTNode right) {
        this.right = right;
    }

    // 1. add
    public void add(BSTNode node) {
        if (node.getValue() < this.getValue()) {
            if (this.getLeft() != null) {
                this.getLeft().add(node);
            } else {
                this.setLeft(node);
            }
        } else {
            if (this.getRight() != null) {
                this.getRight().add(node);
            } else {
                this.setRight(node);
            }
        }
    }

    // 2. 中序查找
    public void infixSearch() {
        if (this.getLeft() != null) {
            this.getLeft().infixSearch();
        }
        System.out.println(this);
        if (this.getRight() != null) {
            this.getRight().infixSearch();
        }
    }

    // 4. 查找结点
    public BSTNode query(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {
            if (this.getLeft() != null) {
                return this.getLeft().query(value);
            } else {
                return null;
            }
        } else {
            if (this.getRight() != null) {
                return this.getRight().query(value);
            } else {
                return null;
            }
        }
    }

    public BSTNode queryParent(int value) {
       if((this.getLeft() != null && this.getLeft().getValue() == value) ||
               (this.getRight() != null && this.getRight().getValue() == value)) {
            return this;
        } else if (this.getLeft() == null && this.getRight() != null) {
            return this.getRight().queryParent(value);
        } else if (this.getRight() == null && this.getLeft() != null) {
           return this.getLeft().queryParent(value);
       } else {
           return null;
       }
    }

    // 3.
    @Override
    public String toString() {
        return "BSTNode{" +
                "value=" + value + "}";
    }
}
