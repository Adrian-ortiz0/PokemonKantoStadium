/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pokemonkantostadium.clases;

/**
 *
 * @author Danie
 */
public class CheckPokemonStateHandler extends AbstractBattleHandler {
    @Override
    public void handle(BattleContext context) {
        Pokemon pokemonActivo = context.getJugadorActual().getEquipoPokemon()
                .get(context.getJugadorActual().getPokemonActivo());
                
        if (pokemonActivo.isEstaKO()) {
            boolean tienePokemonDisponible = false;
            for (Pokemon pokemon : context.getJugadorActual().getEquipoPokemon()) {
                if (!pokemon.isEstaKO()) {
                    tienePokemonDisponible = true;
                    break;
                }
            }
            
            if (!tienePokemonDisponible) {
                context.setBattleEnded(true);
                context.setBattleMessage(context.getJugadorOponente().getNombre() + " ha ganado la batalla!");
                return;
            }
            
            context.setBattleMessage(pokemonActivo.getNombre() + " está debilitado! Debes cambiar de Pokemon!");
            return;
        }
        
        handleNext(context);
    }
}
