import java.util.Scanner;
public class DragonSlayer {

    // instance variables
    private static int topScore = 0;
    private Sword sword;
    private Player player;
    private Dragon dragon;
    private Room room;
    private int roomsEntered;
    private int numDragons;

    private boolean canLeave;

    // constructor
    public DragonSlayer(){
        sword = new Sword();
        dragon = new Dragon();
        player = new Player();
        room = new Room("den", player);
        roomsEntered = 1;
        canLeave = false;
        numDragons = 1;
    }

    // method that starts the game
    public void play() {
        welcome();
        defaultMenu();
    }

    // introduces the player
    private void welcome() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to Dragon Slayer!");
        System.out.println("Your objective is to clear all 5 rooms by defeating the dragons in the rooms.");
        System.out.print("What's your name? ");
        String name = scan.nextLine();
        player.setName(name);
        System.out.println("Good luck exploring, " + name + ", and...try not to die here...");
    }

    /* Main menu that provides general information on current available actions,
       generates a random amount of dragon, and determines end conditions */
    private void defaultMenu()
    {
        Scanner scanner = new Scanner(System.in);
        String choice = "";
        System.out.println("You are now in the " + room.getRoom());
        if (!dragon.isDead()) {
            System.out.println();
            System.out.println("A dragon spawns!");
        }
        System.out.println("Enter the corresponding letter to progress.");
        while (!(choice.equals("Q") || choice.equals("q"))) {
            while (roomsEntered < 5) {
                while (!(choice.equals("A") || choice.equals("a"))) {
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
                }
                numDragons = 1;
                // creates 1-3 dragons per room
                while (numDragons < 3 && roomsEntered != 6) {
                    if (Math.random() >= 0.6) {
                        numDragons++;
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
                        choice = scanner.nextLine();
                        processChoice(choice);
                    } else {
                        numDragons = 3;
                    }
                    choice = "";
                }
            }
                // end conditions
                System.out.println();
                System.out.println("Congratulations, " + player.getName() + ", you managed to clear all 5 rooms! ");
                int newScore = player.getMoney() * 15 + sword.getPow() * 10 + sword.getDodgeRate() * 7 + player.getHealth() * 4;
                System.out.println("You win! You achieved a final score of " + newScore);
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

    // generates current actions during a fight
    public void processChoice(String choice)
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
                        System.out.println("You used your health potion and healed half of your remaining health.");
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

            // determines if end conditions are met
            if (player.isDead()) {
                System.out.println("You lost all your health and died, better luck next time!");
                int newScore = player.getMoney() * 10 + sword.getPow() * 7 + sword.getDodgeRate() * 5;
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
                String reward = dragon.getReward();
                // calculates the four possible drops of defeating a dragon
                    int gold = (int) (Math.random() * 76) + 25;
                    if (reward.equals("gold")) {
                        System.out.println("The dragon drops " + gold + " gold coins, which you collect.");
                        player.setMoney(gold);
                        System.out.println("You now have " + player.getMoney() + " gold coins.");
                    }
                    else if (reward.equals("upgrade")) {
                        System.out.println("The dragon drops an upgrade for the sword.");
                        System.out.println("Your sword gains 10 attack and 15 dodge rating.");
                        sword.moreAtk(10);
                        sword.higherDodge(15);
                    }
                    else if (reward.equals("heal")) {
                        System.out.println("You feel more confident, and healed yourself.");
                        player.heal();
                        System.out.println("You have " + player.getHealth() + " health.");
                    }
                    else {
                        System.out.println("You found nothing and left empty-handed.");
                    }
                canLeave = true;
            }
       }
        if (choice.equals("H") || choice.equals("h")) {
            if (player.hasHPot()) {
                player.useHPot();
                System.out.println("You used your health potion and healed half of your remaining health.");
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

        // sets the name of the rooms
        if (choice.equals("M") || choice.equals("m")) {
            if (canLeave) {
                System.out.println("You move to the next room.");
                canLeave = false;
                roomsEntered ++;
                room.setSearched(false);
                numDragons++;
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
                System.out.println("You have entered the " + room.getRoom());
                dragon = new Dragon();
            }
            else {
                System.out.println("You have to defeat the dragon first.");
            }
        }

    }

    // calculates damage taken by the dragon and the player
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

    private void updateScore(int newScore) {
        if (topScore < newScore) {
            topScore = newScore;
        }
    }

    // resets the values and restarts the game
    public void restart() {
        System.out.println("Your highest score is " + topScore);
        player = new Player();
        dragon = new Dragon();
        sword = new Sword();
        room = new Room("den", player);
        roomsEntered = 1;
        canLeave = false;
        System.out.println();
        welcome();
        defaultMenu();
    }

    private void endGame() {
        System.out.println("Farewell, until we meet again...");
        System.exit(0);
    }

}
