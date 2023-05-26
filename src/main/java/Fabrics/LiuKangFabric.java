package Fabrics;

import Fighters.Player;
import Fighters.LiuKang;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * Class for creating objects of class LiuKang
 * @author Мария
 */
public class LiuKangFabric implements EnemyFabricInterface {
    /**
     *
     * Method for creating objects of class LiuKang
     * @param i
     * @return
     */
    @Override
    public Player create(int i) {
        Player enemy;
        enemy = new LiuKang(1, 70, 20, 1);
        return enemy;
    }
}
