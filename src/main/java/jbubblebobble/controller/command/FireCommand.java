package jbubblebobble.controller.command;

import jbubblebobble.controller.AudioManager;
import jbubblebobble.controller.Command;
import jbubblebobble.model.entity.characters.Player;
import utility.Config;

/**
 * Fire command execute the fire of a bubble.
 */
public class FireCommand implements Command {
    private Player player;

    /**
     * Instantiates a new Fire command.
     *
     * @param player the player
     */
    public FireCommand(Player player) {
        this.player = player;
    }
    /**
     * Execute the fire of a bubble.
     *if the player effectively fire a bubble play the fire sound.
     *
     */
    @Override
    public void execute() {
        if (player.fire()){
            AudioManager.getInstance().play(Config.PATH_TO_AUDIO_FIRE);

        }
    }
}
