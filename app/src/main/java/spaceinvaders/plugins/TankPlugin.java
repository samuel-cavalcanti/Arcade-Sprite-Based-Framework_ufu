package spaceinvaders.plugins;

import org.jetbrains.annotations.NotNull;
import spaceinvaders.SpaceInvadersCommons;
import spriteframework.assetsLoader.AssetsLoader;
import spriteframework.gameBuilder.GameBuilder;
import spriteframework.gameBuilder.GamePlugin;
import spriteframework.gameResource.GameResources;
import spriteframework.gameResource.GameResourcesException;
import spriteframework.keyboard.KeyboardEvent;
import spriteframework.observer.Observer;
import spriteframework.sprite.Sprite;
import spriteframework.sprite.Vec2D;

import java.awt.event.KeyEvent;

public class TankPlugin implements GamePlugin {
    int dx = 0;
    final int maxVelocity = 2;

    public TankPlugin() {


    }

    @Override
    public void build(@NotNull GameBuilder builder) {
        Vec2D playerPos = new Vec2D(SpaceInvadersCommons.INIT_TANK_X, SpaceInvadersCommons.INIT_TANK_Y);
        Sprite playerSprite = new Sprite(AssetsLoader.loadImage(SpaceInvadersCommons.TANK_IMAGE), playerPos, true);


        builder
                .addResource(SpaceInvadersCommons.TANK, playerSprite)
                .addDrawable(playerSprite)
                .addKeyBoardObserver(new KeyboardObserver())
                .addGameCycleObserver(new GameCycleObserver());
    }


    class GameCycleObserver implements Observer<GameResources> {

        @Override
        public void update(@NotNull GameResources resources) {

            try {
                Sprite playerSprite = (Sprite) resources.get(SpaceInvadersCommons.TANK);
                Vec2D spriteSize = new Vec2D(playerSprite.image.getWidth(null), playerSprite.image.getHeight(null));
                Vec2D playerPos = playerSprite.position;
                playerPos.x += dx;

                int leftLimit = 2;
                int rightLimit = SpaceInvadersCommons.BOARD_WIDTH - 2 - spriteSize.x;

                if (playerPos.x < leftLimit) {
                    playerPos.x = leftLimit;
                    return;
                }

                if (playerPos.x >= rightLimit)
                    playerPos.x = rightLimit;

            } catch (GameResourcesException ignored) {

            }
        }
    }

    class KeyboardObserver implements Observer<KeyboardEvent> {

        @Override
        public void update(@NotNull KeyboardEvent e) {

            switch (e.action) {

                case Pressed -> {
                    switch (e.event.getKeyCode()) {
                        case KeyEvent.VK_LEFT -> dx = -maxVelocity;
                        case KeyEvent.VK_RIGHT -> dx = maxVelocity;
                    }
                }
                case Released -> {
                    switch (e.event.getKeyCode()) {
                        case KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT -> dx = 0;
                    }
                }
            }

        }
    }

}
