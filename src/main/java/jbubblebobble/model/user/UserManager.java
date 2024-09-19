package jbubblebobble.model.user;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * UserManager class is responsible for managing the users data
 */
public class UserManager {
    private Map<String,User> users = new HashMap<String,User>();
    private String filePath = "src/main/resources/users.txt";

    /**
     * Instantiates a new User manager.
     */
    public UserManager() {
        loadUsersFromFile(filePath);
    }

    private void  loadUsersFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    User user = new User(parts[0], parts[1]);
                    user.setGamesPlayed(Integer.parseInt(parts[2]));
                    user.setHighScore(Integer.parseInt(parts[3]));
                    user.setWonGames(Integer.parseInt(parts[4]));
                    user.setLostGames(Integer.parseInt(parts[5]));
                    user.setAvatarPath(parts[6]);
                    users.put(parts[0], user);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading users file", e);
        }
    }

    /**
     * this method is used to authenticate the user
     * if the user is not in the file, it will be added
     *
     * @param username the username
     * @param password the password
     * @return the boolean
     */
    public boolean authenticate(String username, String password) {
        if (users.containsKey(username)) {
            return users.get(username).getPassword().equals(password);
        } else {
            User newUser = new User(username, password);
            users.put(username, newUser);
            saveUserToFile(newUser);
            return true;
        }
    }

    private void saveUserToFile(User user) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(user.getName() + "," + user.getPassword() + "," + user.getGamesPlayed() + "," + user.getHighScore() + "," + user.getWonGames() + "," + user.getLostGames() + "," + user.getAvatarPath());
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Error writing to users file", e);
        }
    }

    /**
     * Gets user.
     *
     * @param username the username
     * @return the user
     */
    public User getUser(String username) {
        return users.get(username);
    }

    /**
     * Update users infos in the file.
     *
     * @param user the user
     */
    public void updateUser(User user) {
        users.put(user.getName(), user);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (User u : users.values()) {
                bw.write(u.getName() + "," + u.getPassword() + "," + u.getGamesPlayed() + "," + u.getHighScore() + "," + u.getWonGames() + "," + u.getLostGames() + "," + u.getAvatarPath());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error writing to users file", e);
        }
    }
}
