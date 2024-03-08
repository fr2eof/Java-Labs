public class Dunno extends Human {
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