package com.mycompany.pokemonkantostadium.clases;
import java.util.ArrayList;
import java.util.Scanner;
public class UI {
    private Scanner scanner;
    
    public UI() {
        this.scanner = new Scanner(System.in);
    }
    
    public void start() {
        ArrayList<Jugador> jugadores = menuInicial();
        menuEleccionPokemon(jugadores);
        seleccionarPokemonInicial(jugadores);
        iniciarBatalla(jugadores);
    }
    
    public ArrayList<Jugador> menuInicial() {
        ArrayList<Jugador> jugadoresActuales = new ArrayList<>();
        System.out.println("Bienvenido a Pokemon Kanto Stadium!");
        System.out.println("-----------------------------------");
        
        System.out.println("Ingresa el nombre del jugador 1");
        String nombreJugador1 = scanner.nextLine();
        Jugador jugador1 = new Jugador(nombreJugador1);
        
        System.out.println("Ingresa el nombre del jugador 2");
        String nombreJugador2 = scanner.nextLine();
        Jugador jugador2 = new Jugador(nombreJugador2);
        
        System.out.println("Bienvenidos jugadores: " + nombreJugador1 + " y " + nombreJugador2);
        
        jugadoresActuales.add(jugador1);
        jugadoresActuales.add(jugador2);
        
        System.out.println("Jugadores registrados con exito!");
        return jugadoresActuales;
    }
    
    public ArrayList<Jugador> menuEleccionPokemon(ArrayList<Jugador> jugadoresActuales) {
        for (int jugadorIndex = 0; jugadorIndex < 2; jugadorIndex++) {
        Jugador jugadorActual = jugadoresActuales.get(jugadorIndex);
        System.out.println("\nJugador: " + jugadorActual.getNombre() + " Elige tus 3 Pokémon");
        
        ArrayList<Pokemon> listaDePokemon = PokemonDatabase.getInstance().getListaDePokemon();
        
        while (jugadorActual.getEquipoPokemon().size() < 3) {
            try {
                mostrarPokemonesDisponibles(listaDePokemon);
                
                System.out.print("Elige el numero de tu Pokemon: ");
                int eleccion = Integer.parseInt(scanner.nextLine());
                
                if (eleccion > 0 && eleccion <= listaDePokemon.size()) {
                    Pokemon pokemonElegido = (Pokemon) listaDePokemon.get(eleccion - 1).copy();
                    jugadorActual.agregarPokemon(pokemonElegido);
                    listaDePokemon.remove(eleccion - 1);
                    
                    System.out.println("Has elegido a " + pokemonElegido.getNombre() + "!");
                
                    } else {
                        System.out.println("Opcion invalida. Por favor, elige un numero entre 1 y " + listaDePokemon.size());
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Por favor, ingresa un numero valido.");
                }
            }
            
            mostrarEquipoPokemon(jugadorActual);
        }
        
        return jugadoresActuales;
    }
    
    private void seleccionarPokemonInicial(ArrayList<Jugador> jugadores) {
        System.out.println("\nEs hora de elegir tu Pokemon inicial para la batalla!");
        
        for (int i = 0; i < jugadores.size(); i++) {
            Jugador jugadorActual = jugadores.get(i);
            boolean seleccionValida = false;
            
            while (!seleccionValida) {
                System.out.println("\n" + jugadorActual.getNombre() + ", elige tu Pokemon inicial:");
                mostrarEquipoPokemon(jugadorActual);
                
                try {
                    System.out.print("Ingresa el numero (1-3) del Pokemon con el que deseas comenzar: ");
                    int eleccion = Integer.parseInt(scanner.nextLine());
                    
                    if (eleccion >= 1 && eleccion <= 3) {
                        jugadorActual.setPokemonActivo(eleccion - 1);
                        Pokemon pokemonElegido = jugadorActual.getEquipoPokemon().get(eleccion - 1);
                        System.out.println(jugadorActual.getNombre() + " comenzara la batalla con " + 
                                         pokemonElegido.getNombre() + "!");
                        seleccionValida = true;
                    } else {
                        System.out.println("Por favor, elige un numero entre 1 y 3.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Por favor, ingresa un numero valido.");
                }
            }
        }
        
        System.out.println("\nLos Pokemon iniciales han sido elegidos! Que comience la batalla!");
    }
    
    private void mostrarPokemonesDisponibles(ArrayList<Pokemon> listaPokemon) {
        System.out.println("\nPokemon disponibles:");
        System.out.println("--------------------");
        for (int i = 0; i < listaPokemon.size(); i++) {
            System.out.println((i + 1) + ". " + listaPokemon.get(i).getNombre());
        }
    }
    
    private void mostrarEquipoPokemon(Jugador jugador) {
        System.out.println("\nEquipo de " + jugador.getNombre() + ":");
        System.out.println("--------------------");
        for (int i = 0; i < jugador.getEquipoPokemon().size(); i++) {
            System.out.println((i + 1) + ". " + jugador.getEquipoPokemon().get(i).getNombre());
        }
    }
    
    public void iniciarBatalla(ArrayList<Jugador> jugadores) {
        BattleSystem battleSystem = new BattleSystem(jugadores.get(0), jugadores.get(1));
        System.out.println("\nQue comience la batalla!");
        
        while (!battleSystem.getContext().isBattleEnded()) {
            Jugador jugadorActual = battleSystem.getContext().getJugadorActual();
            Jugador jugadorOponente = battleSystem.getContext().getJugadorOponente();
            
            Pokemon pokemonActivo = jugadorActual.getEquipoPokemon().get(jugadorActual.getPokemonActivo());
            Pokemon pokemonOponente = jugadorOponente.getEquipoPokemon().get(jugadorOponente.getPokemonActivo());
            
            mostrarEstadoBatalla(jugadorActual, jugadorOponente, pokemonActivo, pokemonOponente);
            
            if (pokemonActivo.isEstaKO()) {
                manejarPokemonDebilitado(jugadorActual);
                if (verificarDerrota(jugadorActual)) {
                    System.out.println("\n" + jugadorOponente.getNombre() + " ha ganado la batalla!");
                    battleSystem.getContext().setBattleEnded(true);
                    continue;
                }
                continue;
            }
            
            int accion = mostrarYObtenerAccion();
            
            switch (accion) {
                case 1:
                    realizarAtaque(pokemonActivo, pokemonOponente);
                    break;
                    
                case 2:
                    if (cambiarPokemon(jugadorActual)) {
                        System.out.println(jugadorActual.getNombre() + " ha cambiado de Pokemon!");
                    } else {
                        continue;
                    }
                    break;
                    
                case 3: 
                    mostrarEstadoDetallado(jugadorActual, jugadorOponente);
                    continue;
                    
                default:
                    System.out.println("Opcion invalida");
                    continue;
            }
            
            battleSystem.processTurn();
            if (!battleSystem.getContext().isBattleEnded()) {
                battleSystem.getContext().cambiarTurno();
            }
        }
    }
    
    private void mostrarEstadoBatalla(Jugador jugadorActual, Jugador jugadorOponente, 
                                    Pokemon pokemonActivo, Pokemon pokemonOponente) {
        System.out.println("\n=== Estado de la Batalla ===");
        System.out.println(jugadorActual.getNombre() + " (Turno actual)");
        System.out.println("Pokemon: " + pokemonActivo.getNombre() + 
                         " | HP: " + pokemonActivo.getSalud() + 
                         " | Tipo: " + pokemonActivo.getTipo());
        
        System.out.println("\n" + jugadorOponente.getNombre());
        System.out.println("Pokemon: " + pokemonOponente.getNombre() + 
                         " | HP: " + pokemonOponente.getSalud() + 
                         " | Tipo: " + pokemonOponente.getTipo());
        System.out.println("==========================");
    }
    
    private int mostrarYObtenerAccion() {
        while (true) {
            try {
                System.out.println("\nQue deseas hacer?");
                System.out.println("1. Atacar");
                System.out.println("2. Cambiar Pokemon");
                System.out.println("3. Ver estado detallado");
                System.out.print("Elige una opcion: ");
                
                int opcion = Integer.parseInt(scanner.nextLine());
                if (opcion >= 1 && opcion <= 3) {
                    return opcion;
                } else {
                    System.out.println("Por favor, elige una opción valida (1-3)");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingresa un numero valido");
            }
        }
    }
    
    private void realizarAtaque(Pokemon atacante, Pokemon objetivo) {
        ArrayList<Movimiento> movimientos = atacante.getMovimientos();
        
        while (true) {
            try {
                System.out.println("\nMovimientos disponibles:");
                for (int i = 0; i < movimientos.size(); i++) {
                    Movimiento mov = movimientos.get(i);
                    if (mov instanceof MovimientoAtaque) {
                        MovimientoAtaque movAtaque = (MovimientoAtaque) mov;
                        System.out.println((i + 1) + ". " + mov.getNombre() + 
                                         " (Poder: " + movAtaque.getDaño() + 
                                         " | Tipo: " + movAtaque.getTipo() + ")");
                    } else {
                        System.out.println((i + 1) + ". " + mov.getNombre() + " (Movimiento de Estado)");
                    }
                }
                
                System.out.print("Elige un movimiento (1-" + movimientos.size() + "): ");
                int eleccion = Integer.parseInt(scanner.nextLine());
                
                if (eleccion >= 1 && eleccion <= movimientos.size()) {
                    Movimiento movimientoElegido = movimientos.get(eleccion - 1);
                    if (movimientoElegido instanceof MovimientoAtaque) {
                        atacante.atacar(objetivo, (MovimientoAtaque) movimientoElegido);
                        System.out.println("\n¡" + atacante.getNombre() + " uso " + 
                                         movimientoElegido.getNombre() + "!");
                    } else {
                        atacante.usarMovimientoStat((MovimientoStat) movimientoElegido);
                        System.out.println("\n¡" + atacante.getNombre() + " uso " + 
                                         movimientoElegido.getNombre() + "!");
                    }
                    break;
                } else {
                    System.out.println("Movimiento invalido");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingresa un numero valido");
            }
        }
    }
    
    private boolean cambiarPokemon(Jugador jugador) {
        ArrayList<Pokemon> equipo = jugador.getEquipoPokemon();
        System.out.println("\nTu equipo Pokemon:");
        
        boolean hayPokemonDisponible = false;
        for (int i = 0; i < equipo.size(); i++) {
            Pokemon pokemon = equipo.get(i);
            if (!pokemon.isEstaKO() && i != jugador.getPokemonActivo()) {
                System.out.println((i + 1) + ". " + pokemon.getNombre() + 
                                 " (HP: " + pokemon.getSalud() + ")");
                hayPokemonDisponible = true;
            } else if (i == jugador.getPokemonActivo()) {
                System.out.println((i + 1) + ". " + pokemon.getNombre() + 
                                 " (Actualmente en batalla)");
            } else {
                System.out.println((i + 1) + ". " + pokemon.getNombre() + 
                                 " (Debilitado)");
            }
        }
        
        if (!hayPokemonDisponible) {
            System.out.println("No hay Pokemon disponibles para cambiar!");
            return false;
        }
        
        while (true) {
            try {
                System.out.print("Elige un Pokemon (1-" + equipo.size() + 
                               ") o 0 para cancelar: ");
                int eleccion = Integer.parseInt(scanner.nextLine());
                
                if (eleccion == 0) {
                    return false;
                }
                
                if (eleccion >= 1 && eleccion <= equipo.size()) {
                    Pokemon pokemonElegido = equipo.get(eleccion - 1);
                    
                    if (eleccion - 1 == jugador.getPokemonActivo()) {
                        System.out.println("Este Pokemon ya está en batalla!");
                        continue;
                    }
                    
                    if (pokemonElegido.isEstaKO()) {
                        System.out.println("Este Pokemon está debilitado!");
                        continue;
                    }
                    
                    jugador.setPokemonActivo(eleccion - 1);
                    return true;
                } else {
                    System.out.println("Eleccion invalida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingresa un numero valido");
            }
        }
    }
    
    private void manejarPokemonDebilitado(Jugador jugador) {
        System.out.println("\n" + jugador.getEquipoPokemon().get(jugador.getPokemonActivo()).getNombre() + 
                         " esta debilitado!");
        
        if (!verificarDerrota(jugador)) {
            System.out.println("Debes elegir otro Pokemon!");
            while (!cambiarPokemon(jugador)) {
                System.out.println("Debes seleccionar un Pokemon valido para continuar!");
            }
        }
    }
    
    private boolean verificarDerrota(Jugador jugador) {
        for (Pokemon pokemon : jugador.getEquipoPokemon()) {
            if (!pokemon.isEstaKO()) {
                return false;
            }
        }
        return true;
    }
    
    private void mostrarEstadoDetallado(Jugador jugador1, Jugador jugador2) {
        System.out.println("\n=== Estado Detallado de la Batalla ===");
        
        for (Jugador jugador : new Jugador[]{jugador1, jugador2}) {
            System.out.println("\nEquipo de " + jugador.getNombre() + ":");
            for (int i = 0; i < jugador.getEquipoPokemon().size(); i++) {
                Pokemon pokemon = jugador.getEquipoPokemon().get(i);
                System.out.println("- " + pokemon.getNombre() +
                                 " | HP: " + pokemon.getSalud() +
                                 " | Ataque: " + pokemon.getAtaque() +
                                 " | Defensa: " + pokemon.getDefensa() +
                                 (pokemon.isEstaKO() ? " (Debilitado)" : "") +
                                 (i == jugador.getPokemonActivo() ? " (Activo)" : ""));
            }
        }
        
        System.out.println("\nPresiona Enter para continuar...");
        scanner.nextLine();
    }

}