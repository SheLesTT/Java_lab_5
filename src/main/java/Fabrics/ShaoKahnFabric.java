package Fabrics;

import Fighters.Player;
import Fighters.ShaoKahn;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * Class for creating objects of class ShaoKahn
 * @author Мария
 */
public class ShaoKahnFabric implements EnemyFabricInterface {

    /**
     *
     * Method for creations objects of class ShaoKhan
     * @param i
     * @return
     */
    @Override
    public Player create(int i) {
        Player enemy;
        if(i==0){
            enemy = new ShaoKahn(3, 100, 25, 1);
        }
        else{
           enemy = new ShaoKahn(3, 145, 44, 1); 
        }
        return enemy;
    }
}
