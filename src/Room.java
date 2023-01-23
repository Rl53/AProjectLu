public class Room {

    // instance variables
    private String name;
    private boolean searched;
    private Player player;

    // constructor
    public Room(String name, Player player) {
        this.name = name;
        searched = false;
        this.player = player;
    }

    // getter and setter methods
    public String getRoom() {
        return name;
    }

    public void setSearched(boolean searched) {
        this.searched = searched;
    }

    // gets item from unsearched room
    public void roomSearch() {
        String roomItem = "nothing";
        if (searched) {
            System.out.println("You have already searched here.");
        }
        else {
            if (Math.random() > 0.5) {
                roomItem = "a health pot";
            }
            System.out.println("You searched the room and found " + roomItem);
            if (roomItem.equals("a health pot")) {
                if (player.hasHPot()) {
                    System.out.println("But you already have a health pot, so you did not take it.");
                } else {
                    player.getHPot();
                }
            }
        }
        searched = true;
    }
}
