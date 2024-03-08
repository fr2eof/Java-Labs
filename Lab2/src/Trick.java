import ru.ifmo.se.pokemon.*;

class Trick extends StatusMove {
    //Trick switches held items with the target
    public Trick() {
        super(Type.PSYCHIC, 0, 100);
    }

    @Override
    protected String describe() {
        return "меняется предметами";
    }
}
