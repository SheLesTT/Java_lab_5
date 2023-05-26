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
       int enemies = (int) (Math.random() * 3) + 1 + human.getLevel();
        return enemies;
    }


//    public Player NewEnemy(JLabel L1, JLabel L2,
//                           JLabel L3, JLabel L4, JProgressBar pr2) {
//        action.setEnemyes();
//        Player enemy = action.ChooseEnemy(L1, L2, L3, L4);
//        action.HP(enemy, pr2);
//        pr2.setMaximum(enemy.getMaxHealth());
//        return enemy;
//    }

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
