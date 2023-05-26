package Fighters;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Fighters of class warrior
 * @author Мария
 */
//боец
public class LiuKang extends Player {
    
    public LiuKang(int level, int health, int  damage, int attack){
        super (level, health, damage, attack);
    }

    /**
     * Method for getting enemy name
     * @return
     */
    @Override
    public String getName(){
        return "Liu Kang";
    }
}
