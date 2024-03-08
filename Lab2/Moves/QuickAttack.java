package Moves;

import ru.ifmo.se.pokemon.*;

public class QuickAttack extends PhysicalMove {
    //Quick Attack deals damage and has a priority of +1.
    public QuickAttack() {
        super(Type.NORMAL, 40, 100, getStruggleMove().getPriority() + 1, 1);
    }

    @Override
    protected String describe() {
        return "использует быструю атаку";
    }
}
