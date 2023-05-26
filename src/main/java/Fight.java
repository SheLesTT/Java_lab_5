/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//ADD IMAGE!!!
import Fighters.Player;
import Fabrics.EnemyFabric;
import Fighters.ShaoKahn;

import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;

/**
 * This class represents a game round and contains methods related to player movement, attacks, and round completion.
 * @author Мария
 *
 */
public class Fight {

    ChangeTexts change = new ChangeTexts();
    int kind_attack[] = {0};
    int experiences[] = {40, 90, 180, 260, 410};
    EnemyFabric fabric = new EnemyFabric();
    int i = 1;
    int k = -1;
    int stun = 0;
    double heal_proba = 0.2;

    Optimizer adam = new Optimizer(); // no gradient decent today :(
    double v = 0.0;


    /**
     * Moves the players, determines the outcome of their attacks, and updates the game interface accordingly.
     * @param p1 The first player.
     * @param p2 The second player.
     * @param l The label for displaying information about player movement.
     * @param l2 The label for displaying game status information.
     */
    public void Move(Player p1, Player p2, JLabel l, JLabel l2) {

        if (stun == 1) {
            p1.setAttack(-1);
        }
        if (p1.getAttack() == 2) {
            if (p2.getAttack() == 0) {
                p1.setHealth((p1.getMaxHealth() - p1.getHealth()) / 2);
                l2.setText("Regeneration complited");
            } else {
                p1.setHealth(-(int) (p2.getDamage() * 1.5));
                l2.setText(("Regeneration interapted "));
            }

        } else if (p2.getAttack() == 2) {

            if (p1.getAttack() == 0) {
                p2.setHealth((p2.getMaxHealth() - p2.getHealth()) / 2);
                l2.setText("Regeneration complited");
            } else {
                p2.setHealth(-(int) (p1.getDamage() * 1.5));
                l2.setText(("Regeneration interapted "));
            }
        } else {
            switch (Integer.toString(p1.getAttack()) + Integer.toString(p2.getAttack())) {
                case "10":
                    v = Math.random();
                    if (p1 instanceof ShaoKahn & v < 0.15) {
                        p2.setHealth(-(int) (p1.getDamage() * 0.5));
                        l2.setText("Your block is broken");

                    } else {
                        p1.setHealth(-(int) (p2.getDamage() * 0.5));
                        l2.setText(p2.getName() + " counterattacked");
                    }
                    break;
                case "11":
                    p2.setHealth(-p1.getDamage());
                    l2.setText(p1.getName() + " attacked");
                    break;
                case "00":
                    v = Math.random();
                    if (v <= 0.5) {
                        stun = 1;
                    }
                    l2.setText("Both defended themselves");
                    break;
                case "01":
                    l2.setText(p1.getName() + " didn't attacked");
                    break;
                case "-10":
                    l.setText(p1.getName() + " was stunned");
                    stun = 0;
                    l2.setText(p2.getName() + " didn't attacked");
                    break;
                case "-11":
                    p1.setHealth(-p2.getDamage());
                    l.setText(p1.getName() + " was stunned");
                    stun = 0;
                    l2.setText(p2.getName() + " attacked");
                    break;
            }
        }
    }
/**
 * Performs the hit action between players, updates their health, and handles end-of-round conditions.
 * @param human The human player.
 * @param enemy The enemy player.
 * @param a The attack type.
 * @param label The label for displaying information about the hit action.
 * @param label2 The label for displaying game status information.
 * @param dialog The dialog window for displaying round results.
 * @param label3 The label for displaying additional round information.
 * @param action The character action object.
 * @param pr1 The progress bar for displaying the human player's health.
 * @param pr2 The progress bar for displaying the enemy player's health.
 * @param dialog1 The dialog window for displaying the final round result (top score).
 * @param dialog2 The dialog window for displaying the final round result (not top score).
 * @param frame The main game frame.
 * @param results The list of results from previous rounds.
 * @param label4 The label for displaying the result of the final round (top score).
 * @param label5 The label for displaying the result of the final round (not top score).
 * @param label6 The label for displaying the round number.
 * @param label7 The label for displaying player movement information.
 * @param label8 The label for displaying additional game status information.
 * @param items The array of items in the game.
 * @param rb The radio button for selecting an item.
 * @param location The current game location.
 * @param location_num The label for displaying the current location number.
 * @param lvl_up_dialog The dialog window for displaying level up information.
 */

    public void Hit(Player human, Player enemy, int a, JLabel label,
            JLabel label2, JDialog dialog, JLabel label3, CharacterAction action,
            JProgressBar pr1, JProgressBar pr2, JDialog dialog1,
            JDialog dialog2, JFrame frame, ArrayList<Result> results,
            JLabel label4, JLabel label5, JLabel label6, JLabel label7,
            JLabel label8, Items[] items, JRadioButton rb,Location location, JLabel location_num,
                    JDialog lvl_up_dialog) {
        label7.setText("");
        human.setAttack(a);
        adam.AddMoveStats(a);
        adam.setLast_move(a);
        if (k < kind_attack.length - 1) {
            k++;
        } else {
            kind_attack = action.ChooseBehavior(enemy, action);
            k = 0;
        }

        enemy.setAttack(adam.OptimizeAttack(kind_attack[k]));
        if(enemy instanceof ShaoKahn){
            if (Math.random() < heal_proba){
                enemy.setAttack(2);
            }

        }
        if (i % 2 == 1) {
            Move(human, enemy, label7, label8);
        } else {
            Move(enemy, human, label7, label8);
        }
        i++;
        change.RoundTexts(human, enemy, label, label2, i, label6);
        action.HP(human, pr1);
        action.HP(enemy, pr2);
        if (human.getHealth() <= 0 & items[2].getCount() > 0) {
            human.setNewHealth((int) (human.getMaxHealth() * 0.05));
            items[2].setCount(-1);
            action.HP(human, pr1);
            label2.setText(human.getHealth() + "/" + human.getMaxHealth());
            rb.setText(items[2].getName() + ", " + items[2].getCount() + " шт");
            label7.setText("Вы воскресли");
        }

        if (human.getHealth() <= 0){
            EndFinalRound(((Human) human), action, results, dialog1, dialog2,
                    frame, label4, label5, lvl_up_dialog);
        }
        if ( enemy.getHealth() <= 0) {

            if(enemy instanceof ShaoKahn) {
                location.Complete_location();
                location_num.setText("Location " + location.locations_counter);
                System.out.println(location.total_locations + "this is a locations left");
                if(location.total_locations + 1 == location.locations_counter){
                    EndFinalRound(((Human) human), action, results, dialog1, dialog2,
                            frame, label4, label5, lvl_up_dialog);

                }

            }

//                System.out.println("this is an end");
//                System.out.println(enemy.getClass() + " in Hit ");
                EndRound(human, enemy, dialog, label3, action, items, location,lvl_up_dialog);
            }
        }

    /**
     * Ends the current round and displays the result.
     * @param human The human player.
     * @param enemy The enemy player.
     * @param dialog The dialog window for displaying the round result.
     * @param label The label for displaying the round result.
     * @param action The character action object.
     * @param items The array of items in the game.
     * @param location The current game location.
     * @param lvl_up_dialog The dialog window for displaying level up information.
     */


    public void EndRound(Player human, Player enemy, JDialog dialog, JLabel label,
            CharacterAction action, Items[] items,Location location, JDialog lvl_up_dialog) {

        dialog.setVisible(true);
        dialog.setBounds(300, 150, 700, 600);
        if (human.getHealth() > 0) {
            label.setText("You win");
            ((Human) human).setWin();

            if (enemy instanceof ShaoKahn) {


                ((Human) human).resetWin();

                if(location.total_locations == 0){

                }
//                location.New_Location();


                action.AddItems(38, 23, 8, items);
                action.AddPointsBoss(((Human) human), action.getEnemyes());
            } else {
                action.AddItems(25, 15, 5, items);
                action.AddPoints(((Human) human), action.getEnemyes(), lvl_up_dialog);
            }
        } else {
            label.setText(enemy.getName() + " win");
        }

        i = 1;
        k = -1;
        kind_attack = ResetAttack();

    }

    /**
     * Ends the game  and displays the finalresult.
     * @param human The human player.
     * @param action The character action object.
     * @param results The list of results from previous rounds.
     * @param dialog1 The dialog window for displaying the final round result (top score).
     * @param dialog2 The dialog window for displaying the final round result (not top score).
     * @param frame The main game frame.
     * @param label1 The label for displaying the result of the final round (top score).
     * @param label2 The label for displaying the result of the final round (not top score).
     * @param lvl_up_dialog The dialog window for displaying level up information.
     */
    public void EndFinalRound(Human human, CharacterAction action,
            ArrayList<Result> results, JDialog dialog1, JDialog dialog2, JFrame frame,
            JLabel label1, JLabel label2, JDialog lvl_up_dialog) {
        String text = "Победа не на вашей стороне";
        if (human.getHealth() > 0) {
            human.setWin();
            action.AddPoints(human, action.getEnemyes(), lvl_up_dialog);
            text = "Победа на вашей стороне";
        }
        boolean top = false;
        if (results == null) {
            top = true;
        } else {
            int i = 0;
            for (int j = 0; j < results.size(); j++) {
                if (human.getPoints() < results.get(j).getPoints()) {
                    i++;
                }
            }
            if (i < 10) {
                top = true;
            }
        }
        if (top) {
            dialog1.setVisible(true);
            dialog1.setBounds(150, 150, 600, 500);
            label1.setText(text);
        } else {
            dialog2.setVisible(true);
            dialog2.setBounds(150, 150, 470, 360);
            label2.setText(text);
        }
        frame.dispose();
    }

    public int[] ResetAttack() {
        int a[] = {0};
        return a;
    }

    /** Starts a fight with new enemy
     *
     * @param human
     * @param label
     * @param pr1
     * @param pr2
     * @param label2
     * @param text
     * @param label3
     * @param action
     * @param is_boss
     * @return
     */
    public Player NewRound(Player human, JLabel label, JProgressBar pr1,
            JProgressBar pr2, JLabel label2, JLabel text, JLabel label3, CharacterAction action, boolean is_boss) {

        Player enemy1 = null;
        if (is_boss) {
            System.out.println("Shoosing BOss");
            enemy1 = action.ChooseBoss(label, label2, text, label3, human.getLevel());
        } else {
            enemy1 = action.ChooseEnemy(label, label2, text, label3);
        }
        pr1.setMaximum(human.getMaxHealth());
        pr2.setMaximum(enemy1.getMaxHealth());
        human.setNewHealth(human.getMaxHealth());
        enemy1.setNewHealth(enemy1.getMaxHealth());
        action.HP(human, pr1);
        action.HP(enemy1, pr2);

        return enemy1;
    }

}
