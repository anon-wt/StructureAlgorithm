package com.study.structure.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        Node node1 = new Node(1, "1");
        Node node2 = new Node(2, "2");
        Node node3 = new Node(3, "3");
        Node node4 = new Node(4, "4");
        Node node5 = new Node(5, "5");
        Node node6 = new Node(6, "6");

        node1.setLeft(node2);
        node1.setRight(node3);

        node2.setLeft(node4);
        node2.setRight(node5);

        node3.setLeft(node6);

        BinaryTree binaryTree = new BinaryTree();
        binaryTree.setRoot(node1);

        System.out.println("pre======================");
        binaryTree.preOrder();
        System.out.println("mid======================");
        binaryTree.infixOrder();
        System.out.println("last======================");
        binaryTree.postOrder();

    }

}


class BinaryTree {
    private Node root;


    public BinaryTree() {
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }




    public void preOrder() {
        if(root != null) {
            root.preOrder();
        } else {
            System.out.println("root is null");
        }
    }

    public void infixOrder() {
        if(root != null) {
            root.infixOrder();
        } else {
            System.out.println("root is null");
        }
    }

    public void postOrder() {
        if(root != null) {
            root.postOrder();
        } else {
            System.out.println("root is null");
        }
    }




    public void preOrderSearch(int id) {
        if(root != null) {
            root.preOrderSearch(id);
        } else {
            System.out.println("root is null");
        }
    }

    public void infixOrderSearch(int id) {
        if(root != null) {
            root.infixOrderSearch(id);
        } else {
            System.out.println("root is null");
        }
    }

    public void postOrderSearch(int id) {
        if(root != null) {
            root.postOrderSearch(id);
        } else {
            System.out.println("root is null");
        }
    }

}




class Node{
    private int id;
    private String name;
    private Node left;
    private Node right;

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }


    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public void preOrder() {
        System.out.println(this);

        if(this.getLeft() != null) {
            this.getLeft().preOrder();
        }

        if (this.getRight() != null) {
            this.getRight().preOrder();
        }
    }

    public void infixOrder() {
        if(this.getLeft() != null) {
            this.getLeft().infixOrder();
        }

        System.out.println(this);

        if (this.getRight() != null) {
            this.getRight().infixOrder();
        }
    }

    public void postOrder() {
        if(this.getLeft() != null) {
            this.getLeft().postOrder();
        }


        if (this.getRight() != null) {
           this.getRight().postOrder();
        }

        System.out.println(this);
    }


    public Node preOrderSearch(int id) {
        Node result = null;
        if (this.id == id) {
            return this;
        }

        if(this.getLeft() != null) {
            result = this.getLeft().preOrderSearch(id);
        }
        if (result != null) {
            return  result;
        }

        if (this.getRight() != null) {
            result = this.getRight().preOrderSearch(id);
        }

        return result;
    }

    public Node infixOrderSearch(int id) {
        Node result = null;

        if(this.getLeft() != null) {
            result = this.getLeft().infixOrderSearch(id);
        }
        if (result != null) {
            return result;
        }

        if (this.id == id) {
            return this;
        }

        if (this.getRight() != null) {
            result = this.getRight().infixOrderSearch(id);
        }
        return result;
    }


    public Node postOrderSearch(int id) {
        Node result = null;

        if(this.getLeft() != null) {
            result = this.getLeft().postOrderSearch(id);
        }

        if (result != null) {
            return result;
        }

        if (this.getRight() != null) {
            result = this.getRight().postOrderSearch(id);
        }

        if (result != null) {
            return result;
        }

        if (this.id == id) {
            return this;
        }

        return result;
    }

}
