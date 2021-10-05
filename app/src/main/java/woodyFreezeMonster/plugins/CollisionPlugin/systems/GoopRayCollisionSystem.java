package woodyFreezeMonster.plugins.CollisionPlugin.systems;

import org.jetbrains.annotations.NotNull;
import spriteframework.Commons;
import spriteframework.board.Drawable;
import spriteframework.gameResource.GameResources;
import spriteframework.observer.Observer;
import woodyFreezeMonster.FreezeMonsterCommons;
import woodyFreezeMonster.plugins.CollisionPlugin.SpriteCollision;
import woodyFreezeMonster.plugins.FrozenRayPlugin.Ray;
import woodyFreezeMonster.plugins.MonsterGoopPlugin.Goop;

import java.util.LinkedList;
import java.util.List;

public class GoopRayCollisionSystem implements Observer<GameResources> {
    @Override
    public void update(@NotNull GameResources resources) {

        List<Goop> goops = (List<Goop>) resources.get(FreezeMonsterCommons.MONSTER_GOOP);
        List<Ray> rays = (List<Ray>) resources.get(FreezeMonsterCommons.RAYS);
        List<Drawable> drawables = (List<Drawable>) resources.get(Commons.DRAWABLES);

        if (rays.isEmpty() || goops.isEmpty())
            return;

        List<Goop> collidedGoops = new LinkedList<>();
        List<Ray> collidedRays = new LinkedList<>();

        for (Goop goop : goops) {
            for (Ray ray : rays) {
                if (SpriteCollision.checkSpriteCollision(goop.sprite, ray.sprite)) {
                    collidedGoops.add(goop);
                    collidedRays.add(ray);
                }
            }
        }

        for (Ray ray : collidedRays) {
            drawables.remove(ray.sprite);
            rays.remove(ray);
        }

        for (Goop goop : collidedGoops) {
            drawables.remove(goop.sprite);
            goops.remove(goop);
        }


    }
}
