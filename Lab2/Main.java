import ru.ifmo.se.pokemon.*;
import Pokemons.*;

public class Main {
    public static void main(String[] args) {
        Battle b = new Battle();
        Pokemon p1 = new Ninjask("", 1);
        Pokemon p2 = new Gengar("", 1);
        Pokemon p3 = new Sneasel("", 1);
        Pokemon p4 = new Gyarados("", 1);
        Pokemon p5 = new Combusken("", 1);
        Pokemon p6 = new Ursaring("", 1);

        b.addAlly(p1);
        b.addAlly(p3);
        b.addAlly(p5);
        b.addFoe(p2);
        b.addFoe(p4);
        b.addFoe(p6);

        b.go();
    }
}
