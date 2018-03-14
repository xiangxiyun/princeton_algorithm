import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	/*
	* create n-by-n grid, with all sites blocked
	*/
	private final int N;
	private int nofopensites;
	private boolean[] open_array;
	private WeightedQuickUnionUF sites;

	public Percolation(int n){
		if(n <= 0)
			throw new java.lang.IllegalArgumentException();
		N = n;
		nofopensites = 0;
		open_array = new boolean[n*n];
		sites = new WeightedQuickUnionUF(n*n+1);

	}	
	/*
	* open site (row, col) if it is not open already
	*/		
	public void open(int row, int col){
		if(row < 1 || row > N || col < 1 || col > N)
			throw new java.lang.IllegalArgumentException();

		int index = (row-1)*N+col-1;

		//open site
		if(open_array[index] == false){
			open_array[index] = true;
			nofopensites++;

			//links the site in question to its open neighbors
			if(row == 1)
				sites.union(N*N, index);

			if(row-1 > 0 && open_array[index-N] == true)
				sites.union(index, index-N);
			if(row+1 <= N && open_array[index+N] == true)
				sites.union(index, index+N);
			if(col-1 > 0 && open_array[index-1] == true)
				sites.union(index, index-1);
			if(col+1 <=N && open_array[index+1] == true)
				sites.union(index, index+1);
		}
		
	}
	/* 
	* is site (row, col) open?
	*/
	public boolean isOpen(int row, int col){
		if(row < 1 || row > N || col < 1 || col > N)
			throw new java.lang.IllegalArgumentException();
		return open_array[(row-1)*N+col-1] == true;
	}
	/*
	* is site (row, col) full?  
	*/
	public boolean isFull(int row, int col){
		if(row < 1 || row > N || col < 1 || col > N)
			throw new java.lang.IllegalArgumentException();
		return sites.connected(N*N, (row-1)*N+col-1);		
	}
	/*
	* number of open sites
	*/
	public int numberOfOpenSites(){
		return nofopensites;
	}

	/*
	* does the system percolate?
	*/
	public boolean percolates(){
		for(int i = N*(N-1); i < N*N; i++)
			if(sites.connected(N*N, i))
				return true;
		return false;
	}

	// public static void main(String[] args){
	// 	Percolation myPerc = new Percolation(3);
	// 	myPerc.open(1,1);
	// 	myPerc.open(1,2);
	// 	myPerc.open(2,1);
	// 	System.out.println(myPerc.numberOfOpenSites());

	// }

}






