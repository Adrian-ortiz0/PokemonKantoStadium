/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pokemonkantostadium.clases;

/**
 *
 * @author Danie
 */
public class BattleSystem {
    private BattleHandler firstHandler;
    private BattleContext context;
    
    public BattleSystem(Jugador jugador1, Jugador jugador2) {
        // Configurar la cadena de handlers
        CheckPokemonStateHandler checkStateHandler = new CheckPokemonStateHandler();
        ExecuteMoveHandler executeMoveHandler = new ExecuteMoveHandler();
        SwitchPokemonHandler switchPokemonHandler = new SwitchPokemonHandler();
        
        checkStateHandler.setSiguiente(executeMoveHandler);
        executeMoveHandler.setSiguiente(switchPokemonHandler);
        
        this.firstHandler = checkStateHandler;
        this.context = new BattleContext(jugador1, jugador2);
    }
    
    public void processTurn() {
        firstHandler.handle(context);
    }
    
    public BattleContext getContext() {
        return context;
    }
}