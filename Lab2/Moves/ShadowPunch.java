package Moves;

import ru.ifmo.se.pokemon.*;

public class ShadowPunch extends PhysicalMove {
    /*Shadow Punch deals damage and ignores changes to the Accuracy and Evasion stats*/
    public ShadowPunch() {
        super(Type.GHOST, 60, Double.POSITIVE_INFINITY);
    }

    @Override
    protected String describe() {
        return "бьет из тени";
    }
}
