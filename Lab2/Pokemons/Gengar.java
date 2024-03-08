package Pokemons;

import ru.ifmo.se.pokemon.*;
import Moves.*;

public class Gengar extends Pokemon {
    public Gengar(String name, int level) {
        super(name, level);
        setType(Type.GHOST, Type.POISON);
        setStats(60, 65, 60, 130, 75, 110);
        setMove(new ThunderWave(), new Thunderbolt(), new RapidSpin());
    }
}
