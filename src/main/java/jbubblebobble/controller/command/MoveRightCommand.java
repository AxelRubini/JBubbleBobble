package jbubblebobble.controller.command;

import jbubblebobble.controller.Command;
import jbubblebobble.model.entity.characters.Player;

/**
 * Move right command.
 */
public class MoveRightCommand implements Command {
    private Player player;

    /**
     * Instantiates a new Move right command.
     *
     * @param player the player
     */
    public MoveRightCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
            player.moveRight();
    }
}
