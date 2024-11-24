package com.mycompany.pokemonkantostadium.clases;

import java.util.ArrayList;

public class Blastoise extends Pokemon{

    public Blastoise() {
        super("Blastoise");
        this.idPokedex = 9;
        this.ataque = 180;
        this.defensa = 350;
        this.salud = 350;
        this.movimientos = crearMovimientos();
        this.tipo = Tipo.AGUA;
    }
    
    private static ArrayList<Movimiento> crearMovimientos() {
        ArrayList<Movimiento> movimientos = new ArrayList<>();
        movimientos.add(new MovimientoAtaque("Pistola de Agua", 25, Tipo.AGUA, 40));
        movimientos.add(new MovimientoAtaque("Hidrobomba", 5, Tipo.AGUA, 110));
        movimientos.add(new MovimientoAtaque("Mordisco", 15, Tipo.NORMAL, 60));
        movimientos.add(new MovimientoStat("Aumentar Salud", 10, Tipo.NORMAL, "salud"));
        return movimientos;
    }
    
    @Override
    public Pokemon copy() {
        Blastoise copy = new Blastoise();
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
            System.out.println(getNombre() + " usa " + movimiento.getNombre() + " contra " + objetivo.getNombre() + ". Daño: " + dañoFinal);
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
