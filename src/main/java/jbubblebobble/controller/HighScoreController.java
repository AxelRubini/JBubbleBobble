package jbubblebobble.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import jbubblebobble.controller.applicationstate.MainMenuState;
import utility.Config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 *  High score controller.
 */
public class HighScoreController {

    @FXML
    private TableView<PlayerScore> tableView;

    @FXML
    private TableColumn<PlayerScore,String> player;

    @FXML
    private TableColumn<PlayerScore,String> score;

    @FXML
    private Button menuButton;

    private ObservableList<PlayerScore> playersHighScores;

    /**
     * Instantiates a new High score controller and build an ordered Observable list  to  fill the table view.
     */
    public HighScoreController(){
        playersHighScores= FXCollections.observableArrayList(fillPlayersHighScores());

    }
    private List<PlayerScore> fillPlayersHighScores(){
        List<PlayerScore> playersHighScores = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(Config.PATH_TO_USER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    playersHighScores.add(new PlayerScore(parts[0], parts[3]));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading users file", e);
        }
        return playersHighScores.stream()
                .sorted((ps1, ps2) -> Integer.compare(Integer.parseInt(ps2.getScore().get()), Integer.parseInt(ps1.getScore().get())))
                .collect(Collectors.toList());
    }
    @FXML
    private void initialize(){

        player.setCellValueFactory(cellData -> cellData.getValue().getPlayer());
        score.setCellValueFactory(cellData -> cellData.getValue().getScore());
        tableView.setItems(playersHighScores);
        menuButton.setOnAction(e -> {
            try {
                returnToMenu();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
    private void returnToMenu() throws IOException {
        Stage stage = (Stage) tableView.getScene().getWindow();
        ApplicationContext.getInstance().setState(new MainMenuState(stage));
    }

    private static class PlayerScore{
        private final StringProperty player;
        private final StringProperty score;

        /**
         * Instantiates a new Player score.
         *
         * @param player the player
         * @param score  the score
         */
        public PlayerScore(String player, String score){
            this.player=new SimpleStringProperty(player);
            this.score=new SimpleStringProperty(score);
        }

        /**
         * Get player string property.
         *
         * @return the string property
         */
        public StringProperty getPlayer(){
            return player;
        }

        /**
         * Get score string property.
         *
         * @return the string property
         */
        public StringProperty getScore(){
            return score;
        }
    }

}
