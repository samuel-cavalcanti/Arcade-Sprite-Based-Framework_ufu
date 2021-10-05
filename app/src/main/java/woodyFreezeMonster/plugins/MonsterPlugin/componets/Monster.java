package woodyFreezeMonster.plugins.MonsterPlugin.componets;

import spriteframework.sprite.Sprite;
import spriteframework.sprite.Vec2D;

import java.awt.*;

public class Monster {
    public final Sprite sprite;
    public final Image freeze;
    public boolean isFreeze;
    public Vec2D speed;

    public Monster(Sprite sprite, Image freeze, Vec2D speed) {
        this.sprite = sprite;
        this.freeze = freeze;
        this.speed = speed;
        isFreeze = false;
    }
}
