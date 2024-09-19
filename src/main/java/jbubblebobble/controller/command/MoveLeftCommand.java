package jbubblebobble.controller.command;

import jbubblebobble.controller.Command;
import jbubblebobble.model.entity.characters.Player;

/**
 * Move left command.
 */
public class MoveLeftCommand implements Command {
    private Player player;

    /**
     * Instantiates a new Move left command.
     *
     * @param player the player
     */
    public MoveLeftCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
            player.moveLeft();

    }
}
