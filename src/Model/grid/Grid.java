/**
 * Name: Shehzar Aurangzeb Abbasi
 * ID: 40291795
 * COMP249
 * Assignment #1
 * Due Date: 16th February 2024
 */
package Model.grid;

import Model.enums.ElementType;
import Model.enums.Player;
import Model.position.Position;

/**
 * Represents the grid on which the game is played.
 */
public class Grid {
    private final Position[][] grid;

    /**
     * Constructs a new grid of the specified size.
     *
     * @param size The size of the grid (size x size).
     */
    public Grid(int size) {
        grid = new Position[size][size];
        initializeGrid();
    }

    /**
     * Initializes the grid by assigning {@link ElementType#NOTHING} as the element type
     * and {@link Player#NULL} as the owner for each position.
     */
    private void initializeGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j] = new Position(ElementType.NOTHING, Player.NULL);
            }
        }
    }

    /**
     * Gets the grid.
     *
     * @return The grid represented as a two-dimensional array of positions.
     */
    public Position[][] getGrid() {
        return grid;
    }

    /**
     * Gets the position at the specified coordinates.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @return The position at the specified coordinates.
     */
    public Position getPositionAt(int x, int y) {
        return grid[x][y];
    }

    /**
     * Gets the ships belonging to the specified player.
     *
     * @param player The player whose ships to retrieve.
     * @return An array of positions representing the ships belonging to the player.
     */
    public Position[] getPlayerShips(Player player) {
        // as there will be six ships
        Position[] playerShips = new Position[6];
        int idx = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j].getOwner() != player) continue;
                else if (grid[i][j].getType() != ElementType.SHIP) continue;
                playerShips[idx] = grid[i][j];
                idx++;
            }
        }
        return playerShips;
    }

    /**
     * Places a ship at the specified coordinates for the specified owner.
     *
     * @param x     The x-coordinate.
     * @param y     The y-coordinate.
     * @param owner The owner of the ship.
     */
    public void placeShip(int x, int y, Player owner) {
        grid[x][y].setType(ElementType.SHIP);
        grid[x][y].setOwner(owner);
    }

    /**
     * Places a grenade at the specified coordinates for the specified owner.
     *
     * @param x     The x-coordinate.
     * @param y     The y-coordinate.
     * @param owner The owner of the grenade.
     */
    public void placeGrenade(int x, int y, Player owner) {
        grid[x][y].setType(ElementType.GRENADE);
        grid[x][y].setOwner(owner);
    }
}
