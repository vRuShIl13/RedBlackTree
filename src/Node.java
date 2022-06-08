/*
Red Black Trees are trees that contain nodes.
the nodes will store a number of information
The nodes will  either be black or red in color.
The node contains a key,left and right and a pointer to the parent

NOTE: The node can only be red or black</>
 */


import java.util.Objects;

public class Node {
    private String value;
    private int key;
    private String color;
    private Node right;
    private Node left;
    private Node pParent;


    //skeleton constructor
    public Node() {
        value = "";
        key= -1;
        color = "";
        right = null;
        left = null;
        pParent= null;

    }

    //second constructor
    public Node(int key,String value ) {
        this.value = value;
        this.key = key;
    }

    //=========================================================================================================


    //=========================================================================================================
    //TOSTRING method that provides the details about the node.
    public String toString() {
        return "Node{" +
                "value='" + value + '\'' +
                ", key=" + key +
                ", color='" + color + '\'' +
                ", right=" + right +
                ", left=" + left +
                ", pParent=" + pParent +
                '}';
    }

    //=========================================================================================================
    //GETTERS AND SETTERS

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getpParent() {
        return pParent;
    }

    public void setpParent(Node pParent) {
        this.pParent = pParent;
    }

    //=========================================================================================================
}
