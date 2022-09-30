public class Grid {

    private boolean isPShip;
    private boolean isCShip;
    private boolean shotAt;
    private char state;

    public Grid() {
        isPShip = false;
        isCShip = false;
        shotAt = false;
        state = '-';
    }

    public void setState(char state) {
        this.state = state;
    }

    public char getState() {
        return state;
    }

    public void setShip() {
        isPShip = true;
        state = 'S';
    }

    public void setCShip() {
        isCShip = true;
        state = 'S';
    }

    public void setShotAt() {
        shotAt = true;
        state = 'X';
    }

    public boolean isPShip() {
        return isPShip;
    }

    public boolean isCShip() {
        return isCShip;
    }

    public boolean isShotAt() {
        return shotAt;
    }
}