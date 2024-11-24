package com.mycompany.pokemonkantostadium.clases;

import java.util.ArrayList;

public class Charizard extends Pokemon{

    public Charizard() {
        super("Charizard");
        this.ataque = 360;
        this.defensa = 280;
        this.salud = 293;
        this.tipo = Tipo.FUEGO;
        this.movimientos = crearMovimientos();
        this.idPokedex = 6;
    }
    
    private static ArrayList<Movimiento> crearMovimientos() {
        ArrayList<Movimiento> movimientos = new ArrayList<>();
        movimientos.add(new MovimientoAtaque("Lanzallamas", 15, Tipo.FUEGO, 90));
        movimientos.add(new MovimientoAtaque("Ascuas", 25, Tipo.FUEGO, 40));
        movimientos.add(new MovimientoAtaque("Vuelo", 15, Tipo.NORMAL, 70));
        movimientos.add(new MovimientoStat("Aumentar Ataque", 20, Tipo.NORMAL, "ataque"));
        return movimientos;
    }
    
    @Override
    public Pokemon copy() {
        Charizard copy = new Charizard();
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
            System.out.printf("El ataque de %s aumenta significativamente.\n", getNombre());
            this.setAtaque(this.getAtaque() + 15);
            break;
        case "defensa":
            System.out.printf("La defensa de %s aumenta.\n", getNombre());
            this.setDefensa(this.getDefensa() + 10);
            break;
        case "salud":
            System.out.printf("La salud de %s aumenta ligeramente.\n", getNombre());
            this.setSalud(this.getSalud() + 20);
            break;
        default:
            System.out.println("Estadística no válida para buffear.");
    }

    movimiento.reducirPp();
    }
    
}
