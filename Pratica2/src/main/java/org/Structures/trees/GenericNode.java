package org.Structures.trees;

public abstract class GenericNode<T> {
    private T data;
    private int height;

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
}
