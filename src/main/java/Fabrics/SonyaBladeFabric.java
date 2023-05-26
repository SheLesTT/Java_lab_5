package Fabrics;

import Fighters.Player;
import Fighters.SonyaBlade;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 * Класс для создания  объектов типа SonyaBladeFabric
 * @author Мария
 */
public class SonyaBladeFabric implements EnemyFabricInterface {

    /**
     * Method for creations objects of class SonyaBlade
     */

    @Override
    public Player create(int i) {
        Player enemy;
        enemy = new SonyaBlade(1, 80, 16, 1);
        return enemy;
    }

}
