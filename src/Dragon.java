public class Dragon {

    // instance variables
    private int health;
    private int lvl;

    // constructor
    public Dragon() {
        health = 100;
        lvl = (int) (Math.random() * 3) + 1;
    }

    // getter and setter method
    public int getHealth() {
        return health;
    }

    public int getLvl() {
        return lvl;
    }

    public boolean isDead() {
        return (health <= 0);
    }

    public int dragonAtk() {
        return ((int) (Math.random() * 6) + 1) + lvl * 3;
    }

    public void takeDamage(int dmg) {
        health -= dmg;
    }
}
