package com.mycompany.pokemonkantostadium.clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PokemonDatabase {
    private static PokemonDatabase instance;
    private Map<String, Pokemon> prototipos;  // Guardamos los prototipos

    private PokemonDatabase() {
        prototipos = new HashMap<>();
        inicializarPrototipos();
    }

    private void inicializarPrototipos() {
        prototipos.put("Charizard", new Charizard());
        prototipos.put("Blastoise", new Blastoise());
        prototipos.put("Venasaur", new Venasaur());
    }

    public static PokemonDatabase getInstance() {
        if (instance == null) {
            instance = new PokemonDatabase();
        }
        return instance;
    }

    public Pokemon getPokemon(String nombre) {
        Pokemon prototipo = prototipos.get(nombre);
        if (prototipo == null) {
            throw new IllegalArgumentException("Pokemon no encontrado: " + nombre);
        }
        return (Pokemon) prototipo.copy();
    }

    public ArrayList<Pokemon> getListaDePokemon() {
        ArrayList<Pokemon> lista = new ArrayList<>();
        for (Pokemon prototipo : prototipos.values()) {
            lista.add((Pokemon) prototipo.copy());
        }
        return lista;
    }
}
