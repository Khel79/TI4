package hexagon;

public enum DirectionFlatTopped {
    NORTH(0,-1,1),
    NORTHEAST(1,-1,0),
    SOUTHEAST(1,0,-1),
    SOUTH(0,1,-1),
    SOUTHWEST(-1,1,0),
    NORTHWEST(-1,0,1);

    private int cubeX, cubeY, cubeZ;

    DirectionFlatTopped(int cubeX, int cubeY, int cubeZ) {
        assert (cubeX+cubeY+cubeZ == 0);
        this.cubeX = cubeX;
        this.cubeY = cubeY;
        this.cubeZ = cubeZ;
    }
}
