package schultz.point;

import org.junit.Test;

import static org.junit.Assert.*;

public class PointTest {

    @Test
    public void constructorSetsXAndYProperly() {
        Point point = new Point(1, 2);
        assertEquals(1, point.getX());
        assertEquals(2, point.getY());
    }

}