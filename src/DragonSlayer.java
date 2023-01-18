import java.util.Scanner;

public class DragonSlayer {
    private static int topScore = 0;
    private Sword sword;
    private Player player;
    private Dragon dragon;
    private Room room;

    public DragonSlayer(){
        sword = new Sword();
        dragon = new Dragon();
        room = new Room("the den");
        player = null;
    }

    public void play() {
        welcome();
        showMenu();
    }

    private void welcome() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to Dragon Slayer!");
        System.out.println("Your objective is to clear all 5 rooms by defeating the dragons in the rooms.");
        System.out.print("What's your name? ");
        String name = scan.nextLine();
        player = new Player(name);
        System.out.println("Good luck exploring, " + name + ", and...try not to die here...");
    }
    private void showMenu()
    {
        Scanner scanner = new Scanner(System.in);
        String choice = "";
        if (!dragon.isDead()) {
            System.out.println();
            System.out.println("A dragon spawns!");
        }
        System.out.println("Enter the corresponding letter to progress.");
        while (!(choice.equals("X") || choice.equals("x")))
        {
            System.out.println();
            if (!dragon.isDead()) {
                System.out.println("(A)ttack the dragon.");
            }
            System.out.println("(H)eal with a health pot.");
            System.out.println("(K)now your top score.");
            System.out.println("(S)tart a new game.");
            System.out.println("(L)ook around the room.");
            System.out.println("Or give up and (Q)uit the game?");
            System.out.println();
            System.out.print("What's your decision? ");
            choice = scanner.nextLine();
            processChoice(choice);
        }
    }
    private void inFight() {
            int playerAtk = player.playerAtk();
            System.out.println(player.getName() + " attacks the dragon, dealing " + playerAtk + " damage.");
            dragon.takeDamage(playerAtk);
            System.out.println("The dragon attacks back!");
            if (0.01 * sword.getDodgeRate() >= Math.random()) {
                System.out.println("However, you managed to dodge and lost 0 health.");
            } else {
                int dragonAtk = dragon.dragonAtk();
                System.out.println("The dragon attacks you, dealing " + dragonAtk + " damage.");
                player.takeDmg(dragonAtk);
            }
    }

    private void processChoice(String choice)
    {
        Scanner scanner = new Scanner(System.in);
        if (choice.equals("A") || choice.equals("a"))
        {
            inFight();
            while (!dragon.isDead() && !player.isDead()) {
                System.out.println();
                System.out.println("What do you want do now?");
                System.out.println("(A)ttack the dragon again.");
                System.out.println("(H)eal using a health pot.");
                System.out.println("(V)iew the player and weapon's stats.");
                System.out.println("(I)nspect the dragon's stats.");
                System.out.println("Or give up and (Q)uit the game?");
                System.out.println();
                System.out.print("What's your decision? ");
                choice = scanner.nextLine();
                if (choice.equals("H") || choice.equals("h")) {
                    if (player.hasHPot()) {
                        player.useHPot();
                        System.out.println("You used your health potion and healed half of your health.");
                    } else {
                        System.out.println("You have no health pot.");
                    }
                }
                else if (choice.equals("V") || choice.equals("v")) {
                    System.out.println();
                    System.out.println("You have " + player.getHealth() + " HP.");
                    System.out.println("You have " + player.getMoney() + " gold coins.");
                    if (player.hasHPot()) {
                        System.out.println("You have 1 health pot.");
                    } else {
                        System.out.println("You do not have any health pot.");
                    }
                    System.out.println("Your weapon has: ");
                    System.out.println(sword.getPow() + " attack power");
                    System.out.println(sword.getDodgeRate() + " dodge rate");
                }
                else if (choice.equals("I") || choice.equals("i")) {
                    System.out.println("This dragon is level " + dragon.getLvl() + " and has " + dragon.getHealth() + " health remaining.");
                }
                else {
                    inFight();
                }
            }
            if (player.isDead()) {
                System.out.println("You lost all your health and died, better luck next time!");
                choice = "";
                System.exit(0);
            }
            else if (dragon.isDead()) {
                System.out.println("You defeated the dragon!");
                dragonReward();
            }
       }
        if (choice.equals("H") || choice.equals("h")) {
            if (player.hasHPot()) {
                player.useHPot();
                System.out.println("You used your health potion and healed half of your health.");
            }
            else {
                System.out.println("You have no health pot.");
            }
        }

    }
        public void dragonReward() {
            double random = Math.random();
            int gold = (int) (Math.random() * 76) + 25;
            if (random <= 0.25) {
                System.out.println("The dragon drops " + gold + " gold coins, which you collect.");
                player.setMoney(gold);
                System.out.println("You now have " + player.getMoney() + " gold coins.");
            }
            else if (random <= 0.5) {
                System.out.println("The dragon drops an upgrade for the sword.");
                System.out.println("Your sword gains 10 attack and 15 dodge rating.");
                sword.moreAtk(10);
                sword.higherDodge(15);
            }
            else if (random <= 0.75) {
                System.out.println("You feel more confident, and healed yourself.");
                player.heal();
                System.out.println("You have " + player.getHealth() + " health.");
            }
            else {
                System.out.println("You found nothing and left empty-handed.");
            }
    }
}
