import ru.ifmo.se.pokemon.*;

class TripleKick extends PhysicalMove {
    //Triple Kick deals damage and will strike three times
    public TripleKick() {
        super(Type.FIGHTING, 20, 90, getStruggleMove().getPriority(), 3);

    }

    @Override
    protected String describe() {
        return "использует тройной удар ногой";
    }
}
