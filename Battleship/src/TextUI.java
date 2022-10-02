import java.util.Scanner;

public class TextUI {

    private final Scanner scanner = new Scanner(System.in);

    public TextUI() {}

    public String getUserInput(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    public String getUserInputLine(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public String getUserInput() {
        return scanner.nextLine();
    }

    public void print(String s) {
        System.out.println(s);
    }

    public String getWelcomeText() {
        StringBuilder sb = new StringBuilder();
        sb.append("Welcome to BattleShip!\n");
        sb.append("You will be playing against the computer.\n");
        sb.append("You will be given 5 ships.\n");
        sb.append("\n");
        sb.append("Press 1 to start the game.\n");
        return sb.toString();
    }



    public String getPlaceShipText() {
        StringBuilder sb = new StringBuilder();
        sb.append("You will now place your ships.\n");
        sb.append("Please type in X and Y coordinates for each ship.\n");
        sb.append("The X and Y coordinates must be between 0 and 9.\n");
        sb.append("The X and Y coordinates must be separated by a space.\n");
        sb.append("For example, to place a ship at (3, 4), type in 3 4.\n");
        sb.append("You will be given 5 ships.\n");
        return sb.toString();
    }

    public String getGameText() {
        StringBuilder sb = new StringBuilder();
        sb.append("You will now shoot at the computer's ships.\n");
        sb.append("Please type in X and Y coordinates for each shot.\n");
        sb.append("The X and Y coordinates must be between 0 and 9.\n");
        sb.append("The X and Y coordinates must be separated by a space.\n");
        sb.append("For example, to shoot at (3, 4), type in 3 4.\n");
        return sb.toString();
    }


    public void clearScreen() {
        System.out.println(System.lineSeparator().repeat(50));
    }

}
