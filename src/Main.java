
public class Main {

    public static void main(String[] args) {
	    // write your code here
        RedBlackTree sample = new RedBlackTree();

        sample.insert(1, "1");
        sample.insert(3, "3");
        sample.insert(5, "5");
        sample.insert(7, "7");
        sample.insert(9, "9");
        sample.insert(11,"11");

        sample.print();

        sample.findMinimum();
        sample.findMaximum();

        sample.delete(3);

        //rank
        //select

        sample.search(11);
        sample.print();


    }

}
