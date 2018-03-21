import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdIn;

import java.util.*;

public class WordNet {

	// constructor takes the name of the two input files
	public WordNet(String synsets, String hypernyms){
		if(synsets == null || hypernyms == null)
			throw new java.lang.IllegalArgumentException();

		sfile = new In(synsets);
		while(sfile.hasNextLine()){
			String line = sfile.readLine();
			String[] words = line.split(",");
			int id = Integer.parseInt(word[0]);
			String[] synsets = word[1].split(" ");

		}

		hfile = new In(hypernyms);

	}

	// returns all WordNet nouns
	public Iterable<String> nouns(){

	}

	// is the word a WordNet noun?
	public boolean isNoun(String word){
		if(word == null)
			throw new java.lang.IllegalArgumentException();
	}

	// distance between nounA and nounB (defined below)
	public int distance(String nounA, String nounB){
		if(nounA == null || nounB == null || !isNoun(nounA) || !isNoun(nounB))
			throw new java.lang.IllegalArgumentException();
	}

	// a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
	// in a shortest ancestral path (defined below)
	public String sap(String nounA, String nounB){
		if(nounA == null || nounB == null || !isNoun(nounA) || !isNoun(nounB))
			throw new java.lang.IllegalArgumentException();
	}

	// do unit testing of this class
	public static void main(String[] args){

	}
}