package spriteframework.sprite;

import java.util.Objects;

public class Vec2D {

    public Vec2D(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public Vec2D copy() {
        return new Vec2D(x, y);
    }


    @Override
    public String toString() {
        return "Vec2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vec2D vec2D = (Vec2D) o;
        return x == vec2D.x && y == vec2D.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public int x;
    public int y;
}
