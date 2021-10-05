package woodyFreezeMonster.plugins.MonsterPlugin.componets;

import spriteframework.sprite.Vec2D;

import java.util.Random;

public class MonsterRandomDirection {


    private final Vec2D[] possibleMovements = {
            new Vec2D(4,0),
            new Vec2D(0,4),
            new Vec2D(-4,0),
            new Vec2D(0,-4),
            new Vec2D(0, 2),
            new Vec2D(0, -2),
            new Vec2D(2, 0),
            new Vec2D(-2, 0),
            new Vec2D(2, 2),
            new Vec2D(-2, -2)
    };


    public Vec2D getRandomDirection() {
        return possibleMovements[new Random().nextInt(possibleMovements.length)];
    }

}
