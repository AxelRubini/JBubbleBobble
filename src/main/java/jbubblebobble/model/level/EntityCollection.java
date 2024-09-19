package jbubblebobble.model.level;

import jbubblebobble.model.entity.Entity;
import jbubblebobble.model.entity.bubble.Bubble;
import jbubblebobble.model.entity.characters.Enemy;
import jbubblebobble.model.entity.characters.Player;
import utility.Config;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Collection of entities in the game collected with synchronized list.
 */
public class EntityCollection {
    private final List<Entity> entities = Collections.synchronizedList(new ArrayList<>());

    /**
     * Add synchronized.
     *
     * @param entity the entity
     */
    public void add(Entity entity) {
        synchronized (entities) {
            entities.add(entity);
        }
    }

    /**
     * Remove synchronized.
     *
     * @param entity the entity
     */
    public void remove(Entity entity) {
        synchronized (entities) {
            entities.remove(entity);
        }
    }

    /**
     * Gets entities.
     *
     * @return the entities
     */
    public List<Entity> getEntities() {
        return entities;
    }

    /**
     * Gets enemies.
     *
     * @return the enemies
     */
    public List<Enemy> getEnemies() {
        return entities.stream()
                .filter(entity -> entity instanceof Enemy)
                .map(entity -> (Enemy) entity)
                .collect(Collectors.toList());
    }

    /**
     * Gets bubbles.
     *
     * @return the bubbles
     */
    public List<Bubble> getBubbles() {
        return entities.stream()
                .filter(entity -> entity instanceof Bubble)
                .map(entity -> (Bubble) entity)
                .collect(Collectors.toList());
    }

    /**
     * Get near enemies caught by bubble list.
     *
     * @param enemy the enemy
     * @return the list
     */
    public List<Enemy> getNearEnemiesCaughtByBubble(Enemy enemy){
        return entities.stream()
                .filter(e -> e instanceof Enemy)
                .map(e -> (Enemy) e)
                .filter(e -> enemy.getCoordinate().distance(e.getCoordinate()) < Config.CHAIN_POP_RADIUS)
                .filter(Enemy::isCaughtByBubble)
                .collect(Collectors.toList());
    }

    /**
     * Get near bubbles list.
     *
     * @param entity the entity
     * @return the list
     */
    public List<Bubble> getNearBubbles(Entity entity){
        return entities.stream()
                .filter(e -> e instanceof Bubble)
                .map(e -> (Bubble) e)
                .filter(e -> entity.getCoordinate().distance(e.getCoordinate()) < Config.CHAIN_POP_RADIUS)
                .collect(Collectors.toList());
    }

    /**
     * Gets entities sorted by distance.
     *
     * @param entity the entity
     * @return the entities sorted by distance
     */
    public List<Entity> getEntitiesSortedByDistance(Entity entity) {
        return entities.stream()
                .sorted(Comparator.comparingDouble(e -> entity.getCoordinate().distance(e.getCoordinate())))
                .collect(Collectors.toList());
    }

    /**
     * Gets level map with information to display for each entity.
     *
     * @return the level map
     * @throws NullPointerException the null pointer exception
     */
    public Map<String,List<List<String>>> getLevelMap() throws NullPointerException{
            return entities.stream()
                    .collect(Collectors.groupingBy(
                            entity -> entity.getClass().getSimpleName(),
                            Collectors.mapping(Entity::getInfo , Collectors.toList())));
    }

    /**
     * Gets player.
     *
     * @return the player
     */
    public Player getPlayer() {
        return entities.stream()
                .filter(entity -> entity instanceof Player)
                .map(entity -> (Player) entity)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Player not found"));

    }


}
