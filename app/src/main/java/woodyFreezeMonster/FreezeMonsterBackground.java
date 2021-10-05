package woodyFreezeMonster;


import org.jetbrains.annotations.NotNull;
import spriteframework.board.Drawable;

import java.awt.*;

public class FreezeMonsterBackground implements Drawable {
    @Override
    public void draw(@NotNull Graphics g) {
        g.setColor(new Color(90, 138, 117));
        g.fillRect(0, 0, FreezeMonsterCommons.BOARD_WIDTH, FreezeMonsterCommons.BOARD_HEIGHT);


    }
}
