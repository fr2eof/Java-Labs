package Moves;

import ru.ifmo.se.pokemon.*;

public class LightScreen extends StatusMove {
    //Light Screen reduces damage from Special attacks by 50%, for 5 turns. Its effects apply to all Pokémon on the user's side of the field.
    public LightScreen() {
        super(Type.PSYCHIC, 0, 0);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        if (Math.round(Math.random() * 100) <= 30) {
            p.addEffect((new Effect()).stat(Stat.SPECIAL_ATTACK, (int) (0.5 * p.getStat(Stat.SPECIAL_ATTACK))).turns(5));
        }
    }

    @Override
    protected String describe() {
        return "использует Световой экран";
    }
}
