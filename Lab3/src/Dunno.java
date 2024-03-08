public class Dunno extends Human implements Cloneable{
    public Dunno(String name, int cash, Guns item) {
        super(name, cash, item);
    }

    public Dunno(String name, int cash) {
        super(name, cash);
    }

    public Dunno(int cash) {
        super(cash);
    }


    @Override
    public String toString() {
        return "Незнайка {" +
                "name='" + super.getName() + '\'' +
                ", cash=" + super.getValue() + '}';
    }

}