public class Sword {
    private int atkPow;
    private int dodgeRate;

    public Sword() {
        atkPow = 10;
        dodgeRate = 20;
    }

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
