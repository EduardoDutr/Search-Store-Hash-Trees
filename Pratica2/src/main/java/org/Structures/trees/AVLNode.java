package org.Structures.trees;

public class AVLNode<T extends Comparable<T>> extends GenericNode<T> {
    private AVLNode<T> leftChild, rightChild;

    public AVLNode(T data) {
        super(data);
    }

    public AVLNode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(AVLNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public AVLNode<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(AVLNode<T> rightChild) {
        this.rightChild = rightChild;
    }
}
