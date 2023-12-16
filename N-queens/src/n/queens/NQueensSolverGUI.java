package n.queens;
import javax.swing.*;
import java.awt.*;
//import java.util.Scanner;

//jframe represent the main window 
public class NQueensSolverGUI extends JFrame {
    private final JButton startButton;
    private final JButton stopButton;
    private final chessboard chessboard;
    private NQueensSolver solver;
    private Thread solverThread;

    public NQueensSolverGUI(int boardSize) {
        // Initialize GUI components
        chessboard = new chessboard(boardSize);
        startButton = new JButton("Start");
        stopButton = new JButton("Stop");

        // Add components to the frame
        setLayout(new BorderLayout());
        add(chessboard, BorderLayout.CENTER);
        add(startButton, BorderLayout.SOUTH);
        add(stopButton, BorderLayout.NORTH);
        
        // Add action listeners
        startButton.addActionListener(e -> startSolver());
        stopButton.addActionListener(e -> stopSolver());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 550);
        // to center the frame into center
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void startSolver() {
         // to make the button Unenable 
        startButton.setEnabled(false);
        // to make the button Enable 
        stopButton.setEnabled(true);
        // if i ran the program before, i delete all the queens in the board
        chessboard.removeAllQueens();

        solver = new NQueensSolver(chessboard, chessboard.getBoardSize());
        solverThread = new Thread(solver);
        solverThread.start();
    }

    /*This method is called when the user clicks the "Stop" button.
    It interrupts the solver thread, enabling the "Start" button and disabling the "Stop" button.*/

    private void stopSolver() {
        //to stop the thread 
        solverThread.interrupt();
        // to make the button Enable 
        startButton.setEnabled(true);
         // to make the button Unenable 
        stopButton.setEnabled(false);
    }

    public static void main(String[] args) {
        String num1 = JOptionPane.showInputDialog("Enter Board Size");
     // Check if the input is null or empty
    if (num1 != null && !num1.isEmpty()) {
        try {
            int n = Integer.parseInt(num1);

            SwingUtilities.invokeLater(() -> new NQueensSolverGUI(n));
        } catch (NumberFormatException e) {
            // Handle the case where the input is not a valid integer
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer.");
        }
    } else {
        // Handle the case where the user clicked cancel or closed the dialog
        JOptionPane.showMessageDialog(null, "No input provided. Exiting.");
    }
    }
}