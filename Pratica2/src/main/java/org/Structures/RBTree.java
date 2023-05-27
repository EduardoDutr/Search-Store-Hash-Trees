package org.Structures;

<<<<<<< HEAD:Pratica2/src/main/java/Structures/RBTree.java
package Structures;
import Enum.Color;
import org.Structures.enums.Color;

import java.util.Comparator;
=======
package org.Structures;
>>>>>>> e72cb3c7f009180a59a3e1376c8d36f85ec4c3da:Pratica2/src/main/java/org/Structures/RBTree.java

class RBTree {
    Node root;
    Comparator<Object> comparator;

    RBTree(Comparator<Object> comparator) {
        this.comparator = comparator;
    }

    Node search(Node node, Object key) {
        if (node == null || key.equals(node.getKey()))
            return node;

        if (comparator.compare(key, node.getKey()) < 0)
            return search(node.getLeft(), key);

        return search(node.getRight(), key);
    }

    void insert(Object key) {
        Node newNode = new Node(key);
        newNode.setColor(Color.RED);

        root = insertNode(root, newNode);

        fixInsertion(newNode);
    }

    Node insertNode(Node root, Node newNode) {
        if (root == null)
            return newNode;

        if (comparator.compare(newNode.getKey(), root.getKey()) < 0) {
            root.setLeft(insertNode(root.getLeft(), newNode));
            root.getLeft().setParent(root);
        } else if (comparator.compare(newNode.getKey(), root.getKey()) > 0) {
            root.setRight(insertNode(root.getRight(), newNode));
            root.getRight().setParent(root);
        }

        return root;
    }

    void fixInsertion(Node node) {
        Node parent, grandParent;

        while (node != root && node.getColor() == Color.RED && node.getParent().getColor() == Color.RED) {
            parent = node.getParent();
            grandParent = parent.getParent();

            if (parent == grandParent.getLeft()) {
                Node uncle = grandParent.getRight();

                if (uncle != null && uncle.getColor() == Color.RED) {
                    grandParent.setColor(Color.RED);
                    parent.setColor(Color.BLACK);
                    uncle.setColor(Color.BLACK);
                    node = grandParent;
                } else {
                    if (node == parent.getRight()) {
                        rotateLeft(parent);
                        node = parent;
                        parent = node.getParent();
                    }

                    rotateRight(grandParent);
                    Color tempColor = parent.getColor();
                    parent.setColor(grandParent.getColor());
                    grandParent.setColor(tempColor);
                    node = parent;
                }
            } else {
                Node uncle = grandParent.getLeft();

                if (uncle != null && uncle.getColor() == Color.RED) {
                    grandParent.setColor(Color.RED);
                    parent.setColor(Color.BLACK);
                    uncle.setColor(Color.BLACK);
                    node = grandParent;
                } else {
                    if (node == parent.getLeft()) {
                        rotateRight(parent);
                        node = parent;
                        parent = node.getParent();
                    }

                    rotateLeft(grandParent);
                    Color tempColor = parent.getColor();
                    parent.setColor(grandParent.getColor());
                    grandParent.setColor(tempColor);
                    node = parent;
                }
            }
        }

        root.setColor(Color.BLACK);
    }

    void rotateLeft(Node node) {
        Node rightChild = node.getRight();
        node.setRight(rightChild.getLeft());

        if (node.getRight() != null)
            node.getRight().setParent(node);

        rightChild.setParent(node.getParent());

        if (node.getParent() == null)
            root = rightChild;
        else if (node == node.getParent().getLeft())
            node.getParent().setLeft(rightChild);
        else
            node.getParent().setRight(rightChild);

        rightChild.setLeft(node);
        node.setParent(rightChild);
    }

    void rotateRight(Node node) {
        Node leftChild = node.getLeft();
        node.setLeft(leftChild.getRight());

        if (node.getLeft() != null)
            node.getLeft().setParent(node);

        leftChild.setParent(node.getParent());

        if (node.getParent() == null)
            root = leftChild;
        else if (node == node.getParent().getLeft())
            node.getParent().setLeft(leftChild);
        else
            node.getParent().setRight(leftChild);

        leftChild.setRight(node);
        node.setParent(leftChild);
    }

    void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.getKey() + " ");
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }
}