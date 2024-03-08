package Pokemons;

import Moves.*;
import ru.ifmo.se.pokemon.*;

public class Sneasel extends Pokemon {
    public Sneasel(String name, int level) {
        super(name, level);
        setType(Type.DARK, Type.ICE);
        setStats(55, 95, 55, 35, 75, 115);
        setMove(new ThunderWave(), new Thunderbolt());
    }
}