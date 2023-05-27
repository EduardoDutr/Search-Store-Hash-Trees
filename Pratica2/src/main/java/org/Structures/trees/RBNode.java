package org.Structures.trees;
import org.Structures.trees.enums.Color;

public class RBNode<T> extends GenericNode<T> {

    private RBNode<T> parent;
    private Color color;
    private RBNode<T> leftChild, rightChild;

    public RBNode(T data) {
        super(data);
        color = Color.RED;
    }

    public RBNode<T> getParent() {
        return parent;
    }

    public void setParent(RBNode<T> parent) {
        this.parent = parent;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public RBNode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(RBNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public RBNode<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(RBNode<T> rightChild) {
        this.rightChild = rightChild;
    }

    public boolean isLeftChild() {
        return this == parent.getLeftChild();
    }

    public void flipColor() {
        setColor(color == Color.RED ? Color.BLACK : Color.RED);
    }
}
