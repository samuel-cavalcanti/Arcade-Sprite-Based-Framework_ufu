package spaceinvaders;

import spaceinvaders.drawables.SpaceInvadersBackground;
import spaceinvaders.plugins.AlienPlugin;
import spaceinvaders.plugins.BombPlugin;
import spaceinvaders.plugins.MissilePlugin;
import spaceinvaders.plugins.TankPlugin;
import spaceinvaders.systems.CollisionSystem;
import spriteframework.gameBuilder.GameBuilder;

public class SpaceInvadersGame {


    public SpaceInvadersGame() {
        GameBuilder builder = new GameBuilder();

        builder.setGameTitle("Space Invaders")
                .setBoardSize(SpaceInvadersCommons.BOARD_WIDTH, SpaceInvadersCommons.BOARD_HEIGHT)
                .addDrawable(new SpaceInvadersBackground())
                .addPlugin(new TankPlugin())
                .addPlugin(new AlienPlugin())
                .addPlugin(new BombPlugin())
                .addPlugin(new MissilePlugin())
                .addGameCycleObserver(new CollisionSystem())
                .run();

    }


}
