package jbubblebobble.controller;

import javafx.application.Platform;
import jbubblebobble.controller.applicationstate.LogInState;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JBubbleBobble launcher of the application.
 */
public class JBubbleBobble extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        AudioManager.getInstance();
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
        primaryStage.setTitle("JBubbleBobble");
        ApplicationContext context = ApplicationContext.getInstance();
        context.setState(new LogInState(primaryStage));
    }


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}