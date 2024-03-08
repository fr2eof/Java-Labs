package Moves;

import ru.ifmo.se.pokemon.*;

public class Frustration extends PhysicalMove {
    public Frustration() {
        //The power of Moves.Frustration is higher when the Pokémon likes its trainer less
        super(Type.NORMAL, (int) ((255 - (int) (Math.random() * 101 + 1.1)) / 2.5), 100);
    }

    @Override
    protected String describe() {
        return "разочаровывается";
    }
}
