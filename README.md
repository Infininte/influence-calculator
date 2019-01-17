# influence-calculator
A simple app for calculating the area of influence of positive values in a two-dimensional grid.

# How to use
Build the app with `mvn clean package`

Run the app with `java -jar influence-calculator-1.0-SNAPSHOT.jar [[0,1,0],[1,0,0],[0,0,0]] 1`

- The first parameter is the input grid. _Note:_ ensure the grid does not contain spaces
- The second paramenter is the step size
