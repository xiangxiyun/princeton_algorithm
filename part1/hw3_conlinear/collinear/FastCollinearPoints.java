import java.lang.IllegalArgumentException;
import java.util.Arrays;
import java.util.ArrayList;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
	private ArrayList<LineSegment> segments = new ArrayList<>();

	// finds all line segments containing 4 or more points
	public FastCollinearPoints(Point[] points){
		if(points == null)
			throw new java.lang.IllegalArgumentException();

		int n = points.length;
		for(int i = 0; i < n; i++)
			if(points[i] == null)
				throw new java.lang.IllegalArgumentException();
		
		Point[] copyPoints = deepCopy(points);
		Arrays.sort(copyPoints);
		for(int i = 1; i < n; i++)
			if(copyPoints[i].compareTo(copyPoints[i-1]) == 0)
				throw new java.lang.IllegalArgumentException();

		for(int i = 0; i < n; i++){
			Point p = points[i];
			Arrays.sort(copyPoints);
			Arrays.sort(copyPoints, p.slopeOrder());
			for(int f = 1, l = 2; l < n; l++){
				while(l<n &&(p.slopeTo(copyPoints[f]) == p.slopeTo(copyPoints[l]) ))
					l++;

				if(l-f >= 3 && p.compareTo(copyPoints[f]) < 0){
					// StdOut.print(p);
					// for(int k = f; k < l; k++){
					// 	StdOut.print(copyPoints[k]);
					// }
					// StdOut.println(" ");
					segments.add(new LineSegment(p, copyPoints[l-1]));
				}

				f = l;
			}
		}
	}

	private Point[] deepCopy(Point[] points){
		Point[] newPoints = new Point[points.length];
		for(int i = 0; i < points.length; i++){
			newPoints[i] = points[i];
		}
		return newPoints;
	}

	// the number of line segments
	public int numberOfSegments(){
		return segments.size();
	}

	// the number of line segments
	public LineSegment[] segments(){
		return segments.toArray(new LineSegment[segments.size()]);
	}

	public static void main(String[] args) {

	    // read the n points from a file
	    In in = new In(args[0]);
	    int n = in.readInt();
	    Point[] points = new Point[n];
	    for (int i = 0; i < n; i++) {
	        int x = in.readInt();
	        int y = in.readInt();
	        points[i] = new Point(x, y);
	    }

	    // draw the points
	    StdDraw.enableDoubleBuffering();
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    for (Point p : points) {
	        p.draw();
	    }
	    StdDraw.show();

	    // print and draw the line segments
	    FastCollinearPoints collinear = new FastCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    StdDraw.show();
	}
}