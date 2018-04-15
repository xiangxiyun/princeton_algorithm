import java.util.Random;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Board {
    private int n;
    private final int[][] blocks;
    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks){
        //dimension
        n = blocks.length;
        this.blocks = new int[blocks.length][blocks.length];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                this.blocks[i][j] = blocks[i][j];
            }
        }
        
        
    }

    // board dimension n
    public int dimension(){
        return n;
    }

    // number of blocks out of place
    public int hamming(){
        int distance = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(this.blocks[i][j] != 0 && this.blocks[i][j] != i*n+j+1)
                    distance++;
            }
        }
        return distance;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan(){
        int distance = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(this.blocks[i][j] != 0){
                    int row = (this.blocks[i][j]-1) / n;
                    int col = (this.blocks[i][j]-1) % n;
                    distance += (Math.abs(i-row) + Math.abs(j-col));
                }
            }
        }
        return distance;
    }

    // is this board the goal board?
    public boolean isGoal(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                // StdOut.println(this.blocks[i][j]);
                if(this.blocks[i][j] != 0 && this.blocks[i][j] != i*n+j+1)
                    return false;
            }
        }
        return true;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin(){
        int[][] newBoard = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newBoard[i][j] = blocks[i][j];
            }
        }

        int rowSwap = 0;
        if (newBoard[0][0] == 0 || newBoard[0][1] == 0) {
            rowSwap = 1;
        }

        // exch(newBoard, rowSwap, 0, rowSwap, 1);

        int temp = newBoard[rowSwap][0];
        newBoard[rowSwap][0] = newBoard[rowSwap][1];
        newBoard[rowSwap][1] = temp;
        return new Board(newBoard);
    }
    
    // does this board equal y?
    public boolean equals(Object y){
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        return (this.toString().equals(that.toString()));
    }

    private int[][] copyBoard(){
        int[][] newBoard = new int[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                newBoard[i][j] = blocks[i][j];

        return newBoard;
    }

    private void exch(int[][] blocks, int row, int col, int nrow, int ncol){
        int aux = blocks[row][col];
        blocks[row][col] = blocks[nrow][ncol];
        blocks[nrow][ncol] = aux;
    }

    // all neighboring boards
    public Iterable<Board> neighbors(){
        Queue<Board> neigh = new Queue<>();
        int row= 0, col = 0;
        boolean flag = false;
        for(row = 0; row < n; row++){
            for(col = 0; col < n; col++){
                if(this.blocks[row][col] == 0){
                    flag = true;
                    break;
                }
            }
            if(flag == true)
                break;
        }

        int[][] newBoard;
        // exchange with upper element
        if(row > 0 && row <= n - 1){
            newBoard = copyBoard();
            exch(newBoard, row, col, row-1, col);
            neigh.enqueue(new Board(newBoard));
        }
        //exchange with lower element
        if(row >=0 && row < n-1){
            newBoard = copyBoard();
            exch(newBoard, row, col, row+1, col);
            neigh.enqueue(new Board(newBoard));
        }
        //exchange with left element
        if(col > 0 && col <= n-1){
            newBoard = copyBoard();
            exch(newBoard, row, col, row, col-1);
            neigh.enqueue(new Board(newBoard));
        }
        //exchange with right element
        if(col >= 0 && col < n-1){
            newBoard = copyBoard();
            exch(newBoard, row, col, row, col+1);
            neigh.enqueue(new Board(newBoard));
        }

        return neigh;
    }



    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            
            s.append("\n");
        }
        return s.toString();
    }

    // unit tests (not graded)
    public static void main(String[] args){
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        
        Board board = new Board(blocks);
        Board other = new Board(blocks);
  
        StdOut.println(board.equals(other)); // true
        StdOut.println(board.hamming());   //==> 7
        StdOut.println(board.manhattan());   //==> 16
        StdOut.println(board.equals(other));// ==> false
        StdOut.println(board.twin());
        StdOut.println(board.hamming());     //==> 7
        StdOut.println(board.equals(other));// ==> true
        StdOut.println(board.twin());

        // for(Board b: myBoard.neighbors())
        //     StdOut.print(b);
    } 
}