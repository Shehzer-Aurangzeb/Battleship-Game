/**
 * Name: Shehzar Aurangzeb Abbasi
 * ID: 40291795
 * COMP249
 * Assignment #1
 * Due Date: 16th February 2024
 */
package Model.enums;

/**
 * Represents different types of messages that can be displayed to the player.
 */
public enum Message {
    /**
     * Indicates that the coordinates provided are invalid.
     */
    CoordINVALID,

    /**
     * Indicates that the coordinates provided have already been used.
     */
    CoordALREADYUSED,

    /**
     * Indicates that the position has already been called.
     */
    ALREADYCALLED,

    /**
     * Indicates that a ship has been hit.
     */
    SHIPHIT,

    /**
     * Indicates that a grenade has been encountered.
     */
    GRENADE,

    /**
     * Indicates that nothing has been hit.
     */
    NOTHING
}
