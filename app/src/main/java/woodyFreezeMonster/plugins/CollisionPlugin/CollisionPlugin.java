package woodyFreezeMonster.plugins.CollisionPlugin;

import org.jetbrains.annotations.NotNull;
import spriteframework.gameBuilder.GameBuilder;
import spriteframework.gameBuilder.GamePlugin;
import woodyFreezeMonster.plugins.CollisionPlugin.systems.FreezeMonsterSystem;
import woodyFreezeMonster.plugins.CollisionPlugin.systems.GameOverSystem;
import woodyFreezeMonster.plugins.CollisionPlugin.systems.GoopRayCollisionSystem;

public class CollisionPlugin implements GamePlugin {
    @Override
    public void build(@NotNull GameBuilder builder) {

        builder
                .addGameCycleObserver(new GoopRayCollisionSystem())
                .addGameCycleObserver(new FreezeMonsterSystem())
                .addGameCycleObserver(new GameOverSystem());

    }

}
