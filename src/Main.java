/**
 * Name: Shehzar Aurangzeb Abbasi
 * ID: 40291795
 * COMP249
 * Assignment #1
 * Due Date: 16th February 2024
 */
import Controller.GameController.GameController;
import Model.grid.Grid;
import View.GameView.GameView;

public class Main {
    public static void main(String[] args) {
        // Create a new game grid with a size of 8x8
        Grid gameGrid = new Grid(8);
        // Create a new game view, passing the game grid to it
        GameView gameView = new GameView(gameGrid);
        // Create a new game controller, passing both the game grid and game view to it
        GameController gameController = new GameController(gameGrid, gameView);
        //start the game
        gameController.startGame();

    }
}