package woodyFreezeMonster.plugins.MonsterGoopPlugin.systems;

import org.jetbrains.annotations.NotNull;
import spriteframework.Commons;
import spriteframework.board.Drawable;
import spriteframework.gameResource.GameResources;
import spriteframework.observer.Observer;
import spriteframework.sprite.Vec2D;
import woodyFreezeMonster.FreezeMonsterCommons;
import woodyFreezeMonster.plugins.MonsterGoopPlugin.Goop;

import java.util.LinkedList;
import java.util.List;

public class GoopMovementSystem implements Observer<GameResources> {

    private int boardWidth;
    private int boardHeight;

    @Override
    public void update(@NotNull GameResources resources) {

        List<Goop> goops = (List<Goop>) resources.get(FreezeMonsterCommons.MONSTER_GOOP);
        List<Drawable> drawables = (List<Drawable>) resources.get(Commons.DRAWABLES);
        boardWidth = (int) resources.get(Commons.BOARD_WIDTH);
        boardHeight = (int) resources.get(Commons.BOARD_HEIGHT);
        List<Goop> goopsInBoard = new LinkedList<>();

        for (Goop goop : goops) {
            goop.sprite.position.x += goop.speed.x;
            goop.sprite.position.y += goop.speed.y;

            if (isOutOfBoard(goop.sprite.position)) {
                drawables.remove(goop.sprite);
            } else
                goopsInBoard.add(goop);

        }

        resources.replace(FreezeMonsterCommons.MONSTER_GOOP, goopsInBoard);

    }

    private boolean isOutOfBoard(@NotNull Vec2D position) {
        return position.x < 0 || position.x > boardWidth || position.y < 0 || position.y > boardHeight;
    }

}
