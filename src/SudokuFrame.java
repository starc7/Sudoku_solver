import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuFrame extends JFrame{
    //Panel of sudoku
    private JPanel sudokuPanel;
    //Buttons to solve and reset sudoku panel
    private JButton solveButton, resetButton;
    private JLabel sudokuLabel;
    private int size=9;
    //Grid is the input of sudoku matrix and solution is the solution of sudoku
    private int[][] grid, solution;
    //Matrix of class Cell and given name to each cell as cell[i][j]
    private Cell[][] cell;
    SudokuFrame() {
        setTitle("Sudoku");
        setLayout(null);

        sudokuLabel=new JLabel("Sudoku Solver");
        sudokuLabel.setFont(new Font("OCR A Extended", Font.PLAIN, 35));
        sudokuLabel.setForeground(Color.RED);
        sudokuLabel.setBounds(115,00,300,80);
        sudokuPanel=new JPanel();
        sudokuPanel.setLayout(new GridLayout(9,9));
        //Initializing and cells in gridlayout
        grid=new int[size][size];
        cell=new Cell[size][size];
        for(int i=0;i<size;i++) {
            for(int j=0;j<size;j++) {
                cell[i][j]=new Cell();
                sudokuPanel.add(cell[i][j]);
            }
        }
        sudokuPanel.setBounds(55,70,385,400);
        //Solve button to solve Sudoku
        solveButton=new JButton("Solve");
        solveButton.setBounds(206,480,80,30);
        solveButton.setBackground(Color.ORANGE);
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                            if(!cell[i][j].getText().isEmpty()) {
                                int a=Integer.parseInt(cell[i][j].getText());
                                if(a<1 || a>9) {
                                    JOptionPane.showMessageDialog(new JFrame(),"Please enter value from 1 to 9 only",
                                            "Error",JOptionPane.ERROR_MESSAGE);
                                    System.exit(1);
                                }
                                grid[i][j]=a;
                            }
                        }
                    }
                    solution = new SudokuSolve().solveMethod(grid);
                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                            String str = String.valueOf(solution[i][j]);
                            cell[i][j].setText(str);
                            cell[i][j].setForeground(Color.BLUE);
                        }
                    }
                    //Refreshing frame by resizing it.
                    setBounds(430, 80, 499, 649);
                    setBounds(430, 80, 500, 600);
                } catch(Exception exp) {
                    JOptionPane.showMessageDialog(new JFrame(),exp,
                                "Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //Reset button
        resetButton=new JButton("Reset");
        resetButton.setBounds(206,520,80,30);
        resetButton.setBackground(Color.ORANGE);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<size;i++) {
                    for(int j=0;j<size;j++) {
                        solution[i][j]=0;
                        grid[i][j]=0;
                        cell[i][j].setText("");
                    }
                }
            }
        });

        //Adding to frame
        add(sudokuLabel);
        add(sudokuPanel);
        add(solveButton);
        add(resetButton);

        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setBounds(430,80,500,600);
    }

    public static void main(String[] args) {
        new SudokuFrame();
    }
}
