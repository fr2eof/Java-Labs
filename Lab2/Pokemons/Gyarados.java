package Pokemons;

import Moves.LightScreen;
import Moves.ShadowPunch;
import Moves.TripleKick;
import ru.ifmo.se.pokemon.*;

public class Gyarados extends Pokemon {
    public Gyarados(String name, int level) {
        super(name, level);
        setType(Type.WATER, Type.FLYING);
        setStats(95, 125, 79, 60, 100, 81);
        setMove(new TripleKick(), new LightScreen(), new ShadowPunch());
    }
}