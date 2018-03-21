import java.util.NoSuchElementException;
import java.util.Iterator;
import java.lang.IllegalArgumentException;
import java.lang.UnsupportedOperationException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private int N;
	private Item[] array;

	// construct an empty randomized queue
	public RandomizedQueue(){
		N = 0;
		array = (Item[]) new Object[1];
	}

	// is the randomized queue empty?
	public boolean isEmpty(){
		return N == 0;
	}

	// return the number of items on the randomized queue
	public int size(){
		return N;
	}

	private void resize(int capacity){
		Item[] copy = (Item[]) new Object[capacity];
		for(int i = 0; i < N; i++)
			copy[i] = array[i];
		array = copy;
	}

	// add the item
	public void enqueue(Item item){
		if(item == null)
			throw new java.lang.IllegalArgumentException();

		if(N == array.length)
			resize(2*array.length);

		array[N++] = item;
	}

	// remove and return a random item
	public Item dequeue(){
		if(N == 0)
			throw new java.util.NoSuchElementException();

		int index = StdRandom.uniform(N);
		Item res = array[index];

		if(N-1 != index)
			array[index] = array[N-1];
		
		array[--N] = null;

		if(N > 0 && N == array.length/4)
			resize(array.length/2);
		
		return res;
	}

	// return a random item (but do not remove it)
	public Item sample(){
		if(N == 0)
			throw new java.util.NoSuchElementException();
		int index = StdRandom.uniform(N);
		Item res = array[index];
		return res;
	}

	// return an independent iterator over items in random order
	public Iterator<Item> iterator(){
		return new RQIterator();
	}

	private class RQIterator implements Iterator<Item>{
		int cur;
		int[] new_index;

		public RQIterator(){
			cur = 0;
			new_index = new int[N];
			for(int i = 0; i < N; i++)
				new_index[i] = i;
			StdRandom.shuffle(new_index);
		}

		public boolean hasNext(){return cur < N;}
		public Item next(){
			if(!hasNext())
				throw new java.util.NoSuchElementException();
			
			Item hold = array[new_index[cur]];
			cur++;
			return hold;
		}

		public void remove(){
			throw new java.lang.UnsupportedOperationException();
		}

	}

	// unit testing (optional)
	public static void main(String[] args){
		RandomizedQueue<Integer> myRQ = new RandomizedQueue<Integer>();
		myRQ.enqueue(1);
		myRQ.enqueue(2);
		myRQ.enqueue(3);
		myRQ.enqueue(4);
		// int i = myRQ.sample();
		// System.out.println(i);
		for(int i : myRQ)
			System.out.println(i);
	}
}
