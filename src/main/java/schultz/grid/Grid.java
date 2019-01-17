package schultz.grid;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import schultz.exception.ArgumentIsNotAGrid;
import schultz.exception.SizeOfInfluenceIsNotAPositiveInteger;
import schultz.point.Point;
import schultz.point.PositivePoint;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Grid {

    private int[][] internalArray;
    private int sizeOfPositiveInfluence;

    public Grid(int[][] inputGrid, int sizeOfPositiveInfluence){
        this.internalArray = inputGrid;
        this.sizeOfPositiveInfluence = sizeOfPositiveInfluence;
    }

    public List<PositivePoint> getPositivePoints() {
        List<PositivePoint> positivePoints = new ArrayList<>();

        for(int i = 0; i < this.internalArray[0].length; i++){
            for(int j = 0; j < this.internalArray.length; j++) {
                if(this.internalArray[j][i] > 0) {
                    positivePoints.add(new PositivePoint(i, j));
                }
            }
        }

        return positivePoints;
    }

    public List<Point> getPositivePointsOfInfluence() {
        return getPositivePoints().stream()
                .map(positivePoint -> positivePoint.getPointsOfInfluence(this.sizeOfPositiveInfluence))
                .flatMap(List::stream)
                .filter(point -> point.getX() >= 0)
                .filter(point -> point.getX() < this.internalArray[0].length)
                .filter(point -> point.getY() >= 0)
                .filter(point -> point.getY() < this.internalArray.length)
                .collect(Collectors.toList());
    }

    public Long getCountOfPositiveInfluence() {
        return getPositivePointsOfInfluence().stream()
                .distinct()
                .count();
    }

    public static Grid fromArguments(String inputGrid, String inputSizeOfInfluence) {
        ObjectMapper objectMapper = new ObjectMapper();

        int[][] grid;
        int sizeOfInfluence;

        grid = convertToGrid(inputGrid, objectMapper);
        verifySubArraysAreSameLength(grid);

        sizeOfInfluence = convertToSizeOfInfluence(inputSizeOfInfluence);
        verifySizeOfInfluenceIsPositive(sizeOfInfluence);

        return new Grid(grid, sizeOfInfluence);
    }

    private static void verifySizeOfInfluenceIsPositive(int sizeOfInfluence) {
        if(sizeOfInfluence < 0) {
            throw new SizeOfInfluenceIsNotAPositiveInteger("Please input a positive integer for your second argument");
        }
    }

    private static int convertToSizeOfInfluence(String inputSizeOfInfluence) {
        try{
            return Integer.parseInt(inputSizeOfInfluence);
        } catch (NumberFormatException e) {
            throw new SizeOfInfluenceIsNotAPositiveInteger("Please input a positive integer for your second argument");
        }
    }

    private static void verifySubArraysAreSameLength(int[][] grid) {
        long countOfDifferentArrayLengths = Arrays.stream(grid)
                .map(Array::getLength)
                .distinct()
                .count();

        if(countOfDifferentArrayLengths > 1) {
            throw new ArgumentIsNotAGrid("Please input a two-dimensional array as your first argument");
        }
    }

    private static int[][] convertToGrid(String inputGrid, ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(inputGrid, int[][].class);
        } catch (Exception e) {
            throw new ArgumentIsNotAGrid("Please input a two-dimensional array as your first argument");
        }
    }
}
