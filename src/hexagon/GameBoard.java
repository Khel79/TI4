package hexagon;

import java.awt.*;
import java.util.LinkedHashMap;

public class GameBoard {

    private int numberOfRings;
    private int numberOfTiles;
    private boolean isFlatTopped;
    private int radius;
    private LinkedHashMap<String, Hexagon> tiles;

    public GameBoard(int numberOfRings, boolean isFlatTopped, int radius) {
        this.numberOfRings = numberOfRings;
        this.isFlatTopped = isFlatTopped;
        this.radius = radius;
        this.tiles = new LinkedHashMap<>();

        calculateNumberOfTiles();
        createGameBoard();
    }

    private void calculateNumberOfTiles() {
        numberOfTiles = 1; // center Tile
        for (int i = 1; i < numberOfRings; i++) {
            numberOfTiles += 6 * i;
        }
    }

    private void createGameBoard() {
        tiles.put((0 + "," + 0), new Hexagon(0, 0, 0, radius, isFlatTopped, new Point(0, 0)));
        for (int i = 1; i < numberOfRings; i++) {
            for (int x = 0; x < numberOfRings + 1; x++) {
                generateCoordinateY(x);
                generateCoordinateY(-x);
            }
        }
    }

    private void generateCoordinateY(int x) {
        for (int y = 0; y < numberOfRings + 1; y++) {
            if (Math.abs(x) + y != 0 && x + y < numberOfRings + 1 && tiles.get((x + "," + y)) == null) {
                tiles.put((x + "," + y), new Hexagon(x, y, -(x + y), radius, isFlatTopped, new Point(x, y)));
            }
        }
        for (int y = 0; y < numberOfRings + 1; y++) {
            if (Math.abs(x) + y != 0 && Math.abs(x - y) < numberOfRings + 1 && tiles.get((x + "," + -y)) == null) {
                tiles.put((x + "," + -y), new Hexagon(x, -y, -(x - y), radius, isFlatTopped, new Point(x, -y)));
            }
        }
    }

    public void printGameBoard() {
        for (Hexagon hexagon : tiles.values()) {
            System.out.println(hexagon.toString());
        }
        System.out.println("Total number of Hexagons created = " + Hexagon.getTotalNumberOfHexagons());
        System.out.println("Total number of tiles on the gameboard = " + tiles.size());
    }
}
