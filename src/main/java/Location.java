import Fighters.Player;
import javax.swing.*;

/**
 * class representing the location in the game
 */
public class Location {
    Human human;
    int total_locations;
    int locations_counter= 1;

    int ememies_left;

    public Location( Human human, int total_locations) {
        this.human = human;
        this.total_locations = total_locations;
    }


    public int count_num_enemies() {
       int enemies = (int) (Math.random() * 3) + 2 + human.getLevel();
        return enemies;
    }


    public void Complete_location(){
        locations_counter++;
    }

    public boolean generate_new_enemy(JDialog location_cleared) {

        String out = human.getWin() + " " + ememies_left + " enemies left";
        System.out.println(out);

        if (ememies_left -1 == human.getWin()) {
            return true;
        }
        else{

            return false;}


}
}
