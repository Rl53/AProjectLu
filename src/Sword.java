public class Sword {

    // instance variables
    private int atkPow;
    private int dodgeRate;

    // constructor
    public Sword() {
        atkPow = 10;
        dodgeRate = 20;
    }

    // getter and setter methods
    public int getPow() {
        return atkPow;
    }

    public int getDodgeRate() {
        return dodgeRate;
    }

    public void moreAtk(int pow) {
        atkPow += pow;
    }

    public void higherDodge(int dodge) {
        dodgeRate += dodge;
    }
}
