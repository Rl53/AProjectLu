public class Player {

    // instance variables
    private String name;
    private int health;
    private int gold;
    private boolean healthPot;
    Sword sword = new Sword();

    // constructor
    public Player() {
        health = 100;
        gold = 0;
        healthPot = false;
        name = "";
    }

    // getter and setter methods
    public String getName() {
        return name;
    }

    public void getHPot() {
        healthPot = true;
    }

    public boolean isDead() {
        return (health <= 0);
    }

    public int getHealth() {
        return health;
    }

    public int getMoney() {
        return gold;
    }

    public boolean hasHPot() {
        return healthPot;
    }

    public int playerAtk() {
        return ((int) (0.3 * (Math.random() * 10) + 1)) * sword.getPow();
    }

    public void setName(String newName) {
        name = newName;
    }

    public void setMoney(int money) {
        gold += money;
    }

    public void takeDmg(int dmg) {
        health -= dmg;
    }

    public void heal() {
        health += (int) ((100 - health) * 0.25);
    }

    // uses health pot, which heals half of the remaining health of the player
    public void useHPot() {
        health += (int) ((100 - health) * 0.5);
        healthPot = false;
    }


}
