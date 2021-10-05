package spriteframework.sprite;

import org.jetbrains.annotations.NotNull;
import spriteframework.board.Drawable;

import java.awt.*;

public class Sprite implements Drawable {

    public boolean visible;
    public Image image;
    public Vec2D position;


    public Sprite(Image image, Vec2D p, boolean visible) {
        this.image = image;
        this.position = p;
        this.visible = visible;
    }


    @Override
    public void draw(@NotNull Graphics g) {
        if (this.visible) {
            g.drawImage(this.image, this.position.x, this.position.y, null);
        }

    }


}
