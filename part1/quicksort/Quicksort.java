public class Quicksort{
	public static void main(String[] args){

		int[] a = new int[]{4,1,2,2,4,4,4,3,3,5};

		Quicksort myQs = new Quicksort();
		myQs.sort(a, 0, a.length-1);

		for(int i: a)
			System.out.print(i);
		System.out.println(" ");

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