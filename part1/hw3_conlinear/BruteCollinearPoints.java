import java.lang.IllegalArgumentException;
import java.util.Arrays;
import java.util.ArrayList;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
	private ArrayList<LineSegment> segments = new ArrayList<>();

	// finds all line segments containing 4 points
	public BruteCollinearPoints(Point[] points){
		if(points == null)
			throw new java.lang.IllegalArgumentException();

		for(int i = 0; i < points.length; i++)
			if(points[i] == null)
				throw new java.lang.IllegalArgumentException();

		//deep copy
		Point[] copyPoints = deepCopy(points);

		Arrays.sort(copyPoints);
		for(int i = 1; i < copyPoints.length; i++)
			if(copyPoints[i].compareTo(copyPoints[i-1]) == 0)
				throw new java.lang.IllegalArgumentException();	

		int n = copyPoints.length;
		for(int i = 0; i < n-3; i++){
			for(int j = i+1; j < n-2; j++){
				double slope1 = copyPoints[i].slopeTo(copyPoints[j]);
				for(int k = j+1; k < n -1; k++){
					double slope2 = copyPoints[i].slopeTo(copyPoints[k]);
					for(int m = k+1; m < n; m++){
						double slope3 = copyPoints[i].slopeTo(copyPoints[m]);
						if(slope1 == slope2 && slope2 == slope3){
							//construct line segments
							segments.add(new LineSegment(copyPoints[i], copyPoints[m]));
						}
					}
				}
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
	// the line segments
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
	    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    StdDraw.show();
	}        
}