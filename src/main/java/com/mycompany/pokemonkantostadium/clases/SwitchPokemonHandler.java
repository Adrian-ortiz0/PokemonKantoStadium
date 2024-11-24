package com.mycompany.pokemonkantostadium.clases;

public class SwitchPokemonHandler extends AbstractBattleHandler {
    @Override
    public void handle(BattleContext context) {
        handleNext(context);
    }
}
