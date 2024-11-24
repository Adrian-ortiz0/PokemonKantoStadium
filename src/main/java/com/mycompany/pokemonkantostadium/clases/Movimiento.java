package com.mycompany.pokemonkantostadium.clases;

public abstract class Movimiento implements MovimientoPrototype{
    
    protected String nombre;
    protected int pp;
    protected Tipo tipo;

    public Movimiento(String nombre, int pp, Tipo tipo) {
        this.nombre = nombre;
        this.pp = pp;
        this.tipo = tipo;
    }
    
    public void reducirPp() {
        if (pp > 0) {
            pp--;
        }
    }
    
    @Override
    public abstract Movimiento copy();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPp() {
        return pp;
    }

    public void setPp(int pp) {
        this.pp = pp;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
    
        
}
