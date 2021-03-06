package hexagon;

import java.awt.*;

import static java.lang.Math.sqrt;

public class Hexagon {

    private static int totalNumberOfHexagons = 0;
    private int cubeX, cubeY, cubeZ;
    private int outerRadius, innerRadius;
    private boolean isFlatTopped;
    private int width, height;
    private int horizontalDistance, verticalDistance;
    private final double halfSquareRootOfThree = sqrt(3) / 2;
    private Point center;
    private Point[] corners;

    public Hexagon(int cubeX, int cubeY, int cubeZ, int radius, boolean isFlatTopped, Point center) {
        totalNumberOfHexagons++;

        this.cubeX = cubeX;
        this.cubeY = cubeY;
        this.cubeZ = cubeZ;
        this.outerRadius = radius;
        this.isFlatTopped = isFlatTopped;
        this.center = center;
        this.corners = new Point[6];

        calculateDistances();
        calculateCenter();
        calculateCorners();
    }

    private void calculateDistances() {
        innerRadius = (int) Math.round(halfSquareRootOfThree * outerRadius);
        if (isFlatTopped) {
            width = outerRadius * 2;
            height = innerRadius * 2;
            horizontalDistance = outerRadius;
            verticalDistance = innerRadius;
        } else {
            width = innerRadius * 2;
            height = outerRadius * 2;
            horizontalDistance = innerRadius;
            verticalDistance = outerRadius;
        }
    }

    private Point calculateCorner(int cornerIndex) {
        int coordX = 0;
        int coordY = 0;
        if (isFlatTopped) {
            switch (cornerIndex) {
                case 1:
                    coordX = (int) (center.getX() + Math.round(outerRadius / 2));
                    coordY = (int) (center.getY() + innerRadius);
                    break;
                case 2:
                    coordX = (int) (center.getX() - Math.round(outerRadius / 2));
                    coordY = (int) (center.getY() + innerRadius);
                    break;
                case 3:
                    coordX = (int) (center.getX() - outerRadius);
                    coordY = (int) (center.getY());
                    break;
                case 4:
                    coordX = (int) (center.getX() - Math.round(outerRadius / 2));
                    coordY = (int) (center.getY() - innerRadius);
                    break;
                case 5:
                    coordX = (int) (center.getX() + Math.round(outerRadius / 2));
                    coordY = (int) (center.getY() - innerRadius);
                    break;
                case 6:
                    coordX = (int) (center.getX() + outerRadius);
                    coordY = (int) (center.getY());
                    break;
            }
        } else {
            switch (cornerIndex) {
                case 1:
                    coordX = (int) (center.getX() + innerRadius);
                    coordY = (int) (center.getY() + Math.round(outerRadius / 2));
                    break;
                case 2:
                    coordX = (int) (center.getX());
                    coordY = (int) (center.getY() + outerRadius);
                    break;
                case 3:
                    coordX = (int) (center.getX() - innerRadius);
                    coordY = (int) (center.getY() + Math.round(outerRadius / 2));
                    break;
                case 4:
                    coordX = (int) (center.getX() - innerRadius);
                    coordY = (int) (center.getY() - Math.round(outerRadius / 2));
                    break;
                case 5:
                    coordX = (int) (center.getX() - outerRadius);
                    coordY = (int) (center.getY());
                    break;
                case 6:
                    coordX = (int) (center.getX() + innerRadius);
                    coordY = (int) (center.getY());
                    break;
            }
        }
        return new Point(coordX, coordY);
    }

    private void calculateCorners() {
        for (int i = 0; i < 6; i++) {
            corners[i] = calculateCorner(i + 1);
        }
    }

    private void calculateCenter() {
        int x = center.x * (horizontalDistance + horizontalDistance / 2);
        if (center.x == 0) {
            verticalDistance += verticalDistance;
        }

        if (center.y == 0) {
            center.setLocation(center.x, center.x);
        }

        int y = center.y * verticalDistance;
        center.setLocation(x, y);
    }

    @Override
    public String toString() {
        return String.format("Hexagon at coords: (%2d, %2d, %2d) with center coords: (%2d, %2d)", cubeX, cubeY, cubeZ, center.x, center.y);
    }

    public Point getCenter() {
        return center;
    }

    public Point[] getCorners() {
        return corners;
    }

    public int getRadius() {
        return outerRadius;
    }

    public int getInnerRadius() {
        return innerRadius;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getHorizontalDistance() {
        return horizontalDistance;
    }

    public int getVerticalDistance() {
        return verticalDistance;
    }

    public boolean isFlatTopped() {
        return isFlatTopped;
    }

    public int[] getCubeCoordinates() {
        int[] cubeCoordinates = new int[3];
        cubeCoordinates[0] = cubeX;
        cubeCoordinates[1] = cubeY;
        cubeCoordinates[2] = cubeZ;
        return cubeCoordinates;
    }

    public int[] getAxialcoordinates() {
        int[] axialCoordinates = new int[2];
        axialCoordinates[0] = cubeX;
        axialCoordinates[1] = cubeY;
        return axialCoordinates;
    }

    public static int getTotalNumberOfHexagons() {
        return totalNumberOfHexagons;
    }
}
