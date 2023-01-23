import java.util.Scanner;

public class DragonSlayer {
    private static int topScore = 0;
    private Sword sword;
    private Player player;
    private String name;
    private Dragon dragon;
    private Room room;
    private int roomsEntered;

    private boolean canLeave;

    public DragonSlayer(){
        sword = new Sword();
        dragon = new Dragon();
        player = new Player();
        room = new Room("den", player);
        roomsEntered = 1;
        canLeave = false;
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
        player.setName(name);
        System.out.println("Good luck exploring, " + name + ", and...try not to die here...");
    }
    private void showMenu()
    {
        int numDragons = 1;
        Scanner scanner = new Scanner(System.in);
        String choice = "";
        System.out.println("You are now in the " + room.getRoom());
        if (!dragon.isDead()) {
            System.out.println();
            System.out.println("A dragon spawns!");
        }
        System.out.println("Enter the corresponding letter to progress.");
        while (!(choice.equals("Q") || choice.equals("q"))) {
            while (roomsEntered <= 5) {
                System.out.println();
                System.out.println("Now what is your decision?");
                if (!dragon.isDead()) {
                    System.out.println("(A)ttack the dragon.");
                }
                System.out.println("(H)eal with a health pot.");
                System.out.println("(K)now your top score.");
                System.out.println("(S)tart a new game.");
                System.out.println("(L)ook around the room.");
                if (canLeave) {
                    System.out.println("(M)ove to the next room.");
                }
                System.out.println("Or give up and (Q)uit the game? ");
                System.out.println();
                choice = scanner.nextLine();
                processChoice(choice);
                while (numDragons < 3 && roomsEntered != 6) {
                    if (Math.random() >= 0.6) {
                        canLeave = false;
                        System.out.println();
                        System.out.println("Another dragon spawns!");
                        dragon = new Dragon();
                        System.out.println("Now what is your decision?");
                        System.out.println("(A)ttack the dragon.");
                        System.out.println("(H)eal with a health pot.");
                        System.out.println("(K)now your top score.");
                        System.out.println("(S)tart a new game.");
                        System.out.println("(L)ook around the room.");
                        if (canLeave) {
                            System.out.println("(M)ove to the next room.");
                        }
                        System.out.println("Or give up and (Q)uit the game? ");
                        System.out.println();
                        numDragons++;
                        choice = scanner.nextLine();
                        processChoice(choice);
                    } else {
                        numDragons = 3;
                    }
                }
                numDragons = 1;
            }
                System.out.println();
                System.out.println("Congratulations, you managed to clear all 5 rooms! ");
                int newScore = player.getMoney() * 10 + sword.getPow() * 5 + sword.getDodgeRate() * 3;
                System.out.println("You win! You achieved a highscore of " + newScore);
                updateScore(newScore);
                System.out.println("Enter (S) if you would like to play again.");
                choice = scanner.nextLine();
                if (choice.equals("S") || choice.equals("s")) {
                    restart();
                }
                else {
                    endGame();
                }
        }
    }

    public void endGame() {
        System.out.println("Farewell, until we meet again...");
        System.exit(0);
    }

    public void restart() {
        int newScore = player.getMoney() * 10 + sword.getPow() * 5 + sword.getDodgeRate() * 3;
        updateScore(newScore);
        System.out.println("Your highest score is " + topScore);
        player = new Player();
        dragon = new Dragon();
        sword = new Sword();
        room = new Room("den", player);
        roomsEntered = 1;
        canLeave = false;
        System.out.println();
        welcome();
        showMenu();
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
                else if (choice.equals("q") || choice.equals("Q")) {
                    endGame();
                }
                else if (choice.equals("A") || choice.equals("a")){
                    inFight();
                }
            }
            if (player.isDead()) {
                System.out.println("You lost all your health and died, better luck next time!");
                int newScore = player.getMoney() * 10 + sword.getPow() * 5 + sword.getDodgeRate() * 3;
                System.out.println("Your final score is " + newScore);
                updateScore(newScore);
                System.out.println("Do you want to play again? (Y)es or (N)o");
                choice = scanner.nextLine();
                if (choice.equals("y") || choice.equals("Y")) {
                    choice = "";
                    restart();
                }
                else {
                    System.out.println("See you next time!");
                    System.exit(0);
                }
            }
            else if (dragon.isDead()) {
                System.out.println("You defeated the dragon!");
                dragonReward();
                canLeave = true;
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
        if (choice.equals("L") || choice.equals("l")) {
            room.roomSearch();
        }
        if (choice.equals("K") || choice.equals("k")) {
            System.out.println("Your high score is " + topScore);
        }
        if (choice.equals("S") || choice.equals("s")) {
            restart();
        }
        if (choice.equals("q") || choice.equals("Q")) {
            endGame();
        }
        if (choice.equals("M") || choice.equals("m")) {
            if (canLeave) {
                System.out.println("You move to the next room.");
                canLeave = false;
                roomsEntered ++;
                room.setSearched(false);
                if (roomsEntered == 2) {
                    room = new Room("stronghold", player);
                }
                else if (roomsEntered == 3) {
                    room = new Room("hatchery", player);
                }
                else if (roomsEntered == 4) {
                    room = new Room("castle", player);
                }
                else if (roomsEntered == 5) {
                    room = new Room("dragon's lair", player);
                }
                else if (roomsEntered == 6) {
                    room = new Room("trophy room", player);
                }
                System.out.println("You have entered the " + room.getRoom());
                dragon = new Dragon();
            }
            else {
                System.out.println("You have to defeat the dragon first.");
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

    public void updateScore(int newScore) {
        if (topScore < newScore) {
            topScore = newScore;
        }
    }
}
