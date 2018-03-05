package hexagon;

import javafx.scene.shape.Polygon;

import java.awt.*;
import java.util.LinkedHashMap;

import static java.lang.Math.sqrt;

public class GameBoard {

    private int numberOfRings, numberOfTiles;
    private boolean isFlatTopped;
    private int radius;
    private int offsetX, offsetY;
    private LinkedHashMap<String, Hexagon> tiles;
    private Polygon[] gameBoard;

    public GameBoard(int numberOfRings, boolean isFlatTopped, int radius) {
        this.numberOfRings = numberOfRings;
        this.isFlatTopped = isFlatTopped;
        this.radius = radius;

        calculateOffsets();

        tiles = new LinkedHashMap<>();
        calculateNumberOfTiles();

        gameBoard = new Polygon[numberOfTiles];
        createGameBoard();
        drawHexShapedGameBoard();
    }

    private void calculateNumberOfTiles() {
        numberOfTiles = 1; // center Tile
        for (int i = 1; i < numberOfRings + 1; i++) {
            numberOfTiles += 6 * i;
        }
        System.out.println("The expected number of tiles is " + numberOfTiles);
    }

    private void calculateOffsets() {
        int innerRadius = (int) ((Math.round(sqrt(3) / 2)) * radius);
        if (isFlatTopped) {
            offsetX = (radius * 2) + (radius * numberOfRings * 2);
            offsetY = (innerRadius * 2) + (innerRadius * numberOfRings * 2);
        } else {
            offsetX = (innerRadius * 2) + (innerRadius * numberOfRings * 2);
            offsetY = (radius * 2) + (radius * numberOfRings * 2);
        }
    }

    private void createGameBoard() {
        tiles.put((0 + "," + 0), new Hexagon(0, 0, 0, radius, isFlatTopped, new Point(0, 0)));
        for (int i = 1; i < numberOfRings + 1; i++) {
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
        System.out.println("Total number of tiles on the gameBoard = " + tiles.size());
    }

    private void drawHexShapedGameBoard() {
        int i = 0;
        for (Hexagon hexagon : tiles.values()) {
            gameBoard[i] = new Polygon(hexagon.getCorners()[0].x + offsetX, hexagon.getCorners()[0].y + offsetY,
                    hexagon.getCorners()[1].x + offsetX, hexagon.getCorners()[1].y + offsetY,
                    hexagon.getCorners()[2].x + offsetX, hexagon.getCorners()[2].y + offsetY,
                    hexagon.getCorners()[3].x + offsetX, hexagon.getCorners()[3].y + offsetY,
                    hexagon.getCorners()[4].x + offsetX, hexagon.getCorners()[4].y + offsetY,
                    hexagon.getCorners()[5].x + offsetX, hexagon.getCorners()[5].y + offsetY);
            i++;
        }
    }

    public Polygon[] getGameBoard() {
        return gameBoard;
    }

    public Hexagon[] getGameBoardTiles() {
        Hexagon[] gameBoardTiles = new Hexagon[numberOfTiles];
        int i = 0;
        for (Hexagon hexagon : tiles.values()) {
            gameBoardTiles[i] = hexagon;
            i++;
        }
        return gameBoardTiles;
    }
}
