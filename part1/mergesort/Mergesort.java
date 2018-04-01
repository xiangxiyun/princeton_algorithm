import java.util.*;

public class Mergesort{
	private int[] aux;

	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		// int[] a = {3,2,4,1,5,7,6,8,10,9};
		int n = scan.nextInt();
		int[] a = new int[n];
		for(int i = 0; i < n; i++)
			a[i] = scan.nextInt();

		Mergesort mysort = new Mergesort();

		System.out.println("Original: ");
		for(int i: a)
			System.out.print(i +" ");
		System.out.println("\nSorted: ");
		mysort.sort(a);
		for(int i: a)
			System.out.print(i +" ");
		System.out.println("");
	}

	private void merge(int[] a, int aux[], int lo, int mid, int hi){
		if(lo >= hi)
			return;

		for(int i = lo; i <= hi; i++)
			aux[i] = a[i];

		int l = lo, r = mid+1;
		for(int i = lo; i <= hi; i++){
			if(l > mid) a[i] = aux[r++];
			else if(r > hi) a[i] = aux[l++];
			else if(a[l] < a[r]) a[i] = aux[l++];
			else a[i] = aux[r++];
		}

	}

	private void sort(int[] a, int[] aux, int lo, int hi){
		if(lo >= hi)
			return;
		int mid = lo+ (hi-lo)/2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid+1, hi);
		merge(a, aux, lo, mid, hi);
	}

	public void sort(int[] a){
		aux = new int[a.length];
		sort(a, aux, 0, a.length-1);
	}


}