package Moves;

import ru.ifmo.se.pokemon.*;

public class RapidSpin extends PhysicalMove {
    //Rapid Spin deals damage, raises the user's Speed by one stage
    public RapidSpin() {
        super(Type.NORMAL, 50, 100);
    }

    @Override
    protected void applySelfEffects(Pokemon p) {
        p.setMod(Stat.SPEED, +1);
    }

    @Override
    protected String describe() {
        return "крутится как юла";
    }

}
