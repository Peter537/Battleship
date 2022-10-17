public class Grid {

    private boolean isShip;
    private boolean shotAt;
    private char state;
    private char rawState;

    public Grid() {
        isShip = false;
        shotAt = false;
        state = '-';
        rawState = state;
    }

    // Setters

    public void setShip() {
        isShip = true;
        state = 'S';
        rawState = state;
    }

    public void setShotAt() {
        shotAt = true;
        state = (isShip ? 'H' : 'M');
    }

    // Getters

    public char getState() {
        return state;
    }

    public char getRawState() {
        return rawState;
    }

    public boolean isShip() {
        return isShip;
    }

    public boolean isShotAt() {
        return shotAt;
    }
}