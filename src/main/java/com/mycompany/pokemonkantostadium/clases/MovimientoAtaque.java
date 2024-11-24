package com.mycompany.pokemonkantostadium.clases;

public class MovimientoAtaque extends Movimiento{
    
    private int daño;
    
    public MovimientoAtaque(String nombre, int pp, Tipo tipo, int daño) {
        super(nombre, pp, tipo);
        this.daño = daño;
    }
    
    @Override
    public MovimientoAtaque copy() {
        MovimientoAtaque copy = new MovimientoAtaque(this.nombre, this.pp, this.tipo, this.daño);
        return copy;
    }

    public int getDaño() {
        return daño;
    }

    public void setDaño(int daño) {
        this.daño = daño;
    }
    
    
}
