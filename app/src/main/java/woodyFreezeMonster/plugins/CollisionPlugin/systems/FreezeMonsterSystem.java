package woodyFreezeMonster.plugins.CollisionPlugin.systems;

import org.jetbrains.annotations.NotNull;
import spriteframework.Commons;
import spriteframework.board.Drawable;
import spriteframework.gameResource.GameResources;
import spriteframework.observer.Observer;
import woodyFreezeMonster.FreezeMonsterCommons;
import woodyFreezeMonster.plugins.CollisionPlugin.SpriteCollision;
import woodyFreezeMonster.plugins.FrozenRayPlugin.Ray;
import woodyFreezeMonster.plugins.MonsterPlugin.componets.Monster;

import java.util.LinkedList;
import java.util.List;

public class FreezeMonsterSystem implements Observer<GameResources> {
    @Override
    public void update(@NotNull GameResources resources) {


        List<Drawable> drawables = (List<Drawable>) resources.get(Commons.DRAWABLES);
        List<Ray> rays = (List<Ray>) resources.get(FreezeMonsterCommons.RAYS);
        List<Monster> monsters = (List<Monster>) resources.get(FreezeMonsterCommons.MONSTERS);


        if (rays.isEmpty())
            return;

        List<Ray> collidedRays = new LinkedList<>();
        List<Monster> collidedMonsters = new LinkedList<>();

        for (Monster monster : monsters) {
            for (Ray ray : rays) {
                if (SpriteCollision.checkSpriteCollision(monster.sprite, ray.sprite)) {
                    monster.sprite.image = monster.freeze;
                    monster.isFreeze = true;
                    collidedRays.add(ray);
                    collidedMonsters.add(monster);
                }
            }
        }

        for (Ray ray : collidedRays) {
            rays.remove(ray);
            drawables.remove(ray.sprite);
        }

        for (Monster monster : collidedMonsters) {
            monsters.remove(monster);
        }


    }
}
