package utility;

/**
 * The type Config.
 */
public final class Config {



    private Config(){

        throw new IllegalStateException("Utility class");

    }

    /**
     * The constant PLAYER_SPEED.
     */
    public static final double PLAYER_SPEED = 1.5;
    /**
     * The constant SCREEN_WIDTH.
     */
    public static final int SCREEN_WIDTH = 576;
    /**
     * The constant SCREEN_HEIGHT.
     */
    public static final int SCREEN_HEIGHT = 432;
    /**
     * The constant TILE_SIZE.
     */
    public static final int TILE_SIZE = 18;
    /**
     * The constant NUM_ROWS.
     */
    public static final int NUM_ROWS = 24;
    /**
     * The constant NUM_COLS.
     */
    public static final int NUM_COLS = 32;
    /**
     * The constant NUM_LEVELS.
     */
    public static final int NUM_LEVELS = 8;
    /**
     * The constant GRAVITY.
     */
    public static final double GRAVITY = 0.08;
    /**
     * The constant BUBBLE_FLY_SPEED.
     */
    public static final double BUBBLE_FLY_SPEED = 3.0;
    /**
     * The constant BUBBLE_LIVE_TIME.
     */
    public static final long BUBBLE_LIVE_TIME = 30000;
    /**
     * The constant BUBBLE_FLY_TIME.
     */
    public static final long BUBBLE_FLY_TIME = 300;
    /**
     * The constant PATH_TO_SPRITE.
     */
    public static final String PATH_TO_SPRITE = "/sprite/";
    /**
     * The constant FRAME_DURATION.
     */
    public static final long FRAME_DURATION = 250;
    /**
     * The constant JUMP_STRENGTH.
     */
    public static final double JUMP_STRENGTH = -3;
    /**
     * The constant CHAIN_POP_RADIUS.
     */
    public static final double CHAIN_POP_RADIUS = 30;
    /**
     * The constant MAX_FALL_SPEED.
     */
    public static final double MAX_FALL_SPEED = 2;
    /**
     * The constant STATIC_OBJECT_FRAME.
     */
    public static final int STATIC_OBJECT_FRAME = 0;
    /**
     * The constant VULNERABLE_TIME.
     */
    public static final double VULNERABLE_TIME = 15000;
    /**
     * The constant TIMED_POWER_UP.
     */
    public static final long TIMED_POWER_UP = 10000;
    /**
     * The constant FIRE_RATE.
     */
    public static final long FIRE_RATE = 500;
    /**
     * The constant DISTANCE_TRVELED.
     */
    public static final int DISTANCE_TRVELED = Config.SCREEN_WIDTH * 15;
    /**
     * The constant PURPLE_GUM.
     */
    public static final int PURPLE_GUM =  35;
    /**
     * The constant BLUE_GUM.
     */
    public static final int BLUE_GUM =  35;
    /**
     * The constant YELLOW_GUM.
     */
    public static final int YELLOW_GUM =  35;
    /**
     * The constant EXTRA_LIFE.
     */
    public static final int[] EXTRA_LIFE = {10000, 20000, 30000, 40000, 50000, 60000, 70000, 80000};
    /**
     * The constant PURPLE_RING.
     */
    public static final int PURPLE_RING =  3;
    /**
     * The constant BLUE_RING.
     */
    public static final int BLUE_RING =  3;
    /**
     * The constant RED_RING.
     */
    public static final int RED_RING =  3;
    /**
     * The constant KILLED_ENEMY.
     */
    public static final int KILLED_ENEMY = 50;
    /**
     * The constant EnemyFireRate.
     */
    public static final long EnemyFireRate = 5000;
    /**
     * The constant PATH_TO_MAP.
     */
    public static final String PATH_TO_MAP = "src/main/resources/levels/";
    /**
     * The constant PATH_TO_AUDIO_FIRE.
     */
    public static final String PATH_TO_AUDIO_FIRE = "src/main/resources/sounds/Shoot Bubble.wav";
    /**
     * The constant PATH_TO_AUDIO_GAME_OVER.
     */
    public static final String PATH_TO_AUDIO_GAME_OVER = "src/main/resources/sounds/09-Game-Over.wav";
    /**
     * The constant PATH_TO_AUDIO_THEME.
     */
    public static final String PATH_TO_AUDIO_THEME = "src/main/resources/sounds/01-Introduction-_-Main-Theme.wav";
    /**
     * The constant PATH_TO_AUDIO_JUMP.
     */
    public static final String PATH_TO_AUDIO_JUMP = "src/main/resources/sounds/Jump.wav";
    /**
     * The constant PATH_TO_USER_FILE.
     */
    public static final String PATH_TO_USER_FILE = "src/main/resources/users.txt";
    /**
     * The constant PATH_TO_AUDIO_POWER_UP.
     */
    public static final String PATH_TO_AUDIO_POWER_UP = "src/main/resources/sounds/Powerup.wav";
    /**
     * The constant PATH_TO_AUDIO_PLAYER_DEATH.
     */
    public static final String PATH_TO_AUDIO_PLAYER_DEATH = "src/main/resources/sounds/Player Death.wav";
    /**
     * The constant PATH_TO_AUDIO_ENEMY_DEATH.
     */
    public static final String PATH_TO_AUDIO_ENEMY_DEATH = "src/main/resources/sounds/EnemyDeath.wav";
    /**
     * The constant of JUMP_RATE.
     */
    public  static final  long JUMP_RATE = 500;
}
