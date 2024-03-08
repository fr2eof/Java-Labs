package Moves;

import ru.ifmo.se.pokemon.*;

import java.lang.Math;

public class Fissure extends PhysicalMove {
    public Fissure() {
        super(Type.GROUND, 0, 30);
    }

    private boolean flag;

    @Override
    protected void applyOppEffects(Pokemon p) {
        if (Math.round(Math.random() * 100) <= 30) {
            Effect.sleep(p);
            flag = true;
        }
    }

    @Override
    protected String describe() {
        if (flag) return "делает разлом";
        else return "атакует";
    }
}
