package com.mycompany.pokemonkantostadium.clases;

import com.mycompany.pokemonkantostadium.clases.Tipo;

public class TablaDeEfectividad {
    
    public TablaDeEfectividad(){
        
    }
    
    private static final double tablaDeTipos[][] = {
        // AGUA - FUEGO - NORMAL - PLANTA
        {1.0, 0.5, 1.0, 2.0},     // AGUA
        {2.0, 1.0, 1.0, 0.5},     // FUEGO
        {1.0, 1.0, 1.0, 1.0},     // NORMAL
        {0.5, 2.0, 1.0, 1.0}      // PLANTA
    };
    
    public static double calcularEfectividad(Tipo atacante, Tipo defensor){
        return tablaDeTipos[atacante.ordinal()][defensor.ordinal()];
    }
}
