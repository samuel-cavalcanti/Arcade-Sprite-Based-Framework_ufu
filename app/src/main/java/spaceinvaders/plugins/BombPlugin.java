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
import spriteframework.observer.Observer;
import spriteframework.sprite.Sprite;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class BombPlugin implements GamePlugin {

    private final Image bombImage;


    public BombPlugin() {
        bombImage = AssetsLoader.loadImage(SpaceInvadersCommons.BOMB_IMAGE);

    }


    @Override
    public void build(@NotNull GameBuilder builder) {

        builder.addResource(SpaceInvadersCommons.BOMBS, new LinkedList<Sprite>())
                .addGameCycleObserver(new GameCycleObserver());
    }


    class GameCycleObserver implements Observer<GameResources> {

        @Override
        public void update(@NotNull GameResources resources) {
            try {
                updateBombPositions(resources);
                dropBomb(resources);
            }
            catch (GameResourcesException ignored){

            }

        }
    }

    private void dropBomb(@NotNull GameResources resources) {
        List<Sprite> bombs = (List<Sprite>) resources.get(SpaceInvadersCommons.BOMBS);
        List<Sprite> aliens = (List<Sprite>) resources.get(SpaceInvadersCommons.ALIENS);
        List<Drawable> drawables = (List<Drawable>) resources.get(Commons.DRAWABLES);
        Random generator = new Random();

        for (Sprite alien : aliens) {
            int randomInt = generator.nextInt(15);
            if (randomInt != SpaceInvadersCommons.CHANCE_TO_DROP_BOM || bombs.size() > aliens.size())
                continue;

            Sprite bomb = new Sprite(bombImage, alien.position.copy(), true);

            bombs.add(bomb);
            drawables.add(bomb);
        }
    }

    private void updateBombPositions(@NotNull GameResources resources) {

        try {
            List<Sprite> bombs = (List<Sprite>) resources.get(SpaceInvadersCommons.BOMBS);
            List<Drawable> drawables = (List<Drawable>) resources.get(Commons.DRAWABLES);
            List<Sprite> newBombs = new LinkedList<>();

            for (Sprite bomb : bombs) {
                bomb.position.y++;
                if (bomb.position.y >= SpaceInvadersCommons.GROUND - bomb.image.getHeight(null))
                    drawables.remove(bomb);
                else
                    newBombs.add(bomb);
            }

            resources.replace(SpaceInvadersCommons.BOMBS, newBombs);
        } catch (GameResourcesException ignored) {

        }


    }
}
