package woodyFreezeMonster.plugins.FrozenRayPlugin;

import org.jetbrains.annotations.NotNull;
import spriteframework.Commons;
import spriteframework.board.Drawable;
import spriteframework.gameResource.GameResources;
import spriteframework.observer.Observer;
import spriteframework.sprite.Vec2D;
import woodyFreezeMonster.FreezeMonsterCommons;

import java.util.LinkedList;
import java.util.List;

public class RayMovementSystem implements Observer<GameResources> {
    @Override
    public void update(@NotNull GameResources e) {
        updateRays(e);
    }

    private void updateRays(@NotNull GameResources resources) {
        List<Ray> rays = (List<Ray>) resources.get(FreezeMonsterCommons.RAYS);

        List<Drawable> drawables = (List<Drawable>) resources.get(Commons.DRAWABLES);

        List<Ray> newRays = new LinkedList<>();

        for (Ray ray : rays) {
            ray.sprite.position.x += ray.speed.x;
            ray.sprite.position.y += ray.speed.y;
            if (isOutOfBoard(ray.sprite.position))
                drawables.remove(ray.sprite);
            else
                newRays.add(ray);
        }


        resources.replace(FreezeMonsterCommons.RAYS, newRays);

    }

    private boolean isOutOfBoard(@NotNull Vec2D position) {
        return position.x > FreezeMonsterCommons.BOARD_WIDTH || position.x < 0 || position.y < 0 || position.y > FreezeMonsterCommons.BOARD_HEIGHT;
    }

}
