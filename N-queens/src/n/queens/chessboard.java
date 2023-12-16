package n.queens;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
//import java.awt.Dimension;


// jpanel organizning and grouping the compoents together
public class chessboard extends JPanel {
    private final int boardSize;
    //used to represent the position of the queens
    private final boolean[][] queens;

    public chessboard(int boardSize) {
        this.boardSize = boardSize;
        queens = new boolean[boardSize][boardSize];
    }
    
    //function to retrieve the board size and pass it to the NQueensSolver class when creating a new instance.
    public int getBoardSize() {
        return boardSize;
    }
    /*
    This method is used to place a queen on the chessboard at the specified row and col coordinates.
    It sets the corresponding element in the queens array to true to indicate the presence of a queen,
    and then calls the repaint() method to update the graphical representation of the chessboard.
    */
    public void placeQueen(int row, int col) {
        queens[row][col] = true;
        repaint();
    }
    
    // check if there is a queen in specific row and col 
    public boolean isQueenPlaced(int row, int col) {
    return queens[row][col];
    }
    
    //remove queen from specific row and col then call repaint to update graphical representation 
    public void removeQueen(int row, int col) {
        queens[row][col] = false;
        repaint();
    }
    
     //remove all queens from chessboard then call repaint to update graphical representation 
    public void removeAllQueens() {
    for (int i = 0; i < boardSize; i++) {
        for (int j = 0; j < boardSize; j++) {
            queens[i][j] = false;
        }
    }
    repaint();
}
  //responsible for painting the cmponents on the screen
    @Override
    protected void paintComponent(Graphics g) {
        /*Invokes the paintComponent method of the superclass (JPanel) using the super keyword.
        This ensures that the superclass's painting functionality is executed before we perform our custom painting.*/
        super.paintComponent(g);
        /*cellSize variable calculated by dividing the width of the component (the chessboard panel) by the boardSize*/
        int cellSize = getWidth() / boardSize;

        // Draw the chessboard
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                /*
                For each cell, it checks whether the sum of the row index i and the column index j is even or odd. 
                This condition (i + j) % 2 == 0 determines if the cell should be colored white or black. 
                If the condition is true, the color is set to Color.WHITE; otherwise, it is set to Color.BLACK.
                */
                if ((i + j) % 2 == 0) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.BLACK);
                }
                /*The g.fillRect() method is then called to draw a filled rectangle
                  representing the cell at position (j * cellSize, i * cellSize) with dimensions cellSize by cellSize.*/
                g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);

                // Draw blue queens
                if (queens[i][j]) {
                    g.setColor(Color.BLUE);
                    /*
                    The g.fillOval() method is then called to draw a filled
                    red oval representing the queen at the position (j * cellSize, i * cellSize) with dimensions cellSize by cellSize.
                    */
                    g.fillOval(j * cellSize, i * cellSize, cellSize, cellSize);
                }
            }
        }
    }
}

