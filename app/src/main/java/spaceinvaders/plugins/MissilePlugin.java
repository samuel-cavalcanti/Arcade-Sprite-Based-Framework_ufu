package spaceinvaders.plugins;

import org.jetbrains.annotations.NotNull;
import spaceinvaders.SpaceInvadersCommons;
import spriteframework.Commons;
import spriteframework.assetsLoader.AssetsLoader;
import spriteframework.board.Drawable;
import spriteframework.gameBuilder.GameBuilder;
import spriteframework.gameBuilder.GamePlugin;
import spriteframework.gameResource.GameResources;
import spriteframework.gameResource.GameResourcesException;
import spriteframework.keyboard.KeyboardAction;
import spriteframework.keyboard.KeyboardEvent;
import spriteframework.observer.Observer;
import spriteframework.sprite.Sprite;
import spriteframework.sprite.Vec2D;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MissilePlugin implements GamePlugin {

    private boolean spaceIsPressed;
    private final Image missileImage;


    public MissilePlugin() {
        spaceIsPressed = false;
        missileImage = AssetsLoader.loadImage(SpaceInvadersCommons.MISSILE_IMAGE);
    }

    @Override
    public void build(@NotNull GameBuilder builder) {

        builder
                .addResource(SpaceInvadersCommons.MISSILES, new ArrayList<Sprite>())
                .addKeyBoardObserver(new KeyboardObserver())
                .addGameCycleObserver(new GameCycleObserver());
    }

    public Vec2D calculateNewPos(@NotNull Vec2D playerPos, @NotNull Vec2D playerSize) {
        Vec2D shotPos = playerPos.copy();
        shotPos.x += playerSize.x / 2;
        shotPos.y -= missileImage.getHeight(null) - 1;

        return shotPos;
    }

    public void addNewMissile(@NotNull GameResources resources) {
        Sprite player = (Sprite) resources.get(SpaceInvadersCommons.TANK);
        Vec2D shotPos = calculateNewPos(player.position, new Vec2D(player.image.getWidth(null), player.image.getHeight(null)));
        Sprite missile = new Sprite(missileImage, shotPos, true);

        List<Drawable> drawables = (List<Drawable>) resources.get(Commons.DRAWABLES);
        drawables.add(missile);

        List<Sprite> missiles = (List<Sprite>) resources.get(SpaceInvadersCommons.MISSILES);
        missiles.add(missile);
    }

    public void updateMissiles(@NotNull GameResources resources) {
        try {
            List<Sprite> oldMissiles = (List<Sprite>) resources.get(SpaceInvadersCommons.MISSILES);
            List<Drawable> drawables = (List<Drawable>) resources.get(Commons.DRAWABLES);
            LinkedList<Sprite> newMissiles = new LinkedList<>();
            for (Sprite missile : oldMissiles) {
                missile.position.y -= 4;
                if (missile.position.y < 0)
                    drawables.remove(missile);
                else
                    newMissiles.add(missile);
            }

            resources.replace(SpaceInvadersCommons.MISSILES, newMissiles);

        } catch (GameResourcesException ignored) {

        }

    }


    class GameCycleObserver implements Observer<GameResources> {

        @Override
        public void update(@NotNull GameResources resources) {

            try {
                updateMissiles(resources);

                if (spaceIsPressed) {
                    addNewMissile(resources);
                    spaceIsPressed = false;
                }
            } catch (GameResourcesException ignored) {

            }


        }
    }

    class KeyboardObserver implements Observer<KeyboardEvent> {

        @Override
        public void update(@NotNull KeyboardEvent e) {

            spaceIsPressed = e.action == KeyboardAction.Pressed && e.event.getKeyCode() == KeyEvent.VK_SPACE;

        }
    }
}
