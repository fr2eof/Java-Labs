import ru.ifmo.se.pokemon.*;

public class Combusken extends Pokemon {
    public Combusken(String name, int level) {
        super(name, level);
        setType(Type.FIRE, Type.FIGHTING);
        setStats(60, 85, 60, 85, 60, 55);
        setMove(new TripleKick(), new LightScreen(), new ShadowPunch(), new Frustration());
    }
}