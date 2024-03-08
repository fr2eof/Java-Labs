package Pokemons;

import Moves.*;
import ru.ifmo.se.pokemon.*;

public class Ninjask extends Pokemon {
    public Ninjask(String name, int level) {
        super(name, level);
        setType(Type.BUG, Type.FLYING);
        setStats(61, 90, 45, 50, 50, 160);
        setMove(new Trick(), new Fissure(), new ShadowPunch(), new MorningSun());
    }
}