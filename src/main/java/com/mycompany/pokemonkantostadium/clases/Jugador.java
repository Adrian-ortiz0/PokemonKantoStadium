package com.mycompany.pokemonkantostadium.clases;

import java.util.ArrayList;

public class Jugador {
    
    private String nombre;
    private ArrayList<Pokemon> equipoPokemon;
    private int pokemonActivo;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.equipoPokemon = new ArrayList();
        this.pokemonActivo = 0;
    }

    public void setEquipoPokemon(ArrayList<Pokemon> equipoPokemon) {
        this.equipoPokemon = equipoPokemon;
    }

    public void setPokemonActivo(int pokemonActivo) {
        this.pokemonActivo = pokemonActivo;
    }

   public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Pokemon> getEquipoPokemon() {
        return equipoPokemon;
    }

    public void agregarPokemon(Pokemon pokemon) {
        this.equipoPokemon.add(pokemon);
    }

    public int getPokemonActivo() {
        return pokemonActivo;
    }
    
}
