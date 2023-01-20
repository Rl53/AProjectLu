public class Dragon {
    private int health;
    private int lvl;

    public Dragon() {
        health = 100;
        lvl = (int) (Math.random() * 3) + 1;
    }

    public int dragonAtk() {
        return ((int) (Math.random() * 6) + 1) + lvl * 3;
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
