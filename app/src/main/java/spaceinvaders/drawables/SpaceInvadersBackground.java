package spaceinvaders.drawables;

import org.jetbrains.annotations.NotNull;
import spaceinvaders.SpaceInvadersCommons;
import spriteframework.board.Drawable;

import java.awt.*;

public class SpaceInvadersBackground implements Drawable {
    @Override
    public void draw(@NotNull Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, SpaceInvadersCommons.BOARD_WIDTH, SpaceInvadersCommons.BOARD_HEIGHT);
        g.setColor(Color.green);
        g.drawLine(0, SpaceInvadersCommons.GROUND,
                SpaceInvadersCommons.BOARD_WIDTH, SpaceInvadersCommons.GROUND);
    }
}
