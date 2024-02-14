/**
 * Name: Shehzar Aurangzeb Abbasi
 * ID: 40291795
 * COMP249
 * Assignment #1
 * Due Date: 16th February 2024
 */
package Model.position;

import Model.enums.ElementType;
import Model.enums.Player;

/**
 * Represents a position on the game grid.
 */
public class Position {
    private ElementType type;
    private Player owner;
    private boolean isCalled;

    /**
     * Constructs a new position with the specified type and owner.
     *
     * @param type  The type of element occupying the position.
     * @param owner The player who owns the position.
     */
    public Position(ElementType type, Player owner) {
        this.isCalled = false;
        this.type = type;
        this.owner = owner;
    }

    // Getters

    /**
     * Gets the type of element occupying the position.
     *
     * @return The type of element occupying the position.
     */
    public ElementType getType() {
        return this.type;
    }

    /**
     * Checks if the position has been called.
     *
     * @return {@code true} if the position has been called, {@code false} otherwise.
     */
    public boolean isCalled() {
        return this.isCalled;
    }

    /**
     * Gets the owner of the position.
     *
     * @return The player who owns the position.
     */
    public Player getOwner() {
        return this.owner;
    }

    // Setters

    /**
     * Sets the type of element occupying the position.
     *
     * @param type The type of element to set.
     */
    public void setType(ElementType type) {
        this.type = type;
    }

    /**
     * Sets the owner of the position.
     *
     * @param owner The player who owns the position.
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * Sets whether the position has been called.
     *
     * @param isCalled {@code true} if the position has been called, {@code false} otherwise.
     */
    public void setIsCalled(boolean isCalled) {
        this.isCalled = isCalled;
    }
}
