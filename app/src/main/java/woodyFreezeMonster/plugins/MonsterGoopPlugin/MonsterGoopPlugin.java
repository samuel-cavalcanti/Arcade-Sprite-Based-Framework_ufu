package woodyFreezeMonster.plugins.MonsterGoopPlugin;

import org.jetbrains.annotations.NotNull;
import spriteframework.gameBuilder.GameBuilder;
import spriteframework.gameBuilder.GamePlugin;
import woodyFreezeMonster.FreezeMonsterCommons;
import woodyFreezeMonster.plugins.MonsterGoopPlugin.systems.GoopMovementSystem;
import woodyFreezeMonster.plugins.MonsterGoopPlugin.systems.SpawnGoopSystem;

import java.util.LinkedList;

public class MonsterGoopPlugin implements GamePlugin {


    @Override
    public void build(@NotNull GameBuilder builder) {
        builder
                .addResource(FreezeMonsterCommons.MONSTER_GOOP, new LinkedList<Goop>())
                .addGameCycleObserver(new SpawnGoopSystem())
                .addGameCycleObserver(new GoopMovementSystem());
    }


}
