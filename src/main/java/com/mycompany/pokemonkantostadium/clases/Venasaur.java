package com.mycompany.pokemonkantostadium.clases;

import java.util.ArrayList;

public class Venasaur extends Pokemon{

    public Venasaur() {
        super("Venasaur");
        this.idPokedex = 6;
        this.movimientos = crearMovimientos();
        this.ataque = 400;
        this.defensa = 380;
        this.salud = 250;
        this.tipo = Tipo.PLANTA;
    }
    
    private static ArrayList<Movimiento> crearMovimientos() {
        ArrayList<Movimiento> movimientos = new ArrayList<>();
        movimientos.add(new MovimientoAtaque("Latigo Cepa", 15, Tipo.PLANTA, 55));
        movimientos.add(new MovimientoAtaque("Hoja Afilada", 20, Tipo.PLANTA, 70));
        movimientos.add(new MovimientoAtaque("Golpe Cuerpo", 15, Tipo.NORMAL, 85));
        movimientos.add(new MovimientoStat("Aumentar Defensa", 20, Tipo.NORMAL, "defensa"));
        return movimientos;
    }
    
    @Override
    public Pokemon copy() {
        Venasaur copy = new Venasaur();
        copy.salud = this.salud;
        copy.ataque = this.ataque;
        copy.defensa = this.defensa;
        copy.nivel = this.nivel;
        copy.estaKO = this.estaKO;
        
        copy.movimientos = new ArrayList<>();
        for (Movimiento movimiento : this.movimientos) {
            copy.movimientos.add(movimiento.copy());
        }
        
        return copy;
    }

    @Override
    public void atacar(Pokemon objetivo, MovimientoAtaque movimiento) {
        if(movimiento.getPp() > 0){
            double efectividad = TablaDeEfectividad.calcularEfectividad(objetivo.getTipo(), movimiento.getTipo());
            int dañoBase = movimiento.getDaño();
            int dañoFinal = (int) ((getAtaque() * dañoBase / objetivo.getDefensa()) * efectividad);
            System.out.printf("%s usa %s contra %s. Daño: %d\n", getNombre(), movimiento.getNombre(), objetivo.getNombre(), dañoFinal);
            objetivo.setSalud(objetivo.getSalud() - dañoFinal);
            movimiento.reducirPp();
        }else {
            System.out.println("El movimiento no tiene pp restantes");
        }
    }

    @Override
    public void usarMovimientoStat(MovimientoStat movimiento) {
        String stat = movimiento.getEstadisticaABuffear();
        System.out.printf("%s usa %s para mejorar su %s.\n", getNombre(), movimiento.getNombre(), stat);
        
        switch (stat.toLowerCase()) {
        case "ataque":
            incrementarEstadistica("ataque", 10);
            break;
        case "defensa":
            incrementarEstadistica("defensa", 15);
            break;
        case "salud":
            incrementarEstadistica("salud", 20);
            break;
        default:
            System.out.println("Estadística no válida para buffear.");
            return;
    }

        movimiento.reducirPp();
    
    }
    
    public void incrementarEstadistica(String estadistica, int incremento){
        switch (estadistica.toLowerCase()) {
        case "ataque":
            this.ataque += incremento;
            System.out.printf("El ataque de %s aumenta en %d puntos. Nuevo ataque: %d\n", getNombre(), incremento, this.ataque);
            break;
        case "defensa":
            this.defensa += incremento;
            System.out.printf("La defensa de %s aumenta en %d puntos. Nueva defensa: %d\n", getNombre(), incremento, this.defensa);
            break;
        case "salud":
            this.salud += incremento;
            System.out.printf("La salud de %s aumenta en %d puntos. Nueva salud: %d\n", getNombre(), incremento, this.salud);
            break;
        default:
            System.out.println("No se puede aumentar la estadística especificada.");
    }
    }
    
    
}
