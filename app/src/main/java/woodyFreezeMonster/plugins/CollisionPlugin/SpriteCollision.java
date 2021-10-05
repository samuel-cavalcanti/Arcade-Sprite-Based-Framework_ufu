package woodyFreezeMonster.plugins.CollisionPlugin;

import org.jetbrains.annotations.NotNull;
import spriteframework.sprite.Sprite;
import spriteframework.sprite.Vec2D;

public class SpriteCollision {


    public static boolean checkSpriteCollision(@NotNull Sprite a, @NotNull Sprite b) {
        Vec2D limitSpriteA = new Vec2D(
                a.position.x + a.image.getWidth(null),
                a.position.y + a.image.getHeight(null));

        Vec2D limitSpriteB = new Vec2D(
                b.position.x + b.image.getWidth(null),
                b.position.y + b.image.getHeight(null)
        );


        boolean ainSpaceBX = b.position.x <= a.position.x && limitSpriteB.x >= a.position.x;

        boolean limitAInSpaceBX = b.position.x <= limitSpriteA.x && limitSpriteB.x >= limitSpriteA.x;

        boolean ainSpaceBY = b.position.y <= a.position.y && limitSpriteB.y >= a.position.y;

        boolean limitAInSpaceBY = b.position.y <= limitSpriteA.y && limitSpriteB.y >= limitSpriteA.y;



        return (ainSpaceBX && ainSpaceBY) || (limitAInSpaceBX && limitAInSpaceBY);
    }
}
