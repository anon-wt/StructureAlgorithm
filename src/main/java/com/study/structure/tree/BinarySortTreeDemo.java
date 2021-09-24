package com.study.structure.tree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9};
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

    public BSTNode search(int value) {
        if(root == null) {
            System.out.println("该节点为空");
            return null;
        } else {
            return root.search(value);
        }
    }

    public BSTNode queryParent(int value) {
        if(root == null) {
            System.out.println("该节点为空");
            return null;
        } else {
            return root.queryParent(value);
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

    public BSTNode findMinNode(BSTNode node) {
        if (node == null ) {
            return null;
        }

        BSTNode minNode = node;
        BSTNode parent = node;
        while (minNode.getLeft() != null) {
            parent = minNode;
            minNode = minNode.getLeft();
        }
        parent.setLeft(null);
        return minNode;
    }

    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            BSTNode targetNode = search(value);
            // 如果没有找到
            if (targetNode == null) {
                return;
            }
            // 如果找到了只有根节点一个结点， 直接把跟节点置空
            if (root.getLeft() == null && root.getRight() == null) {
                root = null;
                return;
            }
            BSTNode parentNode = queryParent(value);
            // 如果是叶子节点， 判断是父节点的左节点还是右节点
            if(targetNode.getRight() == null && targetNode.getLeft() == null) {
                if (parentNode.getLeft() != null && parentNode.getLeft().getValue() == value) {
                    parentNode.setLeft(null);
                } else if (parentNode.getRight() != null && parentNode.getRight().getValue() == value) {
                    parentNode.setRight(null);
                }
            } else if(targetNode.getRight() != null && targetNode.getLeft() != null) { // 有两个子节点
                // 将右子树的最小节点删除，并把值赋给该节点
                BSTNode minNode = findMinNode(targetNode.getRight());
                targetNode.setValue(minNode.getValue());
            } else { // 有一个子节点
                if (targetNode.getLeft() != null) {
                    if (parentNode.getLeft().getValue() == targetNode.getValue()) {
                        parentNode.setLeft(targetNode.getLeft());
                    } else {
                        parentNode.setRight(targetNode.getLeft());
                    }
                } else  {
                    if (parentNode.getLeft().getValue() == targetNode.getValue()) {
                        parentNode.setLeft(targetNode.getRight());
                    } else {
                        parentNode.setRight(targetNode.getRight());
                    }
                }

            }
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
    public BSTNode search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {
            if (this.getLeft() != null) {
                return this.getLeft().search(value);
            } else {
                return null;
            }
        } else {
            if (this.getRight() != null) {
                return this.getRight().search(value);
            } else {
                return null;
            }
        }
    }

    public BSTNode queryParent(int value) {
       if((this.getLeft() != null && this.getLeft().getValue() == value) ||
               (this.getRight() != null && this.getRight().getValue() == value)) {
            return this;
        } else {
           if (value < this.getValue() && this.getLeft() != null) {
               return this.getLeft().queryParent(value);
           } else if (value >= this.getValue() && this.getRight() != null) {
               return this.getRight().queryParent(value);
           } else {
               return null;
           }
       }
    }

    // 3.
    @Override
    public String toString() {
        return "BSTNode{" +
                "value=" + value + "}";
    }
}
