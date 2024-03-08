package Pokemons;

import Moves.QuickAttack;
import Moves.RapidSpin;
import Moves.ThunderWave;
import Moves.Thunderbolt;
import ru.ifmo.se.pokemon.*;

public class Ursaring extends Pokemon {
    public Ursaring(String name, int level) {
        super(name, level);
        setType(Type.NORMAL);
        setStats(90, 130, 75, 75, 75, 55);
        setMove(new ThunderWave(), new Thunderbolt(), new RapidSpin(), new QuickAttack());
    }
}