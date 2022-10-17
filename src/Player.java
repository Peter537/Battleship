public class Player {

    private final Grid[][] map;
    private final int mapSize;
    private final TextUI textUI;

    public Player(int mapSize, TextUI textUI) {
        this.mapSize = mapSize;
        this.textUI = textUI;
        this.map = new Grid[mapSize][mapSize];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = new Grid();
            }
        }
    }

    public void placeShips(TextUI textUI, int ships) {
        for (int i = 0; i < ships; i++) {
            textUI.clearScreen();
            this.printRawMap();
            textUI.print("Place ship " + (i + 1) + "/" + ships);
            try {
                int x = Integer.parseInt(textUI.getUserInputLine("X: ").trim());
                int y = Integer.parseInt(textUI.getUserInputLine("Y: ").trim());
                if (map[y][x].isShip()) {
                    textUI.print("There is already a ship there");
                    textUI.getUserInput("Press enter to continue");
                    i--;
                } else {
                    map[y][x].setShip();
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                textUI.print("Invalid input");
                textUI.getUserInput("Press enter to continue");
                i--;
            }
        }
    }

    public void printRawMap() {
        System.out.println("  0 1 2 3 4");
        for (int i = 0; i < map.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j].getRawState() + " ");
            }
            System.out.println();
        }
    }

    public void printMap() {
        System.out.println("  0 1 2 3 4");
        for (int i = 0; i < map.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j].isShotAt()) {
                    System.out.print(map[i][j].getState() + " ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }

    public boolean hasShipsLeft() {
        for (Grid[] grids : map) {
            for (Grid grid : grids) {
                if (grid.isShip() && !grid.isShotAt()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void makeMove(Player enemy) {
        textUI.clearScreen();
        enemy.printMap();
        textUI.print("Make a shot");
        try {
            int x = Integer.parseInt(textUI.getUserInputLine("X: ").trim());
            int y = Integer.parseInt(textUI.getUserInputLine("Y: ").trim());
            if (enemy.getMap()[y][x].isShotAt()) {
                textUI.print("You already shot there");
                textUI.getUserInput("Press enter to continue");
                makeMove(enemy);
            } else {
                enemy.getMap()[y][x].setShotAt();
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            textUI.print("Invalid input");
            textUI.getUserInput("Press enter to continue");
            makeMove(enemy);
        }
    }

    public Grid[][] getMap() {
        return map;
    }

    protected int getMapSize() {
        return mapSize;
    }

    public TextUI getTextUI() {
        return textUI;
    }
}
