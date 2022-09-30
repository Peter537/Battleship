import java.util.Scanner;

public class BattleShip {

    private final Grid[][] map;
    private final Grid[][] computerMap;

    private final Scanner scanner = new Scanner(System.in);

    // TODO:
    //  - Add so the computer first shoots when you press a button

    public BattleShip() {
        int mapSize = 10;
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
        System.out.println("Welcome to BattleShip!");
        System.out.println("You will be playing against the computer.");
        System.out.println("You will be given 5 ships and 5 grenades.");
        System.out.println();
        System.out.println("Press 1 to start the game.");

        String next = scanner.nextLine();
        if (!next.equals("1")) {
            clearScreen();
            start();
        }

        System.out.println("You will now place your ships.");
        System.out.println("Please type in X and Y coordinates for each ship.");
        System.out.println("The X and Y coordinates must be between 0 and 9.");
        System.out.println("The X and Y coordinates must be separated by a space.");
        System.out.println("For example, to place a ship at (3, 4), type in 3 4.");
        System.out.println("You will be given 5 ships.");
        for (int i = 0; i < 5; i++) {
            System.out.println("Please type in the X and Y coordinates for ship " + (i + 1) + ".");
            String input = scanner.nextLine();
            String[] inputArray = input.split(" ");
            int x = Integer.parseInt(inputArray[0]);
            int y = Integer.parseInt(inputArray[1]);
            map[y][x].setShip();
            printMap();
        }
        generateComputerMap();

        System.out.println("You will now shoot at the computer's ships.");
        System.out.println("Please type in X and Y coordinates for each shot.");
        System.out.println("The X and Y coordinates must be between 0 and 9.");
        System.out.println("The X and Y coordinates must be separated by a space.");
        System.out.println("For example, to shoot at (3, 4), type in 3 4.");

        // First the player has a turn, then the computer has a turn.
        // repeat until one of the players has no ships left.

        int playerShips = 5, computerShips = 5;
        while (playerShips > 0 && computerShips > 0) {
            System.out.println("Please type in the X and Y coordinates for your shot.");
            String input = scanner.nextLine();
            String[] inputArray = input.split(" ");
            int x = Integer.parseInt(inputArray[0]);
            int y = Integer.parseInt(inputArray[1]);
            if (computerMap[y][x].isShip()) {
                System.out.println("You hit a ship!");
                computerShips--;
            } else {
                System.out.println("You missed!");
            }
            computerMap[y][x].setShotAt();
            System.out.println();
            System.out.println("COMPUTER MAP:");
            System.out.println();
            printComputerMap();
            System.out.println();
            System.out.println("RAW MAP: (for debugging)");
            System.out.println();
            printComputerRawMap();
            if (computerShips == 0) {
                System.out.println("You win!");
                break;
            }
            System.out.println("The computer is shooting at your ships.");
            int computerX = (int) (Math.random() * 10);
            int computerY = (int) (Math.random() * 10);
            if (map[computerY][computerX].isShip()) {
                System.out.println("The computer hit a ship!");
                playerShips--;
            } else {
                System.out.println("The computer missed!");
            }
            map[computerY][computerX].setShotAt();
            System.out.println();
            System.out.println("PLAYER MAP:");
            System.out.println();
            printMap();
            if (playerShips == 0) {
                System.out.println("You lose!");
                break;
            }
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

    public void generateComputerMap() {
        for (int i = 0; i < 5; i++) {
            int x = (int) (Math.random() * 10);
            int y = (int) (Math.random() * 10);
            computerMap[y][x].setShip();
        }
    }

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



    public void clearScreen() {
        System.out.println(System.lineSeparator().repeat(50));
    }
}
