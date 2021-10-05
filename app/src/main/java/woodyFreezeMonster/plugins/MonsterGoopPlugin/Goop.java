package woodyFreezeMonster.plugins.MonsterGoopPlugin;

import spriteframework.sprite.Sprite;
import spriteframework.sprite.Vec2D;

public class Goop {
    public final Sprite sprite;
    public Vec2D speed;

    public Goop(Sprite sprite, Vec2D speed) {
        this.sprite = sprite;
        this.speed = speed;
    }
}
