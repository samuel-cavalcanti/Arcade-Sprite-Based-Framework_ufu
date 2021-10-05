package spriteframework.sprite;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestVec2D {


    @Test
    public void testVec2D(){

        Vec2D vec = new Vec2D(1,2);

        assertEquals(vec, vec.copy());
    }
}
