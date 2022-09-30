public class Grid {

    private boolean isShip;
    private boolean shotAt;
    private char state;

    public Grid() {
        isShip = false;
        shotAt = false;
        state = '-';
    }

    public char getState() {
        return state;
    }

    public void setShip() {
        isShip = true;
        state = 'S';
    }


    public void setShotAt() {
        shotAt = true;
        state = 'X';
    }

    public boolean isShip() {
        return isShip;
    }

    public boolean isShotAt() {
        return shotAt;
    }
}