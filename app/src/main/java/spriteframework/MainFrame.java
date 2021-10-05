package spriteframework;

import spriteframework.board.Board;

import javax.swing.*;

public class MainFrame extends JFrame {


    public MainFrame(String title, Board board) {

        add(board);

        setTitle(title);
        setSize(board.getWidth(), board.getHeight());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }


}
