package spriteframework.board;


import spriteframework.Commons;
import spriteframework.gameResource.GameResources;
import spriteframework.gameResource.GameResourcesException;
import spriteframework.keyboard.Keyboard;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class Board extends JPanel {

    private final Dimension boardDimension;


    public Board(int width, int height) {
        addKeyListener(Keyboard.getInstance());
        setFocusable(true);
        boardDimension = new Dimension(width, height);
        setBackground(Color.black);


    }

    @Override
    public int getWidth() {
        return boardDimension.width;
    }

    @Override
    public int getHeight() {
        return boardDimension.height;
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        try {
            List<Drawable> drawables = (List<Drawable>) GameResources.getInstance().get(Commons.DRAWABLES);
            for (Drawable drawable : drawables)
                drawable.draw(g);

        } catch (GameResourcesException ignored) {
            System.err.println("Resource drawables not found to draw!");
        }


        Toolkit.getDefaultToolkit().sync();
    }


}
