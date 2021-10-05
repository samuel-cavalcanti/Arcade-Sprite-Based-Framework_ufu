package spaceinvaders.drawables;

import org.jetbrains.annotations.NotNull;
import spriteframework.board.Drawable;

import java.awt.*;

public class EndGameBackground implements Drawable {

    private final String message;
    private final int boardWidth;
    private final int boardHeight;

    public EndGameBackground(int boardWidth, int boardHeight, String message) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.message = message;
    }

    @Override
    public void draw(@NotNull Graphics g) {

        g.setColor(Color.black);
        g.fillRect(0, 0, boardWidth, boardHeight);

        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, boardWidth / 2 - 30, boardWidth - 100, 50);
        g.setColor(Color.white);
        g.drawRect(50, boardWidth / 2 - 30, boardWidth - 100, 50);

        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fontMetrics = g.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(message, (boardWidth - fontMetrics.stringWidth(message)) / 2,
                boardWidth / 2);

    }
}
