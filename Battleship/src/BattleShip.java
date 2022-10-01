import java.util.Scanner;

public class BattleShip {

    private final Grid[][] map;
    private final Grid[][] computerMap;

    private final Scanner scanner = new Scanner(System.in);

    // TODO:
    //  - Add so the computer first shoots when you press a button
    //  - Change method names to be more descriptive

    public BattleShip() {
        int mapSize = 10; // should be a global variable
        map = new Grid[mapSize][mapSize];
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                map[i][j] = new Grid();
            }
        }

        computerMap = new Grid[mapSize][mapSize];
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                computerMap[i][j] = new Grid();
            }
        }
        // TODO Auto-generated constructor stub
    }

    public void start() {
        String next = welcomeText();
        if (!next.equals("1")) {
            clearScreen();
            start();
        }

        clearScreen();
        printPlaceShipText();
        for (int i = 0; i < 5; i++) {
            placeAShip(i + 1);
        }
        generateComputerMap();

        clearScreen();
        printGameText();

        while (true) {
            playerTurn();
            if (checkIfPlayerWon()) {
                break;
            }
            computerTurn();
            if (checkIfComputerWon()) {
                break;
            }
        }
    }


    public String welcomeText() {
        System.out.println("Welcome to BattleShip!");
        System.out.println("You will be playing against the computer.");
        System.out.println("You will be given 5 ships and 5 grenades.");
        System.out.println();
        System.out.println("Press 1 to start the game.");
        return scanner.nextLine();
    }

    public void placeAShip(int shipNumber) {
        System.out.println("Please type in the X and Y coordinates for your ship number " + shipNumber + ".");
        String input = scanner.nextLine();
        String[] inputArray = input.split(" ");
        if (inputArray.length != 2) {
            System.out.println("Please type in two numbers.");
            placeAShip(shipNumber);
            return;
        }
        try {
            int x = Integer.parseInt(inputArray[0]);
            int y = Integer.parseInt(inputArray[1]);
            if (x < 0 || x > 9 || y < 0 || y > 9) {
                System.out.println("Please type in two numbers between 0 and 9.");
                placeAShip(shipNumber);
                return;
            }
            if (map[y][x].isShip()) {
                System.out.println("You already have a ship there.");
                placeAShip(shipNumber);
                return;
            }
            map[y][x].setShip();
        } catch (NumberFormatException e) {
            System.out.println("Please type in two numbers.");
            placeAShip(shipNumber);
            return;
        }
        printMap();
    }

    public void playerTurn() {
        System.out.println("Please type in the X and Y coordinates for your shot.");
        String input = scanner.nextLine();
        String[] inputArray = input.split(" ");
        try {
            int x = Integer.parseInt(inputArray[0]);
            int y = Integer.parseInt(inputArray[1]);
            computerMap[y][x].setShotAt();
            if (computerMap[y][x].isShip()) {
                System.out.println("You hit a ship!");
            } else {
                System.out.println("You missed!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please type in two numbers.");
            playerTurn();
            return;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please type in two numbers between 0 and 9.");
            playerTurn();
            return;
        }
        System.out.println();
        System.out.println("COMPUTER MAP:");
        System.out.println();
        printComputerMap();
        System.out.println();
        System.out.println("RAW MAP: (for debugging)");
        System.out.println();
        printComputerRawMap();
    }

    public boolean checkIfPlayerWon() {
        int computerShips = 5;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (computerMap[i][j].isShip() && computerMap[i][j].isShotAt()) {
                    computerShips--;
                }
            }
        }
        if (computerShips == 0) {
            clearScreen();
            System.out.println("You win!");
            return true;
        }
        return false;
    }

    public void computerTurn() {
        System.out.println("The computer is shooting at your ships.");
        int computerX = (int) (Math.random() * 10);
        int computerY = (int) (Math.random() * 10);
        if (map[computerY][computerX].isShip()) {
            System.out.println("The computer hit a ship!");
        } else {
            System.out.println("The computer missed!");
        }
        map[computerY][computerX].setShotAt();
        System.out.println();
        System.out.println("PLAYER MAP:");
        System.out.println();
        printMap();
    }

    public boolean checkIfComputerWon() {
        int playerShips = 5;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (map[i][j].isShip() && map[i][j].isShotAt()) {
                    playerShips--;
                }
            }
        }
        if (playerShips == 0) {
            clearScreen();
            System.out.println("You lose!");
            return true;
        }
        return false;
    }

    public void printPlaceShipText() {
        System.out.println("You will now place your ships.");
        System.out.println("Please type in X and Y coordinates for each ship.");
        System.out.println("The X and Y coordinates must be between 0 and 9.");
        System.out.println("The X and Y coordinates must be separated by a space.");
        System.out.println("For example, to place a ship at (3, 4), type in 3 4.");
        System.out.println("You will be given 5 ships.");
    }

    public void printGameText() {
        System.out.println("You will now shoot at the computer's ships.");
        System.out.println("Please type in X and Y coordinates for each shot.");
        System.out.println("The X and Y coordinates must be between 0 and 9.");
        System.out.println("The X and Y coordinates must be separated by a space.");
        System.out.println("For example, to shoot at (3, 4), type in 3 4.");
    }

    // ----------------------------
    // Print maps
    // ----------------------------

    public void printMap() {
        System.out.println("  0 1 2 3 4 5 6 7 8 9");
        for (int i = 0; i < map.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j].getState() + " ");
            }
            System.out.println();
        }
    }

    public void printComputerMap() {
        System.out.println("  0 1 2 3 4 5 6 7 8 9");
        for (int i = 0; i < computerMap.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < computerMap[i].length; j++) {
                if (computerMap[i][j].isShotAt()) {
                    System.out.print((computerMap[i][j].isShip() ? "H" : "M") + " ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }

    public void printComputerRawMap() {
        System.out.println("  0 1 2 3 4 5 6 7 8 9");
        for (int i = 0; i < computerMap.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < computerMap[i].length; j++) {
                System.out.print(computerMap[i][j].getState() + " ");
            }
            System.out.println();
        }
    }

    // ----------------------------
    // Generate computer map
    // ----------------------------

    public void generateComputerMap() {
        for (int i = 0; i < 5; i++) {
            int x = (int) (Math.random() * 10);
            int y = (int) (Math.random() * 10);
            if (computerMap[y][x].isShip()) {
                i--;
            } else {
                computerMap[y][x].setShip();
            }
        }
    }

    // ----------------------------
    // Clear Screen
    // ----------------------------
    public void clearScreen() {
        System.out.println(System.lineSeparator().repeat(50));
    }
}
