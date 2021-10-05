package woodyFreezeMonster.plugins.MonsterPlugin.systems;

import org.jetbrains.annotations.NotNull;
import spriteframework.gameResource.GameResources;
import spriteframework.observer.Observer;
import woodyFreezeMonster.FreezeMonsterCommons;
import woodyFreezeMonster.plugins.MonsterPlugin.componets.Monster;
import woodyFreezeMonster.plugins.MonsterPlugin.componets.MonsterRandomDirection;
import woodyFreezeMonster.systems.LimitSpriteMovement;

import java.util.List;
import java.util.Random;

public class MonsterMovementSystem implements Observer<GameResources> {

    final MonsterRandomDirection randomDirection = new MonsterRandomDirection();

    @Override
    public void update(@NotNull GameResources resources) {
        List<Monster> monsters = (List<Monster>) resources.get(FreezeMonsterCommons.MONSTERS);
        LimitSpriteMovement limitSpriteMovement = (LimitSpriteMovement) resources.get(FreezeMonsterCommons.LIMITS);

        for (Monster monster : monsters) {

            monster.sprite.position.x += monster.speed.x;
            monster.sprite.position.y += monster.speed.y;
            limitSpriteMovement.limitMovement(monster.sprite);


            if (new Random().nextInt(10) == 5) {
                monster.speed = randomDirection.getRandomDirection();
            }

        }

    }
}
