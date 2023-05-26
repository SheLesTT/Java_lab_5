package Fighters;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Fighters of class soldier
 * @author Мария
 */
//солдат
public class SonyaBlade extends Player {
    
    public SonyaBlade (int level, int health, int  damage, int attack){
        super (level, health, damage, attack);
    }

    /**
     * Method for getting enemy name
     * @return
     */
    @Override
    public String getName(){
        return "Sonya Blade";
    }
}
