package schultz.grid;

import org.junit.Test;
import schultz.exception.ArgumentIsNotAGrid;
import schultz.exception.SizeOfInfluenceIsNotAPositiveInteger;

import static org.junit.Assert.assertEquals;

public class GridTest {

    @Test
    public void getTotalPositiveInfluence_zeroSizeOfInfluence() {
        String inputGrid = "[[0, 0, 0]," +
                            "[0, 1, 0]]";
        Grid instance = Grid.fromArguments(inputGrid, "0");
        assertEquals(Long.valueOf(1), instance.getTotalPositiveInfluence());
    }

    @Test
    public void getTotalPositiveInfluences_many() {
        String inputGrid = "[[0, 1, 0]," +
                            "[1, 1, 0]]";
        Grid instance = Grid.fromArguments(inputGrid, "0");
        assertEquals(Long.valueOf(3), instance.getTotalPositiveInfluence());
    }

    @Test
    public void getTotalPositiveInfluence_highNumber() {
        String inputGrid = "[[0, 0, 0]," +
                            "[0, 15, 0]]";
        Grid instance = Grid.fromArguments(inputGrid, "0");
        assertEquals(Long.valueOf(1), instance.getTotalPositiveInfluence());
    }

    @Test
    public void getTotalPositiveInfluence_negative() {
        String inputGrid = "[[0, 0, 0]," +
                            "[0, -1, 0]]";
        Grid instance = Grid.fromArguments(inputGrid, "0");
        assertEquals(Long.valueOf(0), instance.getTotalPositiveInfluence());
    }

    @Test
    public void getTotalPositiveInfluence_negativeAndPositve() {
        String inputGrid = "[[0, 0, 0]," +
                            "[0, -1, 1]]";
        Grid instance = Grid.fromArguments(inputGrid, "0");
        assertEquals(Long.valueOf(1), instance.getTotalPositiveInfluence());
    }

    @Test
    public void getTotalPositiveInfluence_entireGridIsZeroes() {
        String inputGrid = "[[0, 0, 0]," +
                            "[0, 0, 0]]";
        Grid instance = Grid.fromArguments(inputGrid, "0");
        assertEquals(Long.valueOf(0), instance.getTotalPositiveInfluence());
    }

    @Test
    public void getTotalPositiveInfluence_entireGridIsPositive() {
        String inputGrid = "[[1, 1, 1]," +
                            "[1, 1, 1]]";
        Grid instance = Grid.fromArguments(inputGrid, "0");
        assertEquals(Long.valueOf(6), instance.getTotalPositiveInfluence());
    }

    @Test
    public void getTotalPositiveInfluence_inCenterWithOneSizeOfInfluence() {
        String inputGrid = "[[0, 0, 0]," +
                            "[0, 1, 0]," +
                            "[0, 0, 0]]";
        Grid instance = Grid.fromArguments(inputGrid, "1");
        assertEquals(Long.valueOf(5), instance.getTotalPositiveInfluence());
    }

    @Test
    public void getTotalPositiveInfluence_onBottomWithOneSizeOfInfluence() {
        String inputGrid = "[[0, 0, 0]," +
                            "[0, 0, 0]," +
                            "[0, 1, 0]]";
        Grid instance = Grid.fromArguments(inputGrid, "1");
        assertEquals(Long.valueOf(4), instance.getTotalPositiveInfluence());
    }

    @Test
    public void  getTotalPositiveInfluence_onLeftSideWithOneSizeOfInfluence() {
        String inputGrid = "[[0, 0, 0]," +
                            "[1, 0, 0]," +
                            "[0, 0, 0]]";
        Grid instance = Grid.fromArguments(inputGrid, "1");
        assertEquals(Long.valueOf(4), instance.getTotalPositiveInfluence());
    }

    @Test
    public void getTotalPositiveInfluence_onTopWithOneSizeOfInfluence() {
        String inputGrid = "[[0, 1, 0]," +
                            "[0, 0, 0]," +
                            "[0, 0, 0]]";
        Grid instance = Grid.fromArguments(inputGrid, "1");
        assertEquals(Long.valueOf(4), instance.getTotalPositiveInfluence());
    }

    @Test
    public void getTotalPositiveInfluence_onRightSideWithOneSizeOfInfluence() {
        String inputGrid = "[[0, 0, 0]," +
                            "[0, 0, 1]," +
                            "[0, 0, 0]]";
        Grid instance = Grid.fromArguments(inputGrid, "1");
        assertEquals(Long.valueOf(4), instance.getTotalPositiveInfluence());
    }

    @Test
    public void getTotalPositiveInfluence_inUpperRightCornerOneSizeOfInfluence() {
        String inputGrid = "[[1, 0, 0]," +
                            "[0, 0, 0]," +
                            "[0, 0, 0]]";
        Grid instance = Grid.fromArguments(inputGrid, "1");
        assertEquals(Long.valueOf(3), instance.getTotalPositiveInfluence());
    }

    @Test
    public void getTotalPositiveInfluence_doesNotDoubleCountOverlaps() {
        String inputGrid = "[[1, 0, 0]," +
                            "[0, 1, 0]," +
                            "[0, 0, 1]]";
        Grid instance = Grid.fromArguments(inputGrid, "1");
        assertEquals(Long.valueOf(7), instance.getTotalPositiveInfluence());
    }

    @Test
    public void getPointsOfInfluenceWithinBounds_entireGrid() {
        String inputGrid = "[[0, 0, 0]," +
                            "[0, 1, 0]," +
                            "[0, 0, 0]]";
        Grid instance = Grid.fromArguments(inputGrid, "2");
        assertEquals(Long.valueOf(9), instance.getTotalPositiveInfluence());
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