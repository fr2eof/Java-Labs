package Moves;

import ru.ifmo.se.pokemon.*;

public class MorningSun extends StatusMove {
    public MorningSun() {
        super(Type.NORMAL, 0, 0);
    }

    //User recovers HP.
    @Override
    protected void applySelfEffects(Pokemon p) {
        p.restore();
    }

    @Override
    protected String describe() {
        return "восстанавливает HP и просыпается Солнце";
    }


}
