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


    //constructor
    public RedBlackTree() {
        root = null;
        nil = null;
    }



    //normal insert in a bst
    public void insert(int i, String v){
        Node y = null;
        Node x = root;

        Node newNodeZ = new Node(i,v);

        //finding the correct position where the newNode should be
        //looping down the tree
        while (x != null){
            y=x;
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
        }else if (newNodeZ.getKey() < y.getKey()){
            y.setLeft(newNodeZ);
        }else{
            y.setRight(newNodeZ);
        }

        //the node is just created, so it doesnt have any left or right children
        //according to the laws of red black trees, a new node must be coloured red.
        newNodeZ.setLeft(null);
        newNodeZ.setRight(null);
        newNodeZ.setColor("RED");

        rbInsertFixup(newNodeZ);
    }


    private void rbInsertFixup(Node z){
        Node y;
        while(z.getpParent().getColor().equals("RED")){
            if(z.getpParent() == z.getpParent().getpParent().getLeft()){
               y = z.getpParent().getpParent().getRight();
            }

        }

    }




}
