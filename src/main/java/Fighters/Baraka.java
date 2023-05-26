package Fighters;


import Fighters.Player;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Fighters of class tank
 * @author Мария
 */

// танк
public class Baraka extends Player{
    
    public Baraka(int level, int health, int  damage, int attack){
        super (level, health, damage, attack);
    }

    /**
     * Method for getting enemy name
     * @return
     */
    @Override
    public String getName(){
        return "Baraka";
    }
    
}
