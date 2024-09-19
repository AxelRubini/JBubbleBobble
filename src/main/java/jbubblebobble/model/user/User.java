package jbubblebobble.model.user;

/**
 * Represents a user in the game.
 */
public class User {
    private String name;
    private String password;
    private int highscore;
    private int gamesPlayed;
    private int wonGames;
    private int lostGames;
    private String avatarPath;

    /**
     * Instantiates a new User.
     *
     * @param name     the name
     * @param password the password
     */
    public User(String name, String password) {
        this.name = name;
        this.password = password;
        avatarPath = "default";
    }

    /**
     * Increment games played.
     */
    public void incrementGamesPlayed() {
        gamesPlayed++;
    }

    /**
     * Sets high score.
     *
     * @param highscore the highscore
     */
    public void setHighScore(int highscore) {
        if (highscore > this.highscore) {
            this.highscore = highscore;
        }
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return name;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets high score.
     *
     * @return the high score
     */
    public int getHighScore() {
        return highscore;
    }

    /**
     * Gets games played.
     *
     * @return the games played
     */
    public int getGamesPlayed() {
        return gamesPlayed;
    }


    /**
     * Sets games played.
     *
     * @param gamesPlayed the games played
     */
    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    /**
     * Gets won games.
     *
     * @return the won games
     */
    public int getWonGames() {
        return wonGames;
    }

    /**
     * Sets won games.
     *
     * @param wonGames the won games
     */
    public void setWonGames(int wonGames) {
        this.wonGames = wonGames;
    }

    /**
     * Gets lost games.
     *
     * @return the lost games
     */
    public int getLostGames() {
        return lostGames;
    }

    /**
     * Sets lost games.
     *
     * @param lostGames the lost games
     */
    public void setLostGames(int lostGames) {
        this.lostGames = lostGames;
    }

    /**
     * Gets avatar path.
     *
     * @return the avatar path
     */
    public String getAvatarPath() {
        return avatarPath;
    }

    /**
     * Sets avatar path.
     *
     * @param avatarPath the avatar path
     */
    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }
}
