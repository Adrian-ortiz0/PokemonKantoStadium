
package com.mycompany.pokemonkantostadium.clases;

public interface BattleHandler {
    
    void setSiguiente(BattleHandler handler);
    void handle(BattleContext context);
}
