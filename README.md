# influence-calculator
A simple app for calculating the area of influence of positive values in a two-dimensional grid.

# How to use
Build the app with `mvn clean package`

Run the app with `java -jar influence-calculator-1.0-SNAPSHOT.jar [[0,1,0],[1,0,0],[0,0,0]] 1`

- The first parameter is the input grid.  
-- _Note:_ ensure the grid does not contain spaces
- The second paramenter is the number steps the influence can make
-- The steps are non-diagonal

Running the command `java -jar influence-calculator-1.0-SNAPSHOT.jar [[0,1,0],[1,0,0],[0,0,0]] 1` will give you an answer of 6 because the influence of the positive points works out to:
```
[[X, X, X],
[X, X, 0],
[X, 0, 0]
```
