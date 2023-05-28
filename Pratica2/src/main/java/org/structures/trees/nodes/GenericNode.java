package org.structures.trees.nodes;

public abstract class GenericNode<T> {
    private T data;
    private int height;
    private GenericNode<T> leftChild, rightChild;

    public GenericNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public abstract GenericNode<T> getLeftChild();

    public abstract GenericNode<T> getRightChild();

}
