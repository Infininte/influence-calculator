package schultz.point;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Point {

    private int x;
    private int y;

    protected Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
