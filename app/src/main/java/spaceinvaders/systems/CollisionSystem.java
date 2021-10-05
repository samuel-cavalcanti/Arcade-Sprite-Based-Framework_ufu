package spaceinvaders.systems;

import org.jetbrains.annotations.NotNull;
import spaceinvaders.SpaceInvadersCommons;
import spaceinvaders.drawables.EndGameBackground;
import spriteframework.Commons;
import spriteframework.assetsLoader.AssetsLoader;
import spriteframework.board.Drawable;
import spriteframework.gameResource.GameResources;
import spriteframework.gameResource.GameResourcesException;
import spriteframework.observer.Observer;
import spriteframework.sprite.Sprite;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class CollisionSystem implements Observer<GameResources> {

    private final List<Sprite> spritesToRemove;
    private final List<Sprite> bombsToRemove;
    private final List<Sprite> aliensToRemove;
    private final List<Sprite> missilesToRemove;
    private final Image explosionImage = AssetsLoader.loadImage(SpaceInvadersCommons.EXPLOSION_IMAGE);

    public CollisionSystem() {
        spritesToRemove = new LinkedList<>();
        bombsToRemove = new LinkedList<>();
        aliensToRemove = new LinkedList<>();
        missilesToRemove = new LinkedList<>();
    }

    @Override
    public void update(@NotNull GameResources resources) {
        try {
            removeSprites(resources);
            playerBombCollision(resources);
            missileAlienCollision(resources);
            missileBombCollision(resources);


        } catch (GameResourcesException ignored) {
        }


    }

    private void removeSprites(@NotNull GameResources resources) {
        List<Sprite> bombs = (List<Sprite>) resources.get(SpaceInvadersCommons.BOMBS);
        List<Sprite> missiles = (List<Sprite>) resources.get(SpaceInvadersCommons.MISSILES);
        List<Sprite> aliens = (List<Sprite>) resources.get(SpaceInvadersCommons.ALIENS);
        List<Drawable> drawables = (List<Drawable>) resources.get(Commons.DRAWABLES);

        for (Sprite bomb : bombsToRemove)
            bombs.remove(bomb);

        for (Sprite missile : missilesToRemove)
            missiles.remove(missile);

        for (Sprite alien : aliensToRemove)
            aliens.remove(alien);

        for (Sprite sprite : spritesToRemove)
            drawables.remove(sprite);


        try {
            resources.get(SpaceInvadersCommons.TANK);
            if (aliens.isEmpty())
                drawables.add( new EndGameBackground(SpaceInvadersCommons.BOARD_WIDTH,SpaceInvadersCommons.BOARD_HEIGHT,"Player Win"));
        }
        catch (GameResourcesException exception ){
            drawables.add( new EndGameBackground(SpaceInvadersCommons.BOARD_WIDTH,SpaceInvadersCommons.BOARD_HEIGHT,"Game Over"));
        }


    }

    private void playerBombCollision(@NotNull GameResources resources) {
        List<Sprite> bombs = (List<Sprite>) resources.get(SpaceInvadersCommons.BOMBS);
        Sprite player = (Sprite) resources.get(SpaceInvadersCommons.TANK);

        for (Sprite bomb : bombs) {
            if (checkSpriteCollision(bomb, player)) {

                bombsToRemove.add(bomb);
                spritesToRemove.add(player);
                spritesToRemove.add(bomb);

                player.image = explosionImage;
                bomb.image = explosionImage;

                resources.remove(SpaceInvadersCommons.TANK);
            }
        }


    }


    private boolean checkSpriteCollision(@NotNull Sprite a, @NotNull Sprite b) {
        return a.position.x >= (b.position.x)
                && a.position.x <= (b.position.x + b.image.getWidth(null))
                && a.position.y >= (b.position.y)
                && a.position.y <= (b.position.y + b.image.getHeight(null));
    }

    private void missileAlienCollision(@NotNull GameResources resources) {
        List<Sprite> missiles = (List<Sprite>) resources.get(SpaceInvadersCommons.MISSILES);
        List<Sprite> aliens = (List<Sprite>) resources.get(SpaceInvadersCommons.ALIENS);


        for (Sprite alien : aliens) {
            for (Sprite missile : missiles) {
                if (checkSpriteCollision(missile, alien)) {
                    alien.image = explosionImage;
                    missile.image = explosionImage;

                    spritesToRemove.add(alien);
                    spritesToRemove.add(missile);
                    aliensToRemove.add(alien);
                    missilesToRemove.add(missile);
                }
            }
        }

    }

    private void missileBombCollision(@NotNull GameResources resources) {
        List<Sprite> missiles = (List<Sprite>) resources.get(SpaceInvadersCommons.MISSILES);
        List<Sprite> bombs = (List<Sprite>) resources.get(SpaceInvadersCommons.BOMBS);

        for (Sprite bomb : bombs) {
            for (Sprite missile : missiles) {
                if (checkSpriteCollision(missile, bomb)) {
                    bombsToRemove.add(bomb);
                    missilesToRemove.add(missile);
                    spritesToRemove.add(bomb);
                    spritesToRemove.add(missile);

                    bomb.image = explosionImage;
                    missile.image = explosionImage;
                }
            }
        }
    }
}
