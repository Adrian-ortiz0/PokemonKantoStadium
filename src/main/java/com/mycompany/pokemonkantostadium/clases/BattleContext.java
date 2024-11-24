/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.pokemonkantostadium.clases;

/**
 *
 * @author Danie
 */
public class BattleContext {
    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugadorActual;
    private boolean battleEnded;
    private String battleMessage;
    
    public BattleContext(Jugador jugador1, Jugador jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.jugadorActual = jugador1;
        this.battleEnded = false;
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public void setJugador1(Jugador jugador1) {
        this.jugador1 = jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public void setJugador2(Jugador jugador2) {
        this.jugador2 = jugador2;
    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    public void setJugadorActual(Jugador jugadorActual) {
        this.jugadorActual = jugadorActual;
    }

    public boolean isBattleEnded() {
        return battleEnded;
    }

    public void setBattleEnded(boolean battleEnded) {
        this.battleEnded = battleEnded;
    }

    public String getBattleMessage() {
        return battleMessage;
    }

    public void setBattleMessage(String battleMessage) {
        this.battleMessage = battleMessage;
    }
    public void cambiarTurno() {
        jugadorActual = (jugadorActual == jugador1) ? jugador2 : jugador1;
    }
    public Jugador getJugadorOponente() {
        return (jugadorActual == jugador1) ? jugador2 : jugador1;
    }
}
