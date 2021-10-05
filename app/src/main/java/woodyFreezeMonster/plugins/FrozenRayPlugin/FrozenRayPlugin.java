package woodyFreezeMonster.plugins.FrozenRayPlugin;

import org.jetbrains.annotations.NotNull;
import spriteframework.Commons;
import spriteframework.assetsLoader.AssetsLoader;
import spriteframework.board.Drawable;
import spriteframework.gameBuilder.GameBuilder;
import spriteframework.gameBuilder.GamePlugin;
import spriteframework.gameResource.GameResources;
import spriteframework.keyboard.KeyboardAction;
import spriteframework.keyboard.KeyboardEvent;
import spriteframework.observer.Observer;
import spriteframework.sprite.Sprite;
import spriteframework.sprite.Vec2D;
import woodyFreezeMonster.FreezeMonsterCommons;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

public class FrozenRayPlugin implements GamePlugin {

    boolean spaceIsPressed = false;
    private final Image rayImage = AssetsLoader.loadImage(FreezeMonsterCommons.RAY_IMAGE);
    private Vec2D raySpeed = new Vec2D(0, -3);


    @Override
    public void build(@NotNull GameBuilder builder) {

        builder
                .addResource(FreezeMonsterCommons.RAYS, new LinkedList<Ray>())
                .addGameCycleObserver(new RayMovementSystem())
                .addGameCycleObserver(new GameCycleObserver())
                .addKeyBoardObserver(new KeyboardObserver());
    }

    public Vec2D calculateNewPos(@NotNull Vec2D playerPos, @NotNull Vec2D playerSize) {
        Vec2D rayPos = playerPos.copy();
        rayPos.x += playerSize.x / 2;
        rayPos.y += rayImage.getHeight(null) - 1;
        return rayPos;
    }


    private void spawnNewRay(@NotNull GameResources resources) {
        Sprite player = (Sprite) resources.get(FreezeMonsterCommons.WOODY);
        List<Drawable> drawables = (List<Drawable>) resources.get(Commons.DRAWABLES);
        List<Ray> rays = (List<Ray>) resources.get(FreezeMonsterCommons.RAYS);

        Vec2D rayPos = calculateNewPos(player.position, new Vec2D(player.image.getWidth(null), player.image.getHeight(null)));
        Sprite sprite = new Sprite(rayImage, rayPos, true);

        drawables.add(sprite);
        rays.add(new Ray(sprite, raySpeed.copy()));

    }


    class GameCycleObserver implements Observer<GameResources> {

        @Override
        public void update(@NotNull GameResources resources) {

            if (spaceIsPressed) {
                spawnNewRay(resources);
                spaceIsPressed = false;
            }

        }
    }

    class KeyboardObserver implements Observer<KeyboardEvent> {

        @Override
        public void update(@NotNull KeyboardEvent e) {

            switch (e.action) {

                case Pressed -> {
                    switch (e.event.getKeyCode()) {
                        case KeyEvent.VK_LEFT -> raySpeed = new Vec2D(-3, 0);
                        case KeyEvent.VK_RIGHT -> raySpeed = new Vec2D(3, 0);
                        case KeyEvent.VK_UP -> raySpeed = new Vec2D(0, -3);
                        case KeyEvent.VK_DOWN -> raySpeed = new Vec2D(0, 3);
                    }
                }
                case Released -> {
                }
            }

            spaceIsPressed = e.action == KeyboardAction.Pressed && e.event.getKeyCode() == KeyEvent.VK_SPACE;

        }
    }
}
