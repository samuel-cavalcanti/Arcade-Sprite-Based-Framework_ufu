package woodyFreezeMonster;

import spriteframework.Commons;
import spriteframework.gameBuilder.GameBuilder;
import woodyFreezeMonster.plugins.CollisionPlugin.CollisionPlugin;
import woodyFreezeMonster.plugins.FrozenRayPlugin.FrozenRayPlugin;
import woodyFreezeMonster.plugins.MonsterGoopPlugin.MonsterGoopPlugin;
import woodyFreezeMonster.plugins.MonsterPlugin.MonsterPlugin;
import woodyFreezeMonster.plugins.WoodyPlugin;
import woodyFreezeMonster.systems.LimitSpriteMovement;

public class WoodyFreezeMonster {

    public WoodyFreezeMonster() {

        final LimitSpriteMovement limitSpriteMovement = new LimitSpriteMovement(
                0,
                FreezeMonsterCommons.BOARD_HEIGHT - Commons.NATIVE_WINDOW_HEIGHT,
                0,
                FreezeMonsterCommons.BOARD_WIDTH);

        GameBuilder builder = new GameBuilder();

        builder
                .setGameTitle(FreezeMonsterCommons.TITLE)
                .setBoardSize(FreezeMonsterCommons.BOARD_WIDTH, FreezeMonsterCommons.BOARD_HEIGHT)
                .addDrawable(new FreezeMonsterBackground())
                .addResource(FreezeMonsterCommons.LIMITS, limitSpriteMovement)
                .addPlugin(new WoodyPlugin())
                .addPlugin(new FrozenRayPlugin())
                .addPlugin(new MonsterPlugin())
                .addPlugin(new MonsterGoopPlugin())
                .addPlugin(new CollisionPlugin())
                .run();

    }
}
