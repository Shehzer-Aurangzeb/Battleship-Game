/**
 * Name: Shehzar Aurangzeb Abbasi
 * ID: 40291795
 * COMP249
 * Assignment #1
 * Due Date: 16th February 2024
 */
package Model.enums;

/**
 * Represents the different types of players in a game.
 */
public enum Player {
    USER,
    COMPUTER,
    NULL; // or UNASSIGNED

    private boolean turnSkipped;

    /**
     * Checks if the player's turn has been skipped.
     *
     * @return {@code true} if the player's turn has been skipped, {@code false} otherwise.
     */
    public boolean isTurnSkipped() {
        return turnSkipped;
    }

    /**
     * Sets whether the player's turn has been skipped.
     *
     * @param turnSkipped {@code true} to indicate the player's turn has been skipped, {@code false} otherwise.
     */
    public void setTurnSkipped(boolean turnSkipped) {
        this.turnSkipped = turnSkipped;
    }
}
