package schultz.point;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class PositivePointTest {

    PositivePoint instance;

    @Before
    public void setup() {
        instance = new PositivePoint(3, 3);
    }

    @Test
    public void getPointsOfInfluence_influenceOfZero() {
        List<Point> pointsOfInfluence = instance.getPointsOfInfluence(0);
        assertThat(pointsOfInfluence, contains(new Point(3, 3)));
    }

    @Test
    public void getPointsOfInfluence_influenceOfOne() {
        List<Point> pointsOfInfluence = instance.getPointsOfInfluence(1);
        assertThat(pointsOfInfluence, containsInAnyOrder(
                                    new Point(3, 2),
                new Point(2, 3),    new Point(3, 3), new Point(4, 3),
                                    new Point(3, 4)));
    }

    @Test
    public void getPointsOfInfluence_influenceOfTwo() {
        List<Point> pointsOfInfluence = instance.getPointsOfInfluence(2);
        assertThat(pointsOfInfluence, containsInAnyOrder(
                                                        new Point(3, 1),
                                    new Point(2, 2),    new Point(3, 2),    new Point(4, 2),
                new Point(1, 3),    new Point(2, 3),    new Point(3, 3),    new Point(4, 3),    new Point(5, 3),
                                    new Point(2, 4),    new Point(3, 4),    new Point(4, 4),
                                                        new Point(3, 5)));
    }

    @Test
    public void getPointsOfInfluence_negativeSizeOfInfluence() {
        List<Point> pointsOfInfluence = instance.getPointsOfInfluence(-1);
        assertTrue(pointsOfInfluence.isEmpty());
    }
}