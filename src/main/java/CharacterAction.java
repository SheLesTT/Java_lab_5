/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import Fighters.Baraka;
import Fighters.Player;
import Fabrics.EnemyFabric;
import Fighters.LiuKang;
import Fighters.ShaoKahn;
import Fighters.SonyaBlade;
import Fighters.SubZero;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 * The CharacterAction class represents the actions and behaviors of characters in a game.
 * It contains methods for setting enemies, choosing enemies and bosses, handling enemy behaviors,
 * managing health points, leveling up, adding items, and using items.*
 * @author Мария
 */
public class CharacterAction {

    private final int experience_for_next_level[] = {40, 90, 180, 260, 410, 1000};

    private final int kind_fight[][] = {{1, 0}, {1, 1, 0}, {0, 1, 0}, {1, 1, 1, 1}};

    private Player enemyes[] = new Player[6];

    EnemyFabric fabric = new EnemyFabric();

    private Player enemyy = null;
    /**
     * Sets the array of enemies using the EnemyFabric
     */
    public void setEnemyes() {
        enemyes[0] = fabric.create(0, 0);
        enemyes[1] = fabric.create(1, 0);
        enemyes[2] = fabric.create(2, 0);
        enemyes[3] = fabric.create(3, 0);
        enemyes[4] = fabric.create(4, 0);
        enemyes[5] = fabric.create(4, 0);
    }
    /**
     * Returns the array of enemies.
     * @return The array of enemies.
     */
    public Player[] getEnemyes() {
        return this.enemyes;
    }
    /**
     * Chooses a random enemy and updates the UI labels and icons accordingly.
     * @param label The label to display the enemy's icon.
     * @param label2 The label to display the enemy's name.
     * @param text The label to display the enemy's damage.
     * @param label3 The label to display the enemy's health.
     * @return The chosen enemy.
     */
    public Player ChooseEnemy(JLabel label, JLabel label2, JLabel text, JLabel label3) {
        int i = (int) (Math.random() * 4);
        ImageIcon icon1 = null;
        System.out.println(i);
        switch (i) {
            case 0:
                enemyy = enemyes[0];
                icon1 = new ImageIcon(".\\resources\\Baraka.png");
                label2.setText("Baraka (танк)");
                break;
            case 1:
                enemyy = enemyes[1];
                icon1 = new ImageIcon(".\\resources\\Sub_Zero.png");
                label2.setText("Sub-Zero (маг)");
                break;
            case 2:
                enemyy = enemyes[2];
                icon1 = new ImageIcon(".\\resources\\LiuKang.png");
                label2.setText("Liu Kang (боец)");
                break;
            case 3:
                enemyy = enemyes[3];
                icon1 = new ImageIcon(".\\resources\\Sonia_blade.png");
                label2.setText("Sonya Blade (солдат)");
                break;
        }
        label.setIcon(icon1);
        text.setText(Integer.toString(enemyy.getDamage()));
        label3.setText(Integer.toString(enemyy.getHealth()) + "/" + Integer.toString(enemyy.getMaxHealth()));
        return enemyy;
    }
    /**
     * Chooses a boss enemy and updates the UI labels and icons accordingly.
     * @param label The label to display the enemy's icon.
     * @param label2 The label to display the enemy's name.
     * @param text The label to display the enemy's damage.
     * @param label3 The label to display the enemy's health.
     * @param i The index of the chosen boss.
     * @return The chosen boss enemy.
     */
    public Player ChooseBoss(JLabel label, JLabel label2, JLabel text, JLabel label3, int i) {
        ImageIcon icon1 = null;
        icon1 = new ImageIcon(".\\resources\\Shaokahn.jpg");
        label2.setText("Shao Kahn (босс)");
//        switch (i) {
//            case 2:
//        enemyy = enemyes[4];
//                break;
//            case 4:
        enemyy = enemyes[5];
//                break;
//        }
        label.setIcon(icon1);
        text.setText(Integer.toString(enemyy.getDamage()));
        label3.setText(Integer.toString(enemyy.getHealth()) + "/" + Integer.toString(enemyy.getMaxHealth()));
        return enemyy;
    }
    /**
     * Determines the behavior of the enemy based on the provided parameters.
     * @param k1 The weight for the first behavior type.
     * @param k2 The weight for the second behavior type.
     * @param k3 The weight for the third behavior type.
     * @param k4 The weight for the fourth behavior type.
     * @param i The random value to determine the behavior.
     * @return An array representing the chosen behavior.
     */
    public int[] EnemyBehavior(int k1, int k2, int k3, int k4, double i) {
        int arr[] = null;
        if (i < k1 * 0.01) {
            arr = kind_fight[0];
        }
        if (i >= k1 * 0.01 & i < (k1 + k2) * 0.01) {
            arr = kind_fight[1];
        }
        if (i >= (k1 + k2) * 0.01 & i < (k1 + k2 + k3) * 0.01) {
            arr = kind_fight[2];
        }
        if (i >= (k1 + k2 + k3) * 0.01 & i < 1) {
            arr = kind_fight[3];
        }
        return arr;
    }
    /**
     * Chooses the behavior for the given enemy.
     * @param enemy The enemy for which to choose the behavior.
     * @param action The CharacterAction instance.
     * @return An array representing the chosen behavior.
     */
    public int[] ChooseBehavior(Player enemy, CharacterAction action) {
        int arr[] = null;
        double i = Math.random();
        if (enemy instanceof Baraka) {
            arr = action.EnemyBehavior(15, 15, 60, 10, i);
        }
        if (enemy instanceof SubZero) {
            arr = action.EnemyBehavior(25, 25, 0, 50, i);
        }
        if (enemy instanceof LiuKang) {
            arr = action.EnemyBehavior(13, 13, 10, 64, i);
        }
        if (enemy instanceof SonyaBlade) {
            arr = action.EnemyBehavior(25, 25, 50, 0, i);
        }
        if (enemy instanceof ShaoKahn) {
            arr = action.EnemyBehavior(10, 45, 0, 45, i);
        }
        return arr;
    }
    /**
     * Updates the health progress bar for the given player.
     * @param player The player for which to update the health.
     * @param progress The progress bar to update.
     */
    public void HP(Player player, JProgressBar progress) {

        if (player.getHealth() >= 0) {
            progress.setValue(player.getHealth());
        } else {
            progress.setValue(0);
        }
    }
    /**
     * Adds experience points and calculates the level up for the human player.
     * @param human The human player.
     * @param enemyes The array of enemies.
     * @param lvl_up_dialog The dialog for level up notification.
     */
    public void AddPoints(Human human, Player[] enemyes, JDialog lvl_up_dialog) {
        switch (human.getLevel()) {
            case 0:
                human.setExperience(20);
                human.setPoints(25 + human.getHealth() / 4);
                break;
            case 1:
                human.setExperience(25);
                human.setPoints(30 + human.getHealth() / 4);
                break;
            case 2:
                human.setExperience(30);
                human.setPoints(35 + human.getHealth() / 4);
                break;
            case 3:
                human.setExperience(40);
                human.setPoints(45 + human.getHealth() / 4);
                break;
            case 4:
                human.setExperience(50);
                human.setPoints(55 + human.getHealth() / 4);
                break;
        }
        for (int i = 0; i < 5; i++) {
            if (experience_for_next_level[i] == human.getExperience()) {
                lvl_up_dialog.setVisible(true);
                lvl_up_dialog.setSize(300,400);

                human.setLevel();
                human.setNextExperience(experience_for_next_level[i + 1]);
                NewHealthHuman(human);
                for (int j = 0; j < 4; j++) {
                    NewHealthEnemy(enemyes[j], human);
                }
            }
        }
    }
    /**
     * Adds experience points and calculates the level up for the human player defeating a boss.
     * @param human The human player.
     * @param enemyes The array of enemies.
     */
    public void AddPointsBoss(Human human, Player[] enemyes) {
        switch (human.getLevel()) {
            case 2:
                human.setExperience(30);
                human.setPoints(45 + human.getHealth() / 2);
                break;
            case 4:
                human.setExperience(50);
                human.setPoints(65 + human.getHealth() / 2);
                break;
        }
        for (int i = 0; i < 5; i++) {
            if (experience_for_next_level[i] == human.getExperience()) {
                human.setLevel();
                human.setNextExperience(experience_for_next_level[i + 1]);
                NewHealthHuman(human);
                for (int j = 0; j < 4; j++) {
                    NewHealthEnemy(enemyes[j], human);
                }
            }
        }
    }
    /**
     * Adds items to the inventory based on the provided probabilities.
     * @param k1 The probability for the first item.
     * @param k2 The probability for the second item.
     * @param k3 The probability for the third item.
     * @param items The array of items.
     */
    public void AddItems(int k1, int k2, int k3, Items[] items) {
        double i = Math.random();
        if (i < k1 * 0.01) {
            items[0].setCount(1);
        }
        if (i >= k1 * 0.01 & i < (k1 + k2) * 0.01) {
            items[1].setCount(1);
        }
        if (i >= (k1 + k2) * 0.01 & i < (k1 + k2 + k3) * 0.01) {
            items[2].setCount(1);
        }
    }

    public void NewHealthHuman(Human human) {
        int hp = 0;
        int damage = 0;
        switch (human.getLevel()) {
            case 1:
                hp = 25;
                damage = 3;
                break;
            case 2:
                hp = 30;
                damage = 3;
                break;
            case 3:
                hp = 30;
                damage = 4;
                break;
            case 4:
                hp = 40;
                damage = 6;
                break;
        }
        human.setMaxHealth(hp);
        human.setDamage(damage);
    }

    public void NewHealthEnemy(Player enemy, Human human) {
        int hp = 0;
        int damage = 0;
        switch (human.getLevel()) {
            case 1:
                hp = 32;
                damage = 25;
                break;
            case 2:
                hp = 30;
                damage = 20;
                break;
            case 3:
                hp = 23;
                damage = 24;
                break;
            case 4:
                hp = 25;
                damage = 26;
                break;
        }
        enemy.setMaxHealth((int) enemy.getMaxHealth() * hp / 100);
        enemy.setDamage((int) enemy.getDamage() * damage / 100);
        enemy.setLevel();
    }
    /**
     * Uses the selected item and updates the player's health accordingly.

     */
    public void UseItem(Player human, Items[] items, String name, JDialog dialog, JDialog dialog1) {
        switch (name) {
            case "jRadioButton1":
                if (items[0].getCount() > 0) {
                    human.setHealth((int) (human.getMaxHealth() * 0.25));
                    items[0].setCount(-1);
                } else {
                    dialog.setVisible(true);
                    dialog.setBounds(300, 200, 400, 300);
                }
                break;
            case "jRadioButton2":
                if (items[1].getCount() > 0) {
                    human.setHealth((int) (human.getMaxHealth() * 0.5));
                    items[1].setCount(-1);
                } else {
                    dialog.setVisible(true);
                    dialog.setBounds(300, 200, 400, 300);
                }
                break;
            case "jRadioButton3":
                dialog.setVisible(true);
                dialog.setBounds(300, 200, 400, 300);
                break;
        }

        if(dialog.isVisible()==false){
            dialog1.dispose();
        }
    }
}
