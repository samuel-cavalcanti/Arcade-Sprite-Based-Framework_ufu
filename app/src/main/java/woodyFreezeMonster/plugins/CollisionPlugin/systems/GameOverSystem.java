package woodyFreezeMonster.plugins.CollisionPlugin.systems;

import org.jetbrains.annotations.NotNull;
import spaceinvaders.drawables.EndGameBackground;
import spriteframework.Commons;
import spriteframework.board.Drawable;
import spriteframework.gameResource.GameResources;
import spriteframework.observer.Observer;
import spriteframework.sprite.Sprite;
import woodyFreezeMonster.FreezeMonsterCommons;
import woodyFreezeMonster.plugins.CollisionPlugin.SpriteCollision;
import woodyFreezeMonster.plugins.MonsterGoopPlugin.Goop;
import woodyFreezeMonster.plugins.MonsterPlugin.componets.Monster;

import java.util.List;

public class GameOverSystem implements Observer<GameResources> {

    boolean gameOver = false;

    @Override
    public void update(@NotNull GameResources resources) {
        Sprite woody = (Sprite) resources.get(FreezeMonsterCommons.WOODY);
        List<Monster> monsters = (List<Monster>) resources.get(FreezeMonsterCommons.MONSTERS);
        List<Goop> goops = (List<Goop>) resources.get(FreezeMonsterCommons.MONSTER_GOOP);
        List<Drawable> drawables = (List<Drawable>) resources.get(Commons.DRAWABLES);
        int boardWidth = (int) resources.get(Commons.BOARD_WIDTH);
        int boardHeight = (int) resources.get(Commons.BOARD_HEIGHT);


        if (!gameOver)
            gameOver = checkMonsterCollision(monsters, woody) || checkGoopCollision(goops, woody);

        boolean playerWin = monsters.isEmpty() && !gameOver;

        if (!gameOver && !playerWin)
            return;

        monsters.clear();
        goops.clear();
        drawables.clear();

        String message;

        if (gameOver) {
            message = "Game Over";
        } else {
            message = "Player Win";
        }

        drawables.add(new EndGameBackground(boardWidth, boardHeight, message));


    }

    private boolean checkGoopCollision(@NotNull List<Goop> goops, @NotNull Sprite woody) {
        for (Goop goop : goops) {
            if (SpriteCollision.checkSpriteCollision(goop.sprite, woody))
                return true;
        }
        return false;
    }

    private boolean checkMonsterCollision(@NotNull List<Monster> monsters, @NotNull Sprite woody) {
        for (Monster monster : monsters) {
            if (SpriteCollision.checkSpriteCollision(monster.sprite, woody)) {
                return true;
            }
        }

        return false;
    }
}
