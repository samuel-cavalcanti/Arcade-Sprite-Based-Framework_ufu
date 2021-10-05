package woodyFreezeMonster.plugins.FrozenRayPlugin;

import spriteframework.sprite.Sprite;
import spriteframework.sprite.Vec2D;

public class Ray {
    public final Sprite sprite;
    public final Vec2D speed;

    public Ray(Sprite sprite, Vec2D speed) {
        this.sprite = sprite;
        this.speed = speed;
    }
}
