/*
The is the main tree, it starts with 0 nodes ,and it will have the following functions:
 1. Insert
 2. Delete
 3. Rank
 4. Minimum
 5. Maximum
 6. Search
 7. Select
 */

public class RedBlackTree {
    private Node root;
    private Node nil;
    //augmented tree, maintaining 2 pointers for min and max keys in the tree
    private Node minimum;
    private Node maximum;


    //constructor
    public RedBlackTree() {
        root = null;
        nil = null;
        minimum = null;
        maximum = null;
    }


    //------------------------------------------------------------------------------------------------------------------
    //normal insert in a bst
    public void insert(int i, String v){
        Node y =  null;
        Node x = root;

        Node newNodeZ = new Node(i,v);

        //finding the correct position where the newNode should be
        //looping down the tree
        while (x != null){
            y = x;
            if(i < x.getKey()){
                x = x.getLeft();
            }else{
                x = x.getRight();
            }
        }
        //once a dead end is found, the newNode is linked to the last node visited.
        newNodeZ.setpParent(y);

        //if y is null that means the tree was empty and this is the first insertion
        if(y == null){
            root = newNodeZ;
            maximum = newNodeZ;
            minimum = newNodeZ;
        }else if (newNodeZ.getKey() < y.getKey()){
            y.setLeft(newNodeZ);
        }else{
            y.setRight(newNodeZ);
        }

        //checking if the new node inserted has the minimum or the maximum key.
        if(i< minimum.getKey()){
            minimum = newNodeZ;
        }
        if(i > maximum.getKey()){
            maximum = newNodeZ;
        }


        //the node is just created, so it doesnt have any left or right children
        //according to the laws of red black trees, a new node must be coloured red.
        newNodeZ.setLeft(null);
        newNodeZ.setRight(null);
        newNodeZ.setColor("RED");

        rbInsertFixup(newNodeZ);
    }

    //------------------------------------------------------------------------------------------------------------------
    //fixing the tree after inserting a node, could have cause the property 2 or 4
    private void rbInsertFixup(Node z) {
        Node y;

        while (z != root && z.getpParent().getColor().equals("RED")) {
            if (z.getpParent() == z.getpParent().getpParent().getLeft()) {
                y = z.getpParent().getpParent().getRight();

                if (y != null && y.getColor().equals("RED")) {
                    z.getpParent().setColor("BLACK");
                    y.setColor("BLACK");
                    z.getpParent().getpParent().setColor("RED");
                    z = z.getpParent().getpParent();

                } else {
                    if (z == z.getpParent().getRight()) {
                        z = z.getpParent();
                        leftRotate(z);
                        z.getpParent().setColor("BLACK");
                        z.getpParent().getpParent().setColor("RED");
                        rightRotate(z.getpParent().getpParent());
                    }

                }
            } else {//for the 3 cases in the right
                if (z.getpParent() == z.getpParent().getpParent().getRight()) {
                    y = z.getpParent().getpParent().getLeft();

                    if (y !=null && y.getColor().equals("RED")) {
                        z.getpParent().setColor("BLACK");
                        y.setColor("BLACK");
                        z.getpParent().getpParent().setColor("RED");
                        z = z.getpParent().getpParent();

                    } else {
                        if (z == z.getpParent().getLeft()) {
                            z = z.getpParent();
                            rightRotate(z);
                            z.getpParent().setColor("BLACK");
                            z.getpParent().getpParent().setColor("RED");
                            leftRotate(z.getpParent().getpParent());
                        }
                    }
                }
            }
        }
        root.setColor("BLACK");
    }

    //------------------------------------------------------------------------------------------------------------------
    //LEFT Rotate
    private void leftRotate(Node x){
        Node y = x.getRight();
        x.setRight(y.getLeft());

        if(y.getLeft() != null){
            y.getLeft().setpParent(x);
        }
        y.setpParent(x.getpParent());

        if(x.getpParent() == null){
            root = y;
        }else if(x == x.getpParent().getLeft()){
            x.getpParent().setLeft(y);
        }else{
            x.getpParent().setRight(y);
        }

        y.setLeft(x);
        x.setpParent(y);
    }

    //------------------------------------------------------------------------------------------------------------------
    //right rotate
    private void rightRotate(Node x){
        Node y = x.getLeft();
        x.setLeft(y.getRight());

        if(y.getRight() != null){
            y.getRight().setpParent(x);
        }
        y.setpParent(x.getpParent());

        if(x.getpParent() == null){
            root = y;
        }else if(x == x.getpParent().getRight()){
            x.getpParent().setRight(y);
        }else{
            x.getpParent().setLeft(y);
        }

        y.setRight(x);
        x.setpParent(y);
    }


    //------------------------------------------------------------------------------------------------------------------
    //Search method cost log n
    public void search(int i){
        Node curr = root;

        while(curr != null){
            if(curr.getKey() == i ){
                System.out.println("The key (" + i +") was found and the value associated to it, is : "+curr.getValue());
                break;
            }else if(curr.getKey() < i){
                curr = curr.getRight();
            }else{
                curr = curr.getLeft();
            }
        }

    }


    //------------------------------------------------------------------------------------------------------------------
    //finding the minimum value node in the tree in constant time
    //this can be made possible by augmenting the tree.
    //maintaining a min pointer that will keep track of the smallest key.
    public void findMinimum(){
        System.out.println("The smallest key in the tree is :"+ minimum.getKey() + " and the value associated to it, is: "+ minimum.getValue() + "\n");
    }


    //------------------------------------------------------------------------------------------------------------------
    //finding the maximum value node in the tree in constant time
    //this can be made possible by augmenting the tree.
    //maintaining a max pointer that will keep track of the biggest key.
    public void findMaximum(){
        System.out.println("The biggest key in the tree is :"+ maximum.getKey() + " and the value associated to it, is: "+ maximum.getValue()+ "\n");
    }

}
