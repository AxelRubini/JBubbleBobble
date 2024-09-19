package jbubblebobble.model.level;

import jbubblebobble.model.entity.characters.Ghost;
import jbubblebobble.model.entity.characters.Monster;
import utility.Config;
import jbubblebobble.model.entity.Entity;
import jbubblebobble.model.entity.characters.Player;
import jbubblebobble.model.entity.characters.ZenChan;
import jbubblebobble.model.entity.bubble.Bubble;
import jbubblebobble.model.level.Wall;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * LevelFactory class is responsible for creating the levels from the file.
 */
public class LevelFactory {


    /**
     * Create level from file level.
     *
     * @param filePath the file path
     * @return the level
     * @throws IOException the io exception
     */
    public static Level createLevelFromFile(String filePath) throws IOException {
        EntityCollection entities = new EntityCollection();
        WallCollection walls = new WallCollection();
        Level level = new Level(entities, walls);
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            for (int row = 0; row < Config.NUM_ROWS; row++) {
                String line = br.readLine();
                if (line == null) {
                    throw new IOException("File does not contain enough rows");
                }
                for (int col = 0; col < Config.NUM_COLS; col++) {
                    if (col >= line.length()) {
                        throw new IOException("File does not contain enough columns in row " + row);
                    }
                    char symbol = line.charAt(col);
                    switch (symbol) {
                        case 'P':
                            Player player = Player.getInstance(col * Config.TILE_SIZE, row * Config.TILE_SIZE,level);
                            player.setX(col * Config.TILE_SIZE);
                            player.setY(row * Config.TILE_SIZE);
                            player.setState(Player.PlayerState.IDLE_RIGHT);
                            player.setFacingRight(true);
                            player.setLevel(level);
                            entities.add(player);
                            break;
                        case 'Z':
                            entities.add(new ZenChan(col * Config.TILE_SIZE, row * Config.TILE_SIZE,level));
                            break;
                        case 'W':
                            Wall wall = new Wall(col * Config.TILE_SIZE, row * Config.TILE_SIZE,false,level.getLevelNumber());
                            walls.add(wall);
                            walls.addWallMatrix(row, col, wall);
                            break;
                        case 'w':
                            Wall platform = new Wall(col * Config.TILE_SIZE, row * Config.TILE_SIZE,true,level.getLevelNumber());
                            walls.add(platform);
                            walls.addSpawnPoint( col * Config.TILE_SIZE, row * (Config.TILE_SIZE -1) );
                            walls.addWallMatrix(row, col,platform);
                            break;
                        case 'M':
                            entities.add(new Monster(col * Config.TILE_SIZE, row * Config.TILE_SIZE,level));
                            break;

                        case 'G':
                            entities.add(new Ghost(col * Config.TILE_SIZE, row * Config.TILE_SIZE,level));
                            break;
                    }
                }
            }
        }
        level.setPlayer(entities.getPlayer());
        return level;// Modify the level number if necessary
    }
}