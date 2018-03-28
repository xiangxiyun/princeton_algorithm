import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.IllegalArgumentException;
import java.lang.UnsupportedOperationException;

public class Deque<Item> implements Iterable<Item> {
	private Node first, last;
	private int N;

	private class Node{
		Item item;
		Node next;
		Node pre;
	}

	// construct an empty deque
	public Deque(){
		last = first = new Node();
		N = 0;
	}                           
	// construct an empty deque
	public boolean isEmpty(){
		return N == 0;
	}
	// return the number of items on the deque      
	public int size(){
		return N;
	}
	// add the item to the front
	public void addFirst(Item item){
		if(item == null)
			throw new java.lang.IllegalArgumentException();
		Node node = new Node();
		node.item = item;
		node.next = first;
		node.pre = null;
		node.next.pre = node;
		first = node;
		N++;
	}

	// add the item to the end
	public void addLast(Item item){
		if(item == null)
			throw new java.lang.IllegalArgumentException();

		if(first == last){
			addFirst(item);
		}else{
			Node node = new Node();
			node.item = item;

			node.next = last;
			node.pre = last.pre;
			last.pre.next = node;
			last.pre = node;
			N++;
		}
		

	}

	// remove and return the item from the front
	public Item removeFirst(){
		if(this.isEmpty())
			throw new java.util.NoSuchElementException();
		Item hold = first.item;
		first = first.next;
		first.pre = null;
		N--;
		return hold;
	}

	// remove and return the item from the end
	public Item removeLast(){
		if(this.isEmpty())
			throw new java.util.NoSuchElementException();
		Node truel = last.pre;
		if(truel.pre == null){ // first node
			removeFirst();
		}else{
			last.pre = truel.pre;
			truel.pre.next = last;
			N--;
		}
		return truel.item;
	}
	// return an iterator over items in order from front to end
	public Iterator<Item> iterator(){
		return new DequeIterator();
	}

	private class DequeIterator implements Iterator<Item>{
		private Node current = first;
		public boolean hasNext() { return current.next != null; }
		public Item next(){
			if(!hasNext()){
				throw new java.util.NoSuchElementException();
			}
			Item item = current.item;
			current = current.next;
			return item;
		}

		public void remove(){
			throw new java.lang.UnsupportedOperationException();
		}

	}
	// unit testing (optional)
	public static void main(String[] args){
		Deque<Integer> myDeque = new Deque<Integer>();
		myDeque.addFirst(1);
		// System.out.println(myDeque.size());
		myDeque.addFirst(2);
		// System.out.println(myDeque.size());
		myDeque.addLast(3);
		// System.out.println(myDeque.size());
		myDeque.removeLast();
		myDeque.removeFirst();
		myDeque.removeFirst();
		myDeque.removeFirst();
		// System.out.println(myDeque.size());
		for(Integer i: myDeque)
			System.out.println(i);

	}
}