package schultz.grid;

import org.junit.Test;
import schultz.exception.ArgumentIsNotAGrid;
import schultz.exception.SizeOfInfluenceIsNotAPositiveInteger;
import schultz.point.Point;
import schultz.point.PositivePoint;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GridTest {

    @Test
    public void constructor_setsGridAndInfluence() {
        int[][] inputGrid = {{0, 0, 0}, {0, 0, 0}};
        Grid instance = new Grid(inputGrid, 1);
        assertEquals(inputGrid, instance.getInternalArray());
        assertEquals(1, instance.getSizeOfPositiveInfluence());
    }

    @Test
    public void getPositivePoints() {
        int[][] inputGrid = {{0, 0, 0}, {0, 1, 0}};
        Grid instance = new Grid(inputGrid, 0);
        assertThat(instance.getPositivePoints(), contains(new PositivePoint(1, 1)));
    }

    @Test
    public void getPositivePoints_many() {
        int[][] inputGrid = {{0, 1, 0},
                            {1, 1, 0}};
        Grid instance = new Grid(inputGrid, 0);
        assertThat(instance.getPositivePoints(), containsInAnyOrder(
                                            new PositivePoint(1, 0),
                new PositivePoint(0, 1),    new PositivePoint(1, 1)));
    }

    @Test
    public void getPositivePoints_highNumber() {
        int[][] inputGrid = {{0, 0, 0}, {0, 15, 0}};
        Grid instance = new Grid(inputGrid, 0);
        assertThat(instance.getPositivePoints(), contains(new PositivePoint(1, 1)));
    }

    @Test
    public void getPositivePoints_negative() {
        int[][] inputGrid = {{0, 0, 0}, {0, -1, 0}};
        Grid instance = new Grid(inputGrid, 0);
        assertTrue(instance.getPositivePoints().isEmpty());
    }

    @Test
    public void getPositivePoints_negativeAndPositve() {
        int[][] inputGrid = {{0, 0, 0}, {0, -1, 1}};
        Grid instance = new Grid(inputGrid, 0);
        assertThat(instance.getPositivePoints(), contains(new PositivePoint(2, 1)));
    }

    @Test
    public void getPositivePoints_allZero() {
        int[][] inputGrid = {{0, 0, 0}, {0, 0, 0}};
        Grid instance = new Grid(inputGrid, 0);
        assertTrue(instance.getPositivePoints().isEmpty());
    }

    @Test
    public void getPositivePointsOfInfluence_inCenter() {
        int[][] inputGrid = {{0, 0, 0},
                            {0, 1, 0},
                            {0, 0, 0}};
        Grid instance = new Grid(inputGrid, 1);
        List<Point> pointsOfInfluence = instance.getPositivePointsOfInfluence();
        assertThat(pointsOfInfluence, containsInAnyOrder(
                                    new Point(1, 0),
                new Point(0, 1),    new Point(1, 1), new Point(2, 1),
                                    new Point(1, 2)));
    }

    @Test
    public void getPositivePointsOfInfluence_onBottom() {
        int[][] inputGrid = {{0, 0, 0},
                            {0, 0, 0},
                            {0, 1, 0}};
        Grid instance = new Grid(inputGrid, 1);
        List<Point> pointsOfInfluence = instance.getPositivePointsOfInfluence();
        assertThat(pointsOfInfluence, containsInAnyOrder(
                                    new Point(1, 1),
                new Point(0, 2),    new Point(1, 2), new Point(2, 2)));
    }

    @Test
    public void getPositivePointsOfInfluence_onLeftSide() {
        int[][] inputGrid = {{0, 0, 0},
                            {1, 0, 0},
                            {0, 0, 0}};
        Grid instance = new Grid(inputGrid, 1);
        List<Point> pointsOfInfluence = instance.getPositivePointsOfInfluence();
        assertThat(pointsOfInfluence, containsInAnyOrder(
                new Point(0, 0),
                new Point(0, 1),    new Point(1, 1),
                new Point(0, 2)));
    }

    @Test
    public void getPositivePointsOfInfluence_onTop() {
        int[][] inputGrid = {{0, 1, 0},
                            {0, 0, 0},
                            {0, 0, 0}};
        Grid instance = new Grid(inputGrid, 1);
        List<Point> pointsOfInfluence = instance.getPositivePointsOfInfluence();
        assertThat(pointsOfInfluence, containsInAnyOrder(
                new Point(0, 0),    new Point(1, 0), new Point(2, 0),
                                    new Point(1, 1)));
    }

    @Test
    public void getPointsOfInfluenceWithinBounds_onRightSide() {
        int[][] inputGrid = {{0, 0, 0},
                            {0, 0, 1},
                            {0, 0, 0}};
        Grid instance = new Grid(inputGrid, 1);
        List<Point> pointsOfInfluence = instance.getPositivePointsOfInfluence();
        assertThat(pointsOfInfluence, containsInAnyOrder(
                new Point(2, 0),
                new Point(1, 1),    new Point(2, 1),
                new Point(2, 2)));
    }

    @Test
    public void getCountOfPositiveInfluence_one() {
        int[][] inputGrid = {{}};
        Grid instance = spy(new Grid(inputGrid, 1));

        doReturn(Arrays.asList(new Point(0, 0))).when(instance).getPositivePointsOfInfluence();
        doCallRealMethod().when(instance).getCountOfPositiveInfluence();

        assertEquals(Long.valueOf(1), instance.getCountOfPositiveInfluence());
    }

    @Test
    public void getCountOfPositiveInfluence_two() {
        int[][] inputGrid = {{}};
        Grid instance = spy(new Grid(inputGrid, 1));

        doReturn(Arrays.asList(new Point(0, 0), new Point(1, 0))).when(instance).getPositivePointsOfInfluence();
        doCallRealMethod().when(instance).getCountOfPositiveInfluence();

        assertEquals(Long.valueOf(2), instance.getCountOfPositiveInfluence());
    }

    @Test
    public void getCountOfPositiveInfluence_ignoresDuplicates() {
        int[][] inputGrid = {{}};
        Grid instance = spy(new Grid(inputGrid, 1));

        doReturn(Arrays.asList(new Point(1, 0), new Point(1, 0))).when(instance).getPositivePointsOfInfluence();
        doCallRealMethod().when(instance).getCountOfPositiveInfluence();

        assertEquals(Long.valueOf(1), instance.getCountOfPositiveInfluence());
    }

    @Test
    public void getPointsOfInfluenceWithinBounds_entireGrid() {
        int[][] inputGrid = {{0, 0, 0},
                            {0, 1, 0},
                            {0, 0, 0}};
        Grid instance = new Grid(inputGrid, 2);
        assertEquals(Long.valueOf(9), instance.getCountOfPositiveInfluence());
    }

    @Test
    public void getPointsOfInfluenceWithinBounds_nothingPositive() {
        int[][] inputGrid = {{0, 0, 0},
                            {0, 0, 0},
                            {0, 0, 0}};
        Grid instance = new Grid(inputGrid, 2);
        assertEquals(Long.valueOf(0), instance.getCountOfPositiveInfluence());
    }

    @Test
    public void fromArguments() {
        Grid grid = Grid.fromArguments("[[1]]", "1");

        assertEquals(1, grid.getInternalArray().length);
        assertEquals(1, grid.getInternalArray()[0].length);
        assertEquals(1, grid.getInternalArray()[0][0]);
        assertEquals(1, grid.getSizeOfPositiveInfluence());
    }

    @Test
    public void fromArguments_acceptsZeroes() {
        Grid grid = Grid.fromArguments("[[1]]", "0");

        assertEquals(0, grid.getSizeOfPositiveInfluence());
    }

    @Test(expected = ArgumentIsNotAGrid.class)
    public void fromArguments_charactersInsteadOfGrid() {
        Grid.fromArguments("abcde", "1");
    }

    @Test(expected = ArgumentIsNotAGrid.class)
    public void fromArguments_singleDimensionalArrayForGrid() {
        Grid.fromArguments("[1]", "1");
    }

    @Test(expected = ArgumentIsNotAGrid.class)
    public void fromArguments_threeDimensionalArrayForGrid() {
        Grid.fromArguments("[[[1]]]", "1");
    }

    @Test(expected = ArgumentIsNotAGrid.class)
    public void fromArguments_lopsidedTwoDimensionalArray() {
        Grid.fromArguments("[[1, 0], [1, 0, 1], [1, 0, 0]]", "1");
    }

    @Test(expected = SizeOfInfluenceIsNotAPositiveInteger.class)
    public void fromArguments_PositiveInfluenceIsACharacter() {
        Grid.fromArguments("[[1]]", "a");
    }

    @Test(expected = SizeOfInfluenceIsNotAPositiveInteger.class)
    public void fromArguments_positiveInfluenceIsADouble() {
        Grid.fromArguments("[[1]]", "1.23");
    }

    @Test(expected = SizeOfInfluenceIsNotAPositiveInteger.class)
    public void fromArguments_positiveInfluenceIsANegativeInteger() {
        Grid.fromArguments("[[1]]", "-1");
    }
}