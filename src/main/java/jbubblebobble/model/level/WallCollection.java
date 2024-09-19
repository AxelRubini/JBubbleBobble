package jbubblebobble.model.level;

import jbubblebobble.model.entity.Entity;
import utility.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * WallCollection class is a container for the walls collection in the game.
 * provides methods to add walls, get walls, get near walls and get spawn points.
 */
public class WallCollection {

    private final List<Wall> walls = new ArrayList<>();
    private final List<Wall>[][] wallMatrix = new List[Config.NUM_ROWS][Config.NUM_COLS];
    private final List<double[]> spawnPoints = new ArrayList<>();

    /**
     * Add.
     *
     * @param wall the wall
     */
    public void add(Wall wall) {
        walls.add(wall);
    }

    /**
     * Add wall matrix.
     *
     * @param row  the row
     * @param col  the col
     * @param wall the wall
     */
    public void addWallMatrix(int row, int col, Wall wall) {
        if (wallMatrix[row][col] == null) {
            wallMatrix[row][col] = new ArrayList<>();
        }
        wallMatrix[row][col].add(wall);
    }

    /**
     * Gets walls.
     *
     * @return the walls
     */
    public List<Wall> getWalls() {
        return walls;
    }

    /**
     * Add spawn point.
     *
     * @param x the x
     * @param y the y
     */
    public void addSpawnPoint(double x, double y) {
        spawnPoints.add(new double[]{x, y});
    }

    /**
     * Provide a list of available spawn points.
     *
     * @return the double [ ]
     */
    public double[] getSpawnPoint(){
        return spawnPoints.get(new Random().nextInt(spawnPoints.size()));
    }

    /**
     * Implemented using stream to provide a List filtered by the position of the entity.
     *
     * @param entity the entity
     * @return the near walls
     */
    public List<Wall> getNearWalls(Entity entity) {
        List<Wall> nearWalls = new ArrayList<>();
        int row = (int) entity.getY() / Config.TILE_SIZE;
        int col = (int) entity.getX() / Config.TILE_SIZE;
        for (int i = Math.max(0, row - 1); i < Math.min(row + 2, Config.NUM_ROWS); i++) {
            for (int j = Math.max(0, col - 1); j < Math.min(col + 2, Config.NUM_COLS); j++) {
                if (wallMatrix[i][j] != null) {
                    nearWalls.addAll(wallMatrix[i][j]);
                }
            }
        }
        return nearWalls;
    }

    /**
     * Gets info.
     *
     * @return the info
     */
    public List<List<String>> getInfo() {
        List<List<String>> info = new ArrayList<>();
        for (Wall wall : walls) {
            info.add(wall.getInfo());
        }
        return info;
    }
}
