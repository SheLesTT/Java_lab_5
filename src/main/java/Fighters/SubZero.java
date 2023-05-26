package Fighters;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * fighter of class magician
 * @author Мария
 */
//маг
public class SubZero extends Player {
    
    public SubZero(int level, int health, int damage , int attack){
        super (level, health, damage, attack);
    }

    /**
     * Method for getting enemy name
     * @return
     */
    @Override
    public String getName(){
        return "Sub-Zero";
    }
}
