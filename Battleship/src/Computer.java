import java.util.Random;

public class Computer extends Player {

    private final Random random = new Random();

    public Computer(int mapSize) {
        super(mapSize);
    }

    public void generateShips(int ships) {
        for (int i = 0; i < ships; i++) {
            int x = random.nextInt(getMapSize());
            int y = random.nextInt(getMapSize());
            if (getMap()[y][x].isShip()) {
                i--;
            } else {
                getMap()[y][x].setShip();
            }
        }
    }

    public void makeMove(Player player) {
        int x = random.nextInt(getMapSize());
        int y = random.nextInt(getMapSize());
        if (player.getMap()[y][x].isShotAt()) {
            makeMove(player);
        } else {
            player.getMap()[y][x].setShotAt();
        }
    }
}