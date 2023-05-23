import javax.swing.*;

public class Location {
    Human human;
    int total_locations;
    int locations_counter= 1;
    CharacterAction action = new CharacterAction();

    int ememies_left;



    public Location( Human human, int total_locations) {
        this.human = human;
        this.total_locations = total_locations;


    }

    Player[] enemies;

    public int count_num_enemies() {
//        this.ememies_left = (int) (Math.random() * 3) + 2 + human.getLevel();
        return 2;
    }

    public void EnemyKilled(){
        ememies_left --;
    }
    public Player NewEnemy(JLabel L1, JLabel L2,
                           JLabel L3, JLabel L4, JProgressBar pr2) {
        action.setEnemyes();
        Player enemy = action.ChooseEnemy(L1, L2, L3, L4);
        action.HP(enemy, pr2);
        pr2.setMaximum(enemy.getMaxHealth());
        return enemy;
    }

    public void Complete_location(){
        locations_counter++;
    }

    public boolean generate_new_enemy(JDialog location_cleared) {

        String out = human.getWin() + " " + ememies_left + " enemies left";
        System.out.println(out);

        if (ememies_left -1 == human.getWin()) {
//            ememies_left--;
            return true;
        }
        else{
//            ememies_left--;
            return false;}


}
}
