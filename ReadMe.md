# POKEMON KANTO STADIUM ⚔️

## Descripcion

Pokemon Kanto Stadium es un videojuego por turnos que opera dentro de la consola de Java, el cual se apega lo mas fiel posible a la funcionalidad convencional del juego lanzado para la N64.

## Tabla de Contenido 

| Indice | Descripcion        |
|--------|--------------------|
| 1      | Funciones          |
| 2      | Casos de uso       |
| 3      | Principios SOLID   |
| 4      | Patrones de diseño |
| 5      | Tabla de tipos     |
| 6      | Esquema            |
| 7      | Explicación        |
| 8      | FAQs               |
| 9      | Licencia           |
| 10     | Contacto / Autor   |

## Funciones 📱

Pokemon Stadium al ser hecho atraves de la consola de Java, cuenta con ciertas limitaciones, sin embargo, estas no son impedimento para poder disfrutar de partidas al mejor estilo de pokemon Stadium

1. Combates PVP
2. Registro de jugadores
3. Eleccion de equipo para los jugadores (Maximo 3 Pokemon por equipo)
4. Eleccion de pokemon iniciales
5. Eleccion de movimientos (Ataques / Movimientos de stats)
6. Cambio de pokemon durante la batalla
7. Debilitar pokemon del rival
8. Caracteristicas competitivas

## Casos de Uso ⭐

El unico modo de juego que se encuentra disponible en esta version de pokemon es el de combate pvp

En este, ambos jugadores deberan registrar sus nombres, luego pasaran a elegir sus pokemon del rouster que viene por defecto, de momento solo estan:
1. Charizard
2. Blastoise
3. Venasaur

Sin embargo se tiene pensado añadir hasta 15 pokemon para hacer del juego una experiencia mas completa.

Ambos jugadores una vez elijan sus pokemon podran elegir con cual de estos iniciar a combatir, luego de esto la batalla si empieza. Dentro de la batalla los jugadores podran elegir si desean cambiar de pokemon o elegir los ataques del pokemon activo
cada pokemon tiene ataques personalizados para poder combatir entre si de manera estrategica, respetando la tabla de tipos.
El juego acabará cuando algun jugador tenga 3 pokemon totalmente debilitados.

## Principios SOLID 📐

Pokemon Kanto Stadium cumple con todos los estandares de los principios SOLID para un codigo totalmente limpio
, se utilizan los patrones de diseño, control y creacion adecuados con el fin de facilitar la tarea de mantener estos principios
en el programa

## Patrones de diseño

### Prototype
Prototype permite crear nuevos objetos clonando un objeto existente, su estructura basica es la siguiente:
1. Una interface base - Prototipos base
2. Una implementacion concreta

Lo primero es definir que queremos clonar, en este caso queremos clonar tanto a los pokemon, como sus movimientos
estos ultimos con el fin de que posean pp personalizados:


    public interface MovimientoPrototype {
    
    MovimientoPrototype copy();
    }
###### Define el método de clonación para movimientos

    public interface PokemonPrototype {
    PokemonPrototype copy();
    }
###### Define el método de clonación para pokémon

Luego, las clases abstractas Pokemon y  Movimiento van a implementar estas interfaces, estas clases al ser padres de otras
(MovimientosAtaque, MovimientosStat, Charizard, Blastoise, Venasaur), van a implementar el metodo de las interfaces de manera abstracta

    @Override
    public abstract Movimiento copy();

Ahora si podremos implementar este metodo en las clases que heredan, veamos un ejemplo de como se hizo con charizard

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

Donde hemos efectuado de manera exitosa una copia de los atributos y de los movimientos, ahora dentro de la clase PokemonDatabase vamosa proceder a
guardar todos los prototipos

    private Map<String, Pokemon> prototipos;

    private void inicializarPrototipos() {
        prototipos.put("Charizard", new Charizard());
        prototipos.put("Blastoise", new Blastoise());
        prototipos.put("Venasaur", new Venasaur());
    }

Por ultimo hacemos una implementacion correcta en la UI al momento de que los jugadores deban elegir sus Pokemon

El patrón de diseño Prototype fue implementado con el fin de crear copias de las clases de Pokemon y movimientos
ya existentes, esto ayudó a resolver el problema de tener una misma instancia de un pokemon para ambos jugadores, es decir, lo que le pasaba
al pokemon del jugador 1, tambien afectaba al pokemon del jugador 2, entonces si un jugador debilitaba a un pokemon el pokemon del jugador tambien se iba a debilitar

Las clases que estan involucradas en este patron son:
1. MovimientoPrototype
2. Movimiento
3. PokemonPrototype
4. Pokemon
5. PokemonDatabase

El patrón Prototype se utiliza aquí para crear copias de Pokémon preconfigurados sin necesidad de crear nuevas instancias desde cero cada vez

#### Estructura del Patrón:

1. Las interfaces PokemonPrototype y MovimientoPrototype definen el contrato para la clonación
2. Las clases abstractas Pokemon y Movimiento implementan estas interfaces
3. PokemonDatabase actúa como un registro de prototipos, usando el patrón Singleton
