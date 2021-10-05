package spaceinvaders.plugins;

import org.jetbrains.annotations.NotNull;
import spaceinvaders.SpaceInvadersCommons;
import spriteframework.assetsLoader.AssetsLoader;
import spriteframework.board.Drawable;
import spriteframework.gameBuilder.GameBuilder;
import spriteframework.gameBuilder.GamePlugin;
import spriteframework.gameResource.GameResources;
import spriteframework.observer.Observer;
import spriteframework.sprite.Sprite;
import spriteframework.sprite.Vec2D;

import java.awt.*;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class AlienPlugin implements GamePlugin {

    private final @NotNull LinkedList<Sprite> aliens;
    private final Image alienImage;
    private int direction = -1;

    public AlienPlugin() {
        alienImage = AssetsLoader.loadImage(SpaceInvadersCommons.ALIEN_IMAGE);
        aliens = createAliens();
    }

    private @NotNull LinkedList<Sprite> createAliens() {
        @NotNull LinkedList<Sprite> aliens = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                aliens.add(createAlienSprite(
                        SpaceInvadersCommons.ALIEN_INIT_X + 18 * j,
                        SpaceInvadersCommons.ALIEN_INIT_Y + 18 * i));
            }
        }
        return aliens;
    }

    private @NotNull Sprite createAlienSprite(int x, int y) {
        return new Sprite(alienImage, new Vec2D(x, y), true);
    }

    @Override
    public void build(@NotNull GameBuilder builder) {
        builder
                .addDrawables(aliens.stream().map(e -> (Drawable) e).collect(Collectors.toList()))
                .addResource(SpaceInvadersCommons.ALIENS, aliens)
                .addGameCycleObserver(new GameCycleObserver());
    }

    class GameCycleObserver implements Observer<GameResources> {

        @Override
        public void update(@NotNull GameResources resources) {

            for (Sprite alien : aliens) {
                if (alien.position.x >= SpaceInvadersCommons.BOARD_WIDTH - SpaceInvadersCommons.BORDER_RIGHT && direction != -1) {
                    direction = -1;
                    for (Sprite i1 : aliens) {
                        i1.position.y += SpaceInvadersCommons.GO_DOWN;
                    }
                }
                if (alien.position.x <= SpaceInvadersCommons.BORDER_LEFT && direction != 1) {
                    direction = 1;
                    for (Sprite i1 : aliens) {
                        i1.position.y += SpaceInvadersCommons.GO_DOWN;
                    }
                }


                alien.position.x += direction;
            }
        }
    }

}
