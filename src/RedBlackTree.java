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

        nil = new Node();
        nil.setLeft(null);
        nil.setRight(null);
        nil.setColor("BLACK");
        root = nil;

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
        while (x != nil){
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
        newNodeZ.setLeft(nil);
        newNodeZ.setRight(nil);
        newNodeZ.setColor("RED");

        rbInsertFixup(newNodeZ);
        System.out.println("value = "+ newNodeZ.getValue());
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
                    }
                    z.getpParent().setColor("BLACK");
                    z.getpParent().getpParent().setColor("RED");
                    rightRotate(z.getpParent().getpParent());

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
                        }
                        z.getpParent().setColor("BLACK");
                        z.getpParent().getpParent().setColor("RED");
                        leftRotate(z.getpParent().getpParent());

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

        if(y.getRight() != nil){
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
        System.out.println("\nThe smallest key in the tree is :"+ minimum.getKey() + " and the value associated to it, is: "+ minimum.getValue() + "\n");
    }


    //------------------------------------------------------------------------------------------------------------------
    //finding the maximum value node in the tree in constant time
    //this can be made possible by augmenting the tree.
    //maintaining a max pointer that will keep track of the biggest key.
    public void findMaximum(){
        System.out.println("The biggest key in the tree is :"+ maximum.getKey() + " and the value associated to it, is: "+ maximum.getValue()+ "\n");
    }

    //------------------------------------------------------------------------------------------------------------------

    //THis method replaces the sub tree rooted at U with the subtree rooted at V
    public void rbTransplant(Node u, Node v){
        if(u.getpParent()==null){
            root = v;
        }else if(u == u.getpParent().getLeft()){
            u.getpParent().setLeft(v);
        }else{
            u.getpParent().setRight(v);
        }
        System.out.println("how many times");

        v.setpParent(u.getpParent());
    }
    //------------------------------------------------------------------------------------------------------------------
    //method that is used to delete a node normally from the bst
    public void delete(int key){
        Node tobeDeleted = null;
        Node curr = root;
        while(curr != nil){
            if(curr.getKey() == key){
                tobeDeleted = curr;
            }
            if(curr.getKey()<= key){
                curr = curr.getRight();
            }else{
                curr = curr.getLeft();
            }
        }

        if(tobeDeleted == null){
            System.out.println("key not found in the tree.");
            return;
        }
        //node associated with the key is found and is sent to the rb-delete method to make sure the properties RBT are maintained.
        rbDelete(tobeDeleted);

    }

    //------------------------------------------------------------------------------------------------------------------
    public void rbDelete(Node z){
        Node x;
        Node y = z ;
        String yColor = y.getColor() ;

        if(z.getLeft() == nil){
            x = z.getRight();
            rbTransplant(z,z.getRight());
        }else if(z.getRight()==nil){
            x= z.getLeft();
            rbTransplant(z,z.getLeft());
        }else{
            y = successor(z);
            System.out.println(y.getValue());
            yColor = y.getColor();
            x = y.getRight();
            if(y.getpParent() == z){
                x.setpParent(y);
            }else{

                rbTransplant(y,y.getRight());
                y.setRight(z.getRight());
                y.getRight().setpParent(y);
            }
            rbTransplant(z,y);
            y.setLeft(z.getLeft());
            y.getLeft().setpParent(y);
            y.setColor(z.getColor());
        }
        if(yColor.equals("BLACK")){
            rbDeleteFixUp(x);
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    //used to restore the red black tree after deleting a node.
    private void rbDeleteFixUp(Node x) {
        Node w;
        while (x != root && x.getColor().equals("BLACK")){
            if(x == x.getpParent().getLeft()){
                w = x.getpParent().getRight();
                if(w.getColor().equals("RED")) {
                    w.setColor("BLACK");
                    x.getpParent().setColor("RED");
                    leftRotate(x.getpParent());
                    w = x.getpParent().getRight();
                }
                if(w.getLeft().getColor().equals("BLACK") && w.getRight().getColor().equals("BLACK")){
                    w.setColor("RED");
                    x = x.getpParent();
                }else{
                    if(w.getRight().getColor().equals("BLACK")){
                        w.getLeft().setColor("BLACK");
                        w.setColor("RED");
                        rightRotate(w);
                        w = x.getpParent().getRight();
                    }
                    w.setColor(x.getpParent().getColor());
                    x.getpParent().setColor("BLACK");
                    w.getRight().setColor("BLACK");
                    leftRotate(x.getpParent());
                    x = root;

                }
            }else{
                w = x.getpParent().getLeft();
                if(w.getColor().equals("RED")) {
                    w.setColor("BLACK");
                    x.getpParent().setColor("RED");
                    rightRotate(x.getpParent());
                    w = x.getpParent().getLeft();
                }
                if(w.getRight().getColor().equals("BLACK") && w.getLeft().getColor().equals("BLACK")){
                    w.setColor("RED");
                    x = x.getpParent();
                }else{
                    if(w.getLeft().getColor().equals("BLACK")){
                        w.getRight().setColor("BLACK");
                        w.setColor("RED");
                        leftRotate(w);
                        w = x.getpParent().getLeft();
                    }
                    w.setColor(x.getpParent().getColor());
                    x.getpParent().setColor("BLACK");
                    w.getLeft().setColor("BLACK");
                    rightRotate(x.getpParent());
                    x = root;
                }
            }

        }
        x.setColor("BLACK");
    }
    //------------------------------------------------------------------------------------------------------------------
    //This method is used to find the successor of a node, this is used when the node is being deleted
    public Node successor(Node origin) {
        if (origin.getRight() != null) {
            return minimumFromSuccessor(origin.getRight());
        }
        Node orgParent = origin.getpParent();
        while(orgParent!=null && origin == orgParent.getRight()){
            origin = orgParent;
            orgParent = orgParent.getpParent();
        }

        return orgParent;
    }
    //------------------------------------------------------------------------------------------------------------------
    //Finding the succesor of a node using a while loop.
    public Node minimumFromSuccessor(Node node) {
        while (node.getLeft() != nil) {
            node = node.getLeft();
        }
        return node;
    }
    //------------------------------------------------------------------------------------------------------------------
    //rank and select method
    //size is the rank of the node.
    // using the rank we can select a node.
    public void select (int i){
        Node curr = root;

    }

    //------------------------------------------------------------------------------------------------------------------
    // this method is used to print the values of all the nodes in the trees.
    public void print() {
        Node curr = root;
        System.out.print("\nContents of the tree: ");
        System.out.print("{ ");
        treeTraversal(curr);
        System.out.print("}\n");

    }

    //traversing the inorder.
    public void treeTraversal(Node curr){

        if(curr!=nil){
            treeTraversal(curr.getLeft());
            System.out.print(curr.getValue()+ " ");
            treeTraversal(curr.getRight());
        }


    }


    //This method is used to ensure that all the properties of a bst and red-black trees hold
    public void validate(){

    }



}
