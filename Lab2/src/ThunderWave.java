import ru.ifmo.se.pokemon.*;

public class ThunderWave extends StatusMove {
    public ThunderWave() {
        super(Type.ELECTRIC, 0, 100);
    }

    private boolean flag;

    @Override
    protected void applyOppEffects(Pokemon p) {
        //Thunder Wave paralyzes the opponent. Paralyzed Pokémon have a 25% chance of not being able to attack, and their Speed is decreased by 50%
        if (Math.round(Math.random() * 100) >= 25) {
            Effect.paralyze(p);
            p.setMod(Stat.SPEED, -(int) (0.5 * p.getStat(Stat.SPEED)));
            flag = true;
        }
    }

    @Override
    protected String describe() {
        if (flag) return "парализует громовой волной";
        else return "атакует";
    }
}
