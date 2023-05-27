package org.Structures.trees;

public class BSNode<T extends Comparable<T>> extends GenericNode<T> {

    private BSNode<T> leftChild, rightChild;
    public BSNode(T data) {
        super(data);
    }

    public BSNode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BSNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public BSNode<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(BSNode<T> rightChild) {
        this.rightChild = rightChild;
    }
}
