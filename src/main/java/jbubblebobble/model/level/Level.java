package jbubblebobble.model.level;

import jbubblebobble.model.entity.Entity;
import jbubblebobble.model.entity.bubble.Bubble;
import jbubblebobble.model.entity.characters.Enemy;
import jbubblebobble.model.entity.characters.Player;
import jbubblebobble.model.entity.powerup.PowerUp;
import jbubblebobble.model.entity.powerup.PowerUpFactory;
import utility.Config;

import java.util.*;
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;

import static jbubblebobble.model.entity.powerup.PowerUpFactory.createPowerUp;

/**
 * Level class is responsible for the level in the game
 * it contains the entities and walls collection
 * and comunicates with the view through the observer pattern
 *
 */
public class Level extends Observable implements Observer {
    private static int levelNumber;
    private EntityCollection entities;
    private WallCollection wallsCollection;
    private boolean bubbleSpeedPowerUp;
    private int extraLife;
    private Player player;

    /**
     * Instantiates a new Level.
     *
     * @param entities the entities
     * @param walls    the walls
     */
    public Level(EntityCollection entities, WallCollection walls) {
        this.levelNumber++;
        this.entities = entities;
        this.wallsCollection = walls;
    }

    /**
     * Sets player.
     *
     * @param player the player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Gets level number.
     *
     * @return the level number
     */
    public int getLevelNumber() {
        return levelNumber;
    }

    /**
     * Gets walls collection.
     *
     * @return the walls collection
     */
    public WallCollection getWallsCollection() {
        return wallsCollection;
    }

    /**
     * Gets player.
     *
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets level.
     *
     * @return the level
     */
    public Level getLevel() {
        return this;
    }

    /**
     * Send a representation of the level with a map to the view
     *
     * @param levelMap the level map
     */
    public void updateToView(Map<String,List<List<String>>> levelMap) {
        setChanged();
        notifyObservers(levelMap);
    }

    /**
     * check if a power up can be spawned than collect the information of the wall and entities
     * and send it to the view
     */
    public void update(Observable o, Object arg) {
        powerUpSpawn();
        Map<String,List<List<String>>> levelMap = entities.getLevelMap();
        levelMap.put("Wall", wallsCollection.getInfo());
        updateToView(levelMap);

    }

    /**
     * Gets entities collection.
     *
     * @return the entities collection
     */
    public EntityCollection getEntitiesCollection() {
        return entities;
    }

    /**
     * Game over.
     */
    public void gameOver() {
    }

    /**
     * Sets bubble speed power up.
     *
     * @param bubbleSpeedPowerUp the bubble speed power up
     */
    public void setBubbleSpeedPowerUp(boolean bubbleSpeedPowerUp) {
        this.bubbleSpeedPowerUp = bubbleSpeedPowerUp;
    }

    /**
     * Is bubble speed power up boolean.
     *
     * @return the boolean
     */
    public boolean isBubbleSpeedPowerUp() {
        return bubbleSpeedPowerUp;
    }

    /**
     * This method is responsible for the spawn of the power ups
     */
    private void powerUpSpawn() {
        double[] spawnPoint = wallsCollection.getSpawnPoint();
        if (player.getJumpCount() > Config.YELLOW_GUM) {
            player.setJumpCount(0);
            entities.add(createPowerUp(spawnPoint[0], spawnPoint[1], this, PowerUp.PowerUpType.YELLOW_GUM));
        }
        if (player.getBubbleExploded() > Config.BLUE_GUM) {
            player.setBubbleExploded(0);
            entities.add(createPowerUp(spawnPoint[0], spawnPoint[1], this, PowerUp.PowerUpType.BLUE_GUM));
        }
        if (player.getBubbleBlown() > Config.PURPLE_GUM) {
            player.setBubbleBlown(0);
            entities.add(createPowerUp(spawnPoint[0], spawnPoint[1], this, PowerUp.PowerUpType.PURPLE_GUM));
        }
        if (player.getDistance() >= Config.DISTANCE_TRVELED) {
            player.setDistance(0);
            entities.add(createPowerUp(spawnPoint[0], spawnPoint[1], this, PowerUp.PowerUpType.SPEED_SHOES));
        }
        if (extraLife < Config.EXTRA_LIFE.length && player.getScore() >= Config.EXTRA_LIFE[extraLife]) {
            extraLife++;
            if ( extraLife < Config.EXTRA_LIFE.length && player.getScore() < Config.EXTRA_LIFE[extraLife])  {
                entities.add(createPowerUp(spawnPoint[0], spawnPoint[1], this, PowerUp.PowerUpType.EXTRA_LIFE));
            }
        }
        if (player.getYellowGumCount() >= Config.RED_RING) {
            player.setYellowGumCount(0);
            entities.add(createPowerUp(spawnPoint[0], spawnPoint[1], this, PowerUp.PowerUpType.RED_RING));
        }
        if (player.getBlueGumCount() >= Config.BLUE_RING) {
            player.setBlueGumCount(0);
            entities.add(createPowerUp(spawnPoint[0], spawnPoint[1], this, PowerUp.PowerUpType.BLUE_RING));
        }
        if (player.getPurpleGumCount() >= Config.PURPLE_RING) {
            player.setPurpleGumCount(0);
            entities.add(createPowerUp(spawnPoint[0], spawnPoint[1], this, PowerUp.PowerUpType.PURPLE_RING));
        }
        if (player.getKilledEnemies() >= Config.KILLED_ENEMY) {
            player.setKilledEnemies(0);
            entities.add(createPowerUp(spawnPoint[0], spawnPoint[1], this, PowerUp.PowerUpType.GLOWING_HEART));
        }
        if (casualEqualsNumber()) {
            entities.add(createPowerUp(spawnPoint[0], spawnPoint[1], this, PowerUp.PowerUpType.RED_CROSS));
        }
    }

    private boolean casualEqualsNumber(){
        Random random = new Random();
        int number = random.nextInt(100000);
        int number2 = random.nextInt(100000);
        return number == number2;

    }

    /**
     * Reset level.
     */
    public void resetLevel(){
        levelNumber = 0;
    }

    /**
     * Create level map map.
     *
     * @return the map
     */
    public Map<String,List<List<String>>> createLevelMap() {
        Map<String,List<List<String>>> levelMap = entities.getLevelMap();
        levelMap.put("Wall", wallsCollection.getInfo());
        return levelMap;
    }
}