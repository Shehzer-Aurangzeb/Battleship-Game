/**
 * Name: Shehzar Aurangzeb Abbasi
 * ID: 40291795
 * COMP249
 * Assignment #1
 * Due Date: 16th February 2024
 */
package Controller.GameController;


import Model.enums.ElementType;
import Model.enums.Message;
import Model.enums.Player;

import static utils.Utilities.*;

import Model.grid.Grid;
import Model.position.Position;
import View.GameView.GameView;

import java.util.Scanner;

/**
 * Controls the flow of the game.
 */
public class GameController {
    private Grid gameGrid;
    private GameView gameView;
    private Scanner scanner;
    private Position[] userShips;
    private Position[] computerShips;
    private Player winner;

    /**
     * Constructs a new GameController with the specified game grid and game view.
     *
     * @param gameGrid The game grid.
     * @param gameView The game view.
     */
    public GameController(Grid gameGrid, GameView gameView) {
        this.gameGrid = gameGrid;
        this.gameView = gameView;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the game.
     */
    public void startGame() {
        //initialize game by placing ships and grenades for both players.
        initializeGame();
        while (!isGameOver()) {
            if (!Player.USER.isTurnSkipped()) {
                executePlayerTurn(Player.USER);
                gameView.viewGameState();
            } else {
                Player.USER.setTurnSkipped(false); // Reset the flag
            }
            //after a turn if game is over break out of the loop
            if (isGameOver()) break;
            if (!Player.COMPUTER.isTurnSkipped()) {
                executePlayerTurn(Player.COMPUTER);
                gameView.viewGameState();
            } else {
                Player.COMPUTER.setTurnSkipped(false); // Reset the flag
            }
        }
        if (winner == Player.USER) System.out.print(" You win!");
        if (winner == Player.COMPUTER) System.out.print(" You lost!");

    }
    /**
     * Initialize the game by placing ships and grenades for both players.
     */
    private void initializeGame() {
        System.out.println("Hi, let's play Battleship!");
        placeUserShipsAndGrenades();
        //save positions where user placed its ships
        userShips = gameGrid.getPlayerShips(Player.USER);
        placeComputerShipsAndGrenades();
        //save positions where computer placed its ships
        computerShips = gameGrid.getPlayerShips(Player.COMPUTER);
        System.out.println("OK, the computer placed its ships and grenades at random.  Let's play. ");
        gameView.viewGameState();


    }
    /**
     * Function to place ships and grenades for user
     */
    private void placeUserShipsAndGrenades() {
        int i = 1;
        //because 6 ships + 4 grenades needs to be placed
        while (i <= 10) {
            if (i <= 6) {
                System.out.printf("Enter the coordinates of your ship #%d: ", i);
            }
            if (i > 6) {
                System.out.printf("Enter the coordinates of your grenade #%d: ", i - 6);
            }
            String coordinates = scanner.nextLine();
            int[] decodedCoordinates = decodeCoordinates(coordinates);
            if (!isValidCoordinates(decodedCoordinates)) {
                feedbackMessage(Message.CoordINVALID);
                continue;
            }
            ;
            if (!isCoordinatesAvailable(decodedCoordinates, gameGrid)) {
                feedbackMessage(Message.CoordALREADYUSED);
                continue;
            }
            ;
            if (i <= 6) gameGrid.placeShip(decodedCoordinates[0], decodedCoordinates[1], Player.USER);
            if (i > 6) gameGrid.placeGrenade(decodedCoordinates[0], decodedCoordinates[1], Player.USER);
            i++;
        }
    }
    /**
     * Function to place ships and grenades for computer
     */
    private void placeComputerShipsAndGrenades() {
        int i = 1;
        //because 4 grenades needs to be placed
        while (i <= 10) {
            String coordinates = generateRandomCoordinates();
            int[] decodedCoordinates = decodeCoordinates(coordinates);
            if (!isValidCoordinates(decodedCoordinates)) continue;
            if (!isCoordinatesAvailable(decodedCoordinates, gameGrid)) continue;
            if (i <= 6) gameGrid.placeShip(decodedCoordinates[0], decodedCoordinates[1], Player.COMPUTER);
            if (i > 6) gameGrid.placeGrenade(decodedCoordinates[0], decodedCoordinates[1], Player.COMPUTER);
            i++;
        }
    }
    /**
     * Constructs a new GameView with the specified game grid.
     *
     * @param position The position to fire rocket at.
     * @param player The player whose firing the rocket.
     */
    private void fireRocket(Position position, Player player) {

        ElementType element = position.getType();
        if (element == ElementType.SHIP) feedbackMessage(Message.SHIPHIT);
        if (element == ElementType.GRENADE) {
            feedbackMessage(Message.GRENADE);
            player.setTurnSkipped(true);
        }
        if (element == ElementType.NOTHING) feedbackMessage(Message.NOTHING);
        position.setIsCalled(true);

    }

    private void executePlayerTurn(Player player) {
        boolean isValidTurn = false;
        String rocketPosition;
        while (!isValidTurn) {
            if (player == Player.USER) {
                System.out.print("position of your rocket: ");
                rocketPosition = scanner.next();
            } else {
                rocketPosition = generateRandomCoordinates();
                System.out.println("position of my rocket: " + rocketPosition);
            }
            int[] decodedCoordinates = decodeCoordinates(rocketPosition);
            if (!isValidCoordinates(decodedCoordinates)) {
                feedbackMessage(Message.CoordINVALID);
                continue;
            }
            Position position = gameGrid.getPositionAt(decodedCoordinates[0], decodedCoordinates[1]);
            if (position.isCalled()) {
                //if position is called let player try again
                feedbackMessage(Message.ALREADYCALLED);
                continue;
            }
            fireRocket(position, player);
            isValidTurn = true;

        }
    }

    //loop termination condition
    /**
     * function to check if the game is over. if any of the player's all ships are sunk then game is over.
     * @return {@code true} if the game is over, {@code false} otherwise.
     */
    private boolean isGameOver() {
        //if any player's all ships are sunk the game is over
        boolean allUserShipsSunk = areAllShipsSunk(userShips);
        boolean allComputerShipsSunk = areAllShipsSunk(computerShips);
        if (allUserShipsSunk) {
            winner = Player.COMPUTER;
            return true;
        }
        if (allComputerShipsSunk) {
            winner = Player.USER;
            return true;
        }

        return false;
    }

    /**
     * function to check if the provided player's ships are sunk or not.
     * @return {@code true} if the all ships are sunk, {@code false} otherwise.
     */
    private boolean areAllShipsSunk(Position[] ships) {
        for (Position ship : ships) {
            //if ship position is not called means it is still there.
            if (!ship.isCalled()) return false;
        }
        return true;
    }


}
