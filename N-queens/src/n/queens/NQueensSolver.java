/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package n.queens;

/**
 *
 * @author Dell
 */

class NQueensSolver implements Runnable {
    private final chessboard chessboard;
    private final int boardSize;
    private boolean stopFlag;

    // the constructor 
    public NQueensSolver(chessboard chessboard, int boardSize) {
        this.chessboard = chessboard;
        this.boardSize = boardSize;
        this.stopFlag = false;
    }
    
    /*
    The stopSolver method sets the stopFlag to true, indicating that the solver should stop its execution.
    */
    public void stopSolver() {
        stopFlag = true;
    }

     /*
    The isSafe method checks if it is safe to place a queen at the given row and col on the chessboard.
    It iterates over the rows above the current row and checks if there is a queen placed in the same column or diagonals. 
    If a queen is found, it returns false, indicating that it is not safe to place a queen at that position.
    If no conflicts are found, it returns true, indicating that it is safe to place a queen at the given position
    */
    private boolean isSafe(int row, int col) {
    for (int i = 0; i < row; i++) {
        /*
        This condition checks if there is a queen already placed in the same column
        as the current position (row - i - 1) but in a row above the current row.
        */
        if (chessboard.isQueenPlaced(row - i - 1, col))
            return false;
        
        /*
        This condition checks if there is a queen already placed in the diagonal (top-left to bottom-right)
        from the current position (row - i - 1, col - i - 1) but in a row above the current row.
        It also checks if the column (col - i - 1) is within the bounds of the chessboard.
        */
        if (col - i - 1 >= 0 && chessboard.isQueenPlaced(row - i - 1, col - i - 1))
            return false;
        
        /*
        This condition checks if there is a queen already placed in the diagonal (top-right to bottom-left) 
        from the current position (row - i - 1, col + i + 1) but in a row above the current row.
        */
        if (col + i + 1 < boardSize && chessboard.isQueenPlaced(row - i - 1, col + i + 1))
            return false;
    }
    return true;
}

    private boolean solveNQueens(int row) {
        //If row reaches the boardSize, it means all queens have been successfully placed, and it returns true.
        if (row == boardSize) {
            return true;
        }

        for (int col = 0; col < boardSize; col++) {
            if (stopFlag) {
                return false;
            }

            if (isSafe(row, col)) {
                //This line places a queen at the current position on the chessboard.
                chessboard.placeQueen(row, col);
                   
                //This line introduces a delay of 500 milliseconds. It is used for visualization purposes to slow down the solver's progress.
                try {
                    Thread.sleep(500); // Delay for visualization
                } catch (InterruptedException e) {
                  System.out.println("Game ended.");
                  return true;
                 }
                 /*
                This line makes a recursive call to solveNQueens for the next row (row + 1).
                If the recursive call returns true, it means all queens have been successfully placed, so it returns true to propagate the success.
                */
                 
                
                if (solveNQueens(row + 1)) {
                    return true;
                }
                 /*
                 This line removes the queen from the current position on the chessboard.
                 It is executed when the recursive call (for the next row) returns false, 
                 indicating that the current placement leads to conflicts later on.
                 */

                chessboard.removeQueen(row, col);
            }
        }
        return false;
    }
    

    @Override
    /*
    The run method is required to implement the Runnable interface.
    It simply starts the solving process by calling solveNQueens with the initial row as 0.
    */
    public void run() {
        solveNQueens(0);
    }
}