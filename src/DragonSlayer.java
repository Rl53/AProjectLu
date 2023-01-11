import java.util.Scanner;

public class DragonSlayer {
    private static int topScore = 0;
    private Sword sword;
    private Player player;
    private Dragon dragon;
    private Room room;

    public DragonSlayer(){
        sword = null;
        dragon = null;
        room = null;
        player = null;
    }

    public void play() {

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
        System.out.println("Enter the corresponding letter to progress.");
        while (!(choice.equals("X") || choice.equals("x")))
        {
            System.out.println();
            System.out.println("(A)ttack the dragon.");
            System.out.println("(H)eal with a health pot.");
            System.out.println("(V)iew your top score.");
            System.out.println("(S)tart a new game.");
            System.out.println("Or give up and (Q)uit the game?");
            System.out.println();
            System.out.print("What's your decision?");
            choice = scanner.nextLine();
            processChoice(choice);
        }
    }

    private void processChoice(String choice)
    {
        if (choice.equals("A") || choice.equals("a"))
        {
            System.out.println(player.getName() + " attacks the dragon, dealing " + player.playerAtk() + " damage.");
            dragon.takeDamage(player.playerAtk());

        }
        else if (choice.equals("M") || choice.equals("m"))
        {
            if (currentTown.leaveTown())
            {
                //This town is going away so print its news ahead of time.
                System.out.println(currentTown.getLatestNews());
                enterTown();
            }
        }
        else if (choice.equals("L") || choice.equals("l"))
        {
            currentTown.lookForTrouble();
        }
        else if (choice.equals("H") || choice.equals("h")) {
            currentTown.huntForTreasure();
        }
        else if (choice.equals("X") || choice.equals("x"))
        {
            System.out.println("Fare thee well, " + hunter.getHunterName() + "!");
        }
        else
        {
            System.out.println("Yikes! That's an invalid option! Try again.");
        }
    }
}
