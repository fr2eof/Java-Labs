package Moves;

import ru.ifmo.se.pokemon.*;

public class Thunderbolt extends SpecialMove {
    public Thunderbolt() {
        super(Type.ELECTRIC, 90, 100);
    }

    //Moves.Thunderbolt deals damage and has a 10% chance of paralyzing the target.
    private boolean flag;

    @Override
    protected void applyOppEffects(Pokemon p) {
        if (Math.round(Math.random() * 100) <= 10) {
            Effect.paralyze(p);
            flag = true;
        }
    }

    @Override
    protected String describe() {
        if (flag) return "бьет молнией";
        else return "атакует";
    }
}
