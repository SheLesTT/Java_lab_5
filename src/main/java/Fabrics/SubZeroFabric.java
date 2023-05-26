package Fabrics;

import Fighters.Player;
import Fighters.SubZero;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * Class for creations objects of class SubZero
 * @author Мария
 */
public class SubZeroFabric implements EnemyFabricInterface {
    /**
     * method for crating objects of class SubZero
     * @param i
     * @return
     */
    @Override
    public Player create(int i) {
        Player enemy;
        enemy = new SubZero(1, 60, 16, 1);
        return enemy;
    }

}
