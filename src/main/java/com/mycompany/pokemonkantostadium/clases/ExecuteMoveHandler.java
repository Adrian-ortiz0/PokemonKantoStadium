/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pokemonkantostadium.clases;

/**
 *
 * @author Danie
 */
public class ExecuteMoveHandler extends AbstractBattleHandler {
    @Override
    public void handle(BattleContext context) {
        Pokemon pokemonActivo = context.getJugadorActual().getEquipoPokemon()
                .get(context.getJugadorActual().getPokemonActivo());
        Pokemon pokemonOponente = context.getJugadorOponente().getEquipoPokemon()
                .get(context.getJugadorOponente().getPokemonActivo());
        
        handleNext(context);
    }
}