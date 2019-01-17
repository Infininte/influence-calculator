package schultz.point;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static java.lang.StrictMath.abs;

@Data
public class PositivePoint extends Point {

    public PositivePoint(int x, int y) {
        super(x, y);
    }

    public List<Point> getPointsOfInfluence(int sizeOfInfluence) {
        List<Point> pointsOfInfluence = new ArrayList<>();

        if(sizeOfInfluence < 0) {
            return pointsOfInfluence;
        }

        for(int offsetX = -sizeOfInfluence; offsetX <= sizeOfInfluence; offsetX++) {
            for(int offsetY = -sizeOfInfluence; offsetY <= sizeOfInfluence; offsetY++) {
                if(abs(offsetX) + abs(offsetY) <= sizeOfInfluence) {
                    pointsOfInfluence.add(new Point(this.getX() + offsetX, this.getY() + offsetY));
                }
            }
        }

        return pointsOfInfluence;
    }
}