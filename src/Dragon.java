public class Dragon {
    private int health;
    private int lvl;

    public Dragon() {
        health = 100;
        lvl = (int) (Math.random() * 3) + 1;
    }

    public int dragonAtk() {
        return ((int) (Math.random() * 10) + 1) + lvl * 6;
    }

    public void takeDamage(int dmg) {
        health -= dmg;
    }

    public boolean isDead() {
        return (health <= 0);
    }

    public int getHealth() {
        return health;
    }

    public int getLvl() {
        return lvl;
    }
}
