package schultz;

import schultz.grid.Grid;

public class App 
{
    public static void main( String[] args )
    {
        Grid grid = Grid.fromArguments(args[0], args[1]);
        System.out.println(grid.getTotalPositiveInfluence());
        return;
    }
}
