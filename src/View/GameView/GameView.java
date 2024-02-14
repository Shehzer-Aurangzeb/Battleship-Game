/**
 * Name: Shehzar Aurangzeb Abbasi
 * ID: 40291795
 * COMP249
 * Assignment #1
 * Due Date: 16th February 2024
 */
package View.GameView;

import Model.enums.ElementType;
import Model.enums.Player;
import Model.grid.Grid;
import Model.position.Position;

/**
 * Represents the view of the game.
 */
public class GameView {
    private final Grid gameGrid;

    /**
     * Constructs a new GameView with the specified game grid.
     *
     * @param gameGrid The game grid to be displayed.
     */
    public GameView(Grid gameGrid) {
        this.gameGrid = gameGrid;
    }

    /**
     * Displays the current state of the game grid on the console.
     */
    public void viewGameState() {
        Position[][] grid = gameGrid.getGrid();
        for (Position[] row : grid) {
            for (Position currentPosition : row) {
                // if the current position is not called
                if (!currentPosition.isCalled()) {
                    System.out.print("_" + " ");
                } else {
                    // current position is called
                    char displayChar = '*';
                    if (currentPosition.getType() == ElementType.SHIP) {
                        displayChar = currentPosition.getOwner() == Player.COMPUTER ? 'S' : 's';

                    }
                    if (currentPosition.getType() == ElementType.GRENADE) {
                        displayChar = currentPosition.getOwner() == Player.COMPUTER ? 'G' : 'g';
                    }
                    System.out.print(displayChar + " ");
                }

            }
            System.out.println();
        }
    }

}
