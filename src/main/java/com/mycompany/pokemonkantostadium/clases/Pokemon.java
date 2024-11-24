package com.mycompany.pokemonkantostadium.clases;

import java.util.ArrayList;

public abstract class Pokemon implements PokemonPrototype{
    protected String nombre;
    protected int idPokedex;
    protected ArrayList<Movimiento> movimientos;
    protected int salud;
    protected int defensa;
    protected int ataque;
    protected Tipo tipo;
    protected int nivel;
    protected boolean estaKO;

    public Pokemon(String nombre) {
        this.nombre = nombre;
        this.nivel = 50;
        this.estaKO = false;
    }

    @Override
    public abstract PokemonPrototype copy();

    public abstract void atacar(Pokemon objetivo, MovimientoAtaque movimiento);
    public abstract void usarMovimientoStat(MovimientoStat movimiento);

    public boolean isEstaKO() {
        return this.salud <= 0;
    }

    public void setEstaKO(boolean estaKO) {
        this.estaKO = estaKO;
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdPokedex() {
        return idPokedex;
    }

    public void setIdPokedex(int idPokedex) {
        this.idPokedex = idPokedex;
    }

    public ArrayList<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(ArrayList<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
    
}
