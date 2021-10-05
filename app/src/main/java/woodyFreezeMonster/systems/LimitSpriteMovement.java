package woodyFreezeMonster.systems;

import org.jetbrains.annotations.NotNull;
import spriteframework.sprite.Sprite;

public class LimitSpriteMovement {

    private final int topLimit;
    private final int bottomLimit;
    private final int leftLimit;
    private final int rightLimit;

    public LimitSpriteMovement(int topLimit, int bottomLimit, int leftLimit, int rightLimit) {
        this.topLimit = topLimit;
        this.bottomLimit = bottomLimit;
        this.leftLimit = leftLimit;
        this.rightLimit = rightLimit;
    }


    public void limitMovement(@NotNull Sprite sprite) {

        final int spriteHeight = sprite.image.getHeight(null);
        final int spriteWidth = sprite.image.getWidth(null);
        if (sprite.position.y < topLimit)
            sprite.position.y = topLimit;
        else if (sprite.position.y + spriteHeight > bottomLimit)
            sprite.position.y = bottomLimit - spriteHeight;

        if (sprite.position.x < leftLimit)
            sprite.position.x = leftLimit;
        else if (sprite.position.x + spriteWidth > rightLimit)
            sprite.position.x = rightLimit - spriteWidth;


    }
}
