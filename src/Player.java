public class Player {
    private String name;
    private int health;
    private int gold;
    private boolean healthPot;
    Sword sword = new Sword();

    public Player() {
        health = 1000;
        gold = 0;
        healthPot = false;
        name = "";
    }

    public int playerAtk() {
        return ((int) (0.3 * (Math.random() * 10) + 1)) * sword.getPow();
    }
    public void setName(String newName) {
        name = newName;
    }

    public void takeDmg(int dmg) {
        health -= dmg;
    }

    public String getName() {
        return name;
    }

    public void setMoney(int money) {
        gold += money;
    }

    public void heal() {
        health += (int) ((100 - health) * 0.25);
    }

    public void getHPot() {
        healthPot = true;
    }

    public void useHPot() {
        health += (int) ((100 - health) * 0.5);
        healthPot = false;
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
}
