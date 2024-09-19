package jbubblebobble.controller;

import jbubblebobble.model.user.User;
import jbubblebobble.model.user.UserManager;

import java.io.IOException;
import java.util.Map;

/**
 *
 * The application context is a singleton class that is used to store the application state and the user.
 */
public class ApplicationContext {
    private static ApplicationContext instance;
    private ApplicationState state;
    private User user;
    private UserManager userManager;
    private Map<String,String> avatars;

    private ApplicationContext() {

        avatars = Map.of("default","/avatar/uomo.png","avatar1","/avatar/avatar.png","avatar2","/avatar/ghost.png");

    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ApplicationContext getInstance() {
        if (instance == null) {
            instance = new ApplicationContext();
        }
        return instance;
    }

    /**
     * Sets state.
     *
     * @param state the state
     * @throws IOException the io exception
     */
    public void setState(ApplicationState state) throws IOException {
        this.state = state;
        this.state.enterState();
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public ApplicationState getState() {
        return state;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user manager.
     *
     * @param userManager the user manager
     */
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    /**
     * Gets user manager.
     *
     * @return the user manager
     */
    public UserManager getUserManager() {
        return userManager;
    }

    /**
     * Get avatars map.
     *
     * @return the map
     */
    public Map<String,String> getAvatars(){
        return avatars;
    }

    /**
     * Get avatar string.
     *
     * @param key the key
     * @return the string
     */
    public String getAvatar(String key){
        return avatars.get(key);
    }
}
