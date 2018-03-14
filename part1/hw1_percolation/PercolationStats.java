import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;



public class PercolationStats {
    /*
    * perform trials independent experiments on an n-by-n grid
    */

    private double[] open_fraction;
    private final int T;
    private final double tmean, tstddev;

    public PercolationStats(int n, int trials){
        if(n <= 0 || trials <= 0)
            throw new java.lang.IllegalArgumentException();

        open_fraction = new double[trials];
        T = trials;

        for(int t = 0; t < trials; t++){

            Percolation myPer = new Percolation(n);
            int[] tank = new int[n*n];
            int partition = n*n-1;
            for(int i = 0; i < tank.length; i++)
                tank[i] = i;
            

            while(!myPer.percolates() ){
                //randomly select a sites to open
                int index = StdRandom.uniform(partition+1);
                //System.out.println(partition);

                int row = tank[index]/n+1;
                int col = tank[index]%n+1;
                myPer.open(row,col);

                int hold = tank[partition];
                tank[partition] = tank[index];
                tank[index] = hold;
                partition--;

            }
            
            open_fraction[t] = (double) myPer.numberOfOpenSites()/(n*n);
        }

        tmean = StdStats.mean(open_fraction);
        tstddev = StdStats.stddev(open_fraction);
    }
    /*
    * sample mean of percolation threshold
    */
    public double mean(){
        return tmean;
    }
    /*
    * sample standard deviation of percolation threshold
    */                          
    public double stddev(){
        return tstddev;
        
    }
    /*
    * low  endpoint of 95% confidence interval
    */             
    public double confidenceLo(){
        return tmean - (1.96*tstddev/Math.sqrt(T));
    }
    /*
    * high  endpoint of 95% confidence interval
    */                
    public double confidenceHi(){
        return tmean + (1.96*tstddev/Math.sqrt(T));
    }

    // test client (described below)
    public static void main(String[] args){
    //     int n = Integer.parseInt(args[0]);
    //     int trials = Integer.parseInt(args[1]);
    //     PercolationStats test = new PercolationStats(n, trials);

    //     System.out.println(test.mean());
    //     System.out.println(test.stddev());
    //     System.out.println(test.confidenceLo());
    //     System.out.println(test.confidenceHi());
    } 
}