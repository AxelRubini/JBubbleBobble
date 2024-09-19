package jbubblebobble.controller.command;

import jbubblebobble.controller.AudioManager;
import jbubblebobble.controller.Command;
import jbubblebobble.model.entity.characters.Player;
import utility.Config;

/**
 * JumpCommand execute the jump.
 */
public class JumpCommand implements Command {
    private Player player;

    /**
     * Instantiates a new Jump command.
     *
     * @param player the player
     */
    public JumpCommand(Player player) {
        this.player = player;
    }
    /**
     * Execute the Jump of the player.
     * if the player is in an idle state or he's jumping he willl jump vertically and if the jump is successful play the jump sound.
     * if the player is moving left or right he will jump horizontally and if the jump is successful play the jump sound.
     */
    @Override
    public void execute() {
        boolean jumped = false;
        switch (player.getState()) {
            case IDLE_LEFT,JUMPING_LEFT:
                if (player.verticalJump())
                    jumped = true;
                break;
            case IDLE_RIGHT,JUMPING_RIGHT:
                if (player.verticalJump())
                    jumped = true;
                break;
            case MOVING_LEFT:
                if (player.jump(false))
                    jumped = true;
                break;
            case MOVING_RIGHT:
                if (player.jump(true))
                    jumped = true;
                break;

        }
        if (jumped) {
            AudioManager.getInstance().play(Config.PATH_TO_AUDIO_JUMP);
        }
    }
}
