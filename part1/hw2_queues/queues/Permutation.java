import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
	public static void main(String[] args){
		int k = Integer.parseInt(args[0]);
		RandomizedQueue<String> myRQ = new RandomizedQueue<>();
		// System.out.println(k);
		while (!StdIn.isEmpty()){
			String s = StdIn.readString();
			myRQ.enqueue(s);
		}

		while(k-- > 0)
			StdOut.println(myRQ.dequeue());
	}
}