import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.*;

public class SAP {
   Digraph graph;
   // constructor takes a digraph (not necessarily a DAG)
   public SAP(Digraph G){
      graph = G;
   }

   // length of shortest ancestral path between v and w; -1 if no such path
   // public int length(int v, int w){}

   // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
   // public int ancestor(int v, int w){}

   // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
   // public int length(Iterable<Integer> v, Iterable<Integer> w){}

   // a common ancestor that participates in shortest ancestral path; -1 if no such path
   // public int ancestor(Iterable<Integer> v, Iterable<Integer> w){}

   // do unit testing of this class
   public static void main(String[] args){
      In in = new In(args[0]);
      Digraph G = new Digraph(in);
      SAP sap = new SAP(G);
      System.out.println("here");
      while (!StdIn.isEmpty()) {
         int v = StdIn.readInt();
         // int w = StdIn.readInt();
         Iterable<Integer> hold = sap.graph.adj(v);
         for(int a : hold)
            StdOut.printf("vertex = %d\n", a);

         // int length   = sap.length(v, w);
         // int ancestor = sap.ancestor(v, w);
         // StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
      }
   }
}