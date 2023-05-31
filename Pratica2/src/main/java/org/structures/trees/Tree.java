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

    public abstract int size();
    protected int size(GenericNode<T> node){
        int count = 0;
        if (node != null) {
            count++;
            count += size(node.getLeftChild());;
            count += size(node.getRightChild());
        }
        return count;
    }

    protected void preOrder(GenericNode<T> node){
        if (node != null) {
            preOrder(node.getLeftChild());
            System.out.println(node.getData());
            preOrder(node.getRightChild());
        }
    }
}