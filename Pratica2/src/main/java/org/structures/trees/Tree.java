package org.structures.trees;

import org.structures.trees.nodes.GenericNode;

public abstract class Tree<T extends Comparable<T>> {
    public abstract void insert(T data);

    public abstract void delete(T data);

    public abstract void traverse();

    public abstract T getMax();

    public abstract T getMin();

    public abstract boolean isEmpty();

    public abstract void printAll();

    protected void preOrder(GenericNode<T> node){
        if (node != null) {
            preOrder(node.getLeftChild());
            System.out.print(node.getData() + " ");
            preOrder(node.getRightChild());
        }
    }
}