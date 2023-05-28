package org.structures.trees;

import org.structures.trees.nodes.BSNode;

public class BSTree<T extends Comparable<T>> extends Tree<T> {
    private BSNode<T> root;

    @Override
    public void insert(T data) {
        root = insert(data, root);
    }

    private BSNode<T> insert(T data, BSNode<T> node) {
        if (node == null) {
            return new BSNode<>(data);
        }
        if (data.compareTo(node.getData()) < 0) {
            node.setLeftChild(insert(data, node.getLeftChild()));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRightChild(insert(data, node.getRightChild()));
        }
        return node;
    }

    @Override
    public void delete(T data) {
        root = delete(data, root);
    }

    private BSNode<T> delete(T data, BSNode<T> node) {
        if (node == null) {
            return null;
        }
        if (data.compareTo(node.getData()) < 0) {
            node.setLeftChild(delete(data, node.getLeftChild()));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRightChild(delete(data, node.getRightChild()));
        } else {
            // One child or No children
            if (node.getLeftChild() == null) {
                return node.getRightChild();
            } else if (node.getRightChild() == null) {
                return node.getLeftChild();
            }
            // Two children
            node.setData(getMax(node.getLeftChild()));
            node.setLeftChild(delete(node.getData(), node.getLeftChild()));
        }
        return node;
    }

    @Override
    public void traverse() {
        traverseInOrder(root);
    }

    private void traverseInOrder(BSNode<T> node) {
        if (node == null) {
            return;
        }
        traverseInOrder(node.getLeftChild());
        System.out.println(node);
        traverseInOrder(node.getRightChild());
    }

    @Override
    public T getMax() {
        if (isEmpty()) {
            return null;
        }
        return getMax(root);
    }

    private T getMax(BSNode<T> node) {
        if (node.getRightChild() != null) {
            return getMax(node.getRightChild());
        }
        return node.getData();
    }

    @Override
    public T getMin() {
        if (isEmpty()) {
            return null;
        }
        return getMin(root);
    }

    private T getMin(BSNode<T> node) {
        if (node.getLeftChild() != null) {
            return getMin(node.getLeftChild());
        }
        return node.getData();
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void printAll() {
        preOrder(root);
    }
}
