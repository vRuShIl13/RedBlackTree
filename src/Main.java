
public class Main {

    public static void main(String[] args) {
	// write your code here
        RedBlackTree sample = new RedBlackTree();

        sample.insert(44, "Vrushil");
        sample.insert(45, "Patel");
        sample.insert(5, "Comp");
        sample.insert(2, "3170");
        sample.insert(87, "Computer");
        sample.insert(75, "ground");
        sample.insert(100, "the 100s");


        sample.search(5);
        sample.findMaximum();
        sample.findMinimum();
    }

}
