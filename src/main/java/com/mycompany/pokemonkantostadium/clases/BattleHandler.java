/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.pokemonkantostadium.clases;

/**
 *
 * @author Danie
 */
public interface BattleHandler {
    
    void setSiguiente(BattleHandler handler);
    void handle(BattleContext context);
}
