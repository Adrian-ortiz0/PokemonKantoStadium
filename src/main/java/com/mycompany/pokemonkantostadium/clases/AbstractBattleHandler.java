package com.mycompany.pokemonkantostadium.clases;

public abstract class AbstractBattleHandler implements BattleHandler {
    protected BattleHandler nextHandler;
    
    @Override
    public void setSiguiente(BattleHandler handler) {
        this.nextHandler = handler;
    }
    
    protected void handleNext(BattleContext context) {
        if (nextHandler != null) {
            nextHandler.handle(context);
        }
    }
}