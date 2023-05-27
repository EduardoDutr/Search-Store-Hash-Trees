package Structures;
import Enum.Color;
public class Node {
    private Object key;
    private int height;
    private Node left, right,parent;
    private Color color;

    public Node(Object d) {
        key = d;
        height = 1;
    }

    public Node getParent() {
        return parent;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getKey() {
        return key;
    }
}
