package org.Structures;

public class AVLTree<T extends Comparable<T>> {
    Node<T> root;

    int height(Node node) {
        if (node == null)
            return 0;
        return node.getHeight();
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    Node<T> rightRotate(Node<T> y) {
        Node<T> x = y.getLeft();
        Node<T> T2 = x.getRight();

        x.setRight(y);
        y.setLeft(T2);

        y.setHeight(max(height(y.getLeft()), height(y.getRight())) + 1) ;
        x.setHeight(max(height(x.getLeft()), height(x.getRight())) + 1) ;

        return x;
    }

    private Node<T> leftRotate(Node<T> x) {
        Node<T> y = x.getRight();
        Node<T> T2 = y.getLeft();

        y.setLeft(x);
        x.setRight(T2);

        x.setHeight(max(height(x.getLeft()), height(x.getRight())) + 1);
        y.setHeight(max(height(y.getLeft()), height(y.getRight())) + 1);

        return y;
    }

    int getBalance(Node<T> node) {
        if (node == null)
            return 0;
        return height(node.getLeft()) - height(node.getRight());
    }

    Node<T> insert(Node<T> node, T key) {
        if (node == null)
            return (new Node(key));

        if (key.compareTo(node.getKey()) < 0)
            node.setLeft(insert(node.getLeft(), key));
        else if (key.compareTo(node.getKey()) > 0)
            node.setRight(insert(node.getRight(), key));
        else
            return node;

        node.setHeight(1 + max(height(node.getLeft()), height(node.getRight()   )));

        int balance = getBalance(node);

        if (balance > 1 && key.compareTo(node.getLeft().getKey()) < 0)
            return rightRotate(node);

        if (balance < -1 && key.compareTo(node.getRight().getKey()) > 0)
            return leftRotate(node);

        if (balance > 1 && key.compareTo(node.getLeft().getKey()) > 0) {
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }

        if (balance < -1 && key.compareTo(node.getRight().getKey()) < 0) {
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }

        return node;
    }

    void preOrder(Node<T> node) {
        if (node != null) {
            System.out.print(node.getKey() + " ");
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }
}