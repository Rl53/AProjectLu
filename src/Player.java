public class Player {
    private String name;
    private int health;
    private int gold;
    private boolean healthPot;
    Sword sword = new Sword();

    public Player(String name) {
        health = 100;
        gold = 0;
        healthPot = false;
        this.name = name;
    }

    public int playerAtk() {
        return ((int) (Math.random() * 10) + 1) * sword.getPow();
    }

    public void takeDmg(int dmg) {
        health -= dmg;
    }

    public String getName() {
        return name;
    }

    public void getMoney(int money) {
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

}
