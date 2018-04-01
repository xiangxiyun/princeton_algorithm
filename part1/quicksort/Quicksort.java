import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Quicksort{
	public static void main(String[] args) throws Exception {
		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader input = new BufferedReader(in);
		String s = input.readLine();
		String[] str = s.split(" ");

		// int[] a = new int[]{4,1,2,2,4,4,4,3,3,5};
		int[] a = new int[str.length];
		for(int i = 0; i < str.length; i++)
			a[i] = Integer.parseInt(str[i]);

		Quicksort myQs = new Quicksort();
		myQs.sort(a, 0, a.length-1);

		System.out.println("Sorted: ");
		for(int i: a)
			System.out.print(i + " ");
		System.out.println("");

	}

	private int partition(int[] a, int lo, int hi){
		int l = lo, r = hi+1;

		while(true){
			while(a[++l] < a[lo])
				if(l == hi) break;

			while(a[--r] > a[lo])
				if(r == lo) break;

			if(l >= r) break;

			exchange(a, l, r);
		}

		//change pivot and r
		exchange(a, lo, r);

		return r;
	}

	private void sort(int[] a, int lo, int hi){
		if(hi <= lo) return;
		int mid = partition(a, lo, hi);
		sort(a, lo, mid-1);
		sort(a, mid+1, hi);
	}

	private void exchange(int[] a, int i, int j){
		int hold = a[i];
		a[i] = a[j];
		a[j] = hold;
	}
}