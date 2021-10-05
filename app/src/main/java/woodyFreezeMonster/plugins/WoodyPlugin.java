package woodyFreezeMonster.plugins;

import org.jetbrains.annotations.NotNull;
import spriteframework.assetsLoader.AssetsLoader;
import spriteframework.gameBuilder.GameBuilder;
import spriteframework.gameBuilder.GamePlugin;
import spriteframework.gameResource.GameResources;
import spriteframework.keyboard.KeyboardEvent;
import spriteframework.observer.Observer;
import spriteframework.sprite.Sprite;
import spriteframework.sprite.Vec2D;
import woodyFreezeMonster.FreezeMonsterCommons;
import woodyFreezeMonster.systems.LimitSpriteMovement;

import java.awt.event.KeyEvent;

public class WoodyPlugin implements GamePlugin {

    private final Vec2D maxSpeed = new Vec2D(5, 5);
    private final Vec2D speed = new Vec2D(0, 0);

    @Override
    public void build(@NotNull GameBuilder builder) {
        Vec2D playerPos = new Vec2D(FreezeMonsterCommons.INIT_WOODY_X, FreezeMonsterCommons.INIT_WOODY_Y);
        Sprite woodySprite = new Sprite(AssetsLoader.loadImage(FreezeMonsterCommons.PLAYER_IMAGE), playerPos, true);

        builder
                .addResource(FreezeMonsterCommons.WOODY, woodySprite)
                .addKeyBoardObserver(new KeyboardObserver())
                .addGameCycleObserver(new GameCycleObserver())
                .addDrawable(woodySprite);

    }


    class GameCycleObserver implements Observer<GameResources> {
        @Override
        public void update(@NotNull GameResources resources) {
            Sprite woody = (Sprite) resources.get(FreezeMonsterCommons.WOODY);
            LimitSpriteMovement limitSpriteMovement = (LimitSpriteMovement) resources.get(FreezeMonsterCommons.LIMITS);
            woody.position.x += speed.x;
            woody.position.y += speed.y;
            limitSpriteMovement.limitMovement(woody);
        }
    }

    class KeyboardObserver implements Observer<KeyboardEvent> {
        @Override
        public void update(@NotNull KeyboardEvent e) {
            switch (e.action) {
                case Pressed -> {
                    switch (e.event.getKeyCode()) {
                        case KeyEvent.VK_LEFT -> speed.x = -maxSpeed.x;
                        case KeyEvent.VK_RIGHT -> speed.x = maxSpeed.x;
                        case KeyEvent.VK_UP -> speed.y = -maxSpeed.y;
                        case KeyEvent.VK_DOWN -> speed.y = maxSpeed.y;
                    }
                }
                case Released -> {
                    switch (e.event.getKeyCode()) {
                        case KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT -> speed.x = 0;
                        case KeyEvent.VK_UP, KeyEvent.VK_DOWN -> speed.y = 0;
                    }
                }
            }
        }
    }

}
