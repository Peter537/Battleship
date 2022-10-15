import java.util.Scanner;

public class BattleShip {

    private final Player player;
    private final Computer computer;
    private final TextUI textUI;
    private final int mapSize = 5;
    private final int ships = 5;

    // TODO:
    //  - Add so the computer first shoots when you press a button
    //  - Change method names to be more descriptive
    //  - Save TextUI to Player class and use the variable instead of passing it to methods & not use System.out.println

    public BattleShip() {
        player = new Player(mapSize);
        computer = new Computer(mapSize);
        textUI = new TextUI();
    }

    public void start() {
        String input = textUI.getUserInput(textUI.getWelcomeText());
        if (!input.equals("1")) {
            textUI.clearScreen();
            start();
        }

        textUI.clearScreen();
        textUI.print(textUI.getPlaceShipText());
        player.placeShips(textUI, ships);
        computer.generateShips(ships);

        while (true) {
            player.makeMove(textUI, computer);
            if (!computer.hasShipsLeft()) {
                textUI.clearScreen();
                textUI.print("");
                textUI.print("The Computers map");
                textUI.print("");
                computer.printMap();
                textUI.print("");
                textUI.print("Your map");
                textUI.print("");
                player.printMap();
                textUI.print("");
                textUI.print("You win");
                break;
            }
            computer.makeMove(player);
            if (!player.hasShipsLeft()) {
                textUI.clearScreen();
                textUI.print("");
                textUI.print("The Computers map");
                textUI.print("");
                computer.printMap();
                textUI.print("");
                textUI.print("Your map");
                textUI.print("");
                player.printMap();
                textUI.print("");
                textUI.print("You lose!");
                break;
            }
        }
    }

    // ----------------------------
    // Getters
    // ----------------------------

    public Computer getComputer() {
        return computer;
    }

    public Player getPlayer() {
        return player;
    }

    public TextUI getTextUI() {
        return textUI;
    }

    public int getMapSize() {
        return mapSize;
    }

    public int getShips() {
        return ships;
    }
}