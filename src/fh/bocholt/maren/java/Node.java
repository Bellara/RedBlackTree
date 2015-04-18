package fh.bocholt.maren.java;

/**
 * Created by Maren on 08.04.15.
 */
public class Node implements Comparable {

    private Node rightNode;
    private Node leftNode;

    private Node parent;

    private int value;

    private Color color;

    public Node(int value, Color color) {
        this.value = value;
        this.color = color;
    }

    public boolean hasChildren() {
        return !(leftNode == null && rightNode == null);
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean isBalanced(){
        if(rightNode != null){
            if(rightNode.getRightNode() != null && rightNode.getLeftNode() == null){
                return false;
            }
        }
        if(leftNode != null){
            if(leftNode.getLeftNode() != null && leftNode.getRightNode() == null){
                return false;
            }
        }
        return true;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
