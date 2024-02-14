/**
 * Name: Shehzar Aurangzeb Abbasi
 * ID: 40291795
 * COMP249
 * Assignment #1
 * Due Date: 16th February 2024
 */
package utils;

import Model.enums.ElementType;
import Model.enums.Message;
import Model.grid.Grid;
import Model.position.Position;

import java.util.Random;

/**
 * Contains utility methods for various operations related to the game.
 */
public class Utilities {
    static Random random = new Random();

    /**
     * Checks if the given coordinates are within the grid.
     *
     * @param coordinates The coordinates to check.
     * @return {@code true} if the coordinates are valid, {@code false} otherwise.
     */
    public static boolean isValidCoordinates(int[] coordinates) {
        int row = coordinates[0];
        int col = coordinates[1];
        if (row < 0 || col < 0) return false;
        else return row < 8 && col < 8;
    }

    /**
     * Checks if the given coordinates are available in the game grid.
     *
     * @param coordinates The coordinates to check.
     * @param gameGrid    The game grid.
     * @return {@code true} if the coordinates are available, {@code false} otherwise.
     */
    public static boolean isCoordinatesAvailable(int[] coordinates, Grid gameGrid) {
        Position position = gameGrid.getPositionAt(coordinates[0], coordinates[1]);
        // position is available if there is no element.
        return position.getType() == ElementType.NOTHING;
    }

    /**
     * Decodes the string representation of coordinates into an array of integers.
     *
     * @param coordinates The string representation of coordinates (e.g., "A1").
     * @return An array of integers representing the decoded coordinates.
     */
    public static int[] decodeCoordinates(String coordinates) {
        // calculating index by subtracting the ascii value of given character from ascii value of A.
        int row = (int) coordinates.toUpperCase().charAt(0) - 'A';
        int col = Integer.parseInt(coordinates.substring(1)) - 1;
        return new int[]{row, col};
    }

    /**
     * Generates a random string representation of coordinates.
     *
     * @return A randomly generated string representation of coordinates (e.g., "A1").
     */
    public static String generateRandomCoordinates() {
        char row = (char) ('A' + random.nextInt(8)); //'A' ascii code is 65;
        int col = random.nextInt(8) + 1; // excluding zero because when we decode it we will do -1.
        return row + Integer.toString(col);
    }

    /**
     * Provides feedback message based on the given message type.
     *
     * @param messageType The type of message to display.
     */
    public static void feedbackMessage(Message messageType) {
        switch (messageType) {
            case CoordINVALID:
                System.out.println("Sorry, coordinates outside the grid. Try again.");
                break;
            case CoordALREADYUSED:
                System.out.println("Sorry, coordinates already used. Try again.");
                break;
            case ALREADYCALLED:
                System.out.println("Position already called.");
                break;
            case SHIPHIT:
                System.out.println("Ship hit.");
                break;
            case GRENADE:
                System.out.println("Boom! Grenade.");
                break;
            case NOTHING:
                System.out.println("Nothing.");
                break;
        }
    }

}
