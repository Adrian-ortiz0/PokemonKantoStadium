/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pokemonkantostadium.clases;

/**
 *
 * @author Danie
 */
public class MovimientoStat extends Movimiento{
    
    private String estadisticaABuffear;
    
    public MovimientoStat(String nombre, int pp, Tipo tipo, String MovimientoStat) {
        super(nombre, pp, tipo);
        this.estadisticaABuffear = estadisticaABuffear;
    }
    @Override
    public MovimientoStat copy() {
        MovimientoStat copy = new MovimientoStat(this.nombre, this.pp, this.tipo, this.estadisticaABuffear);
        return copy;
    }

    public String getEstadisticaABuffear() {
        return estadisticaABuffear;
    }

    public void setEstadisticaABuffear(String estadisticaABuffear) {
        this.estadisticaABuffear = estadisticaABuffear;
    }
    
    
    
}
