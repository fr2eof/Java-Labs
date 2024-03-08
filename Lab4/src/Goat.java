public class Goat extends Human {
    public Goat(String name, int cash) {
        super(name, cash);
    }

    public Goat(int cash) {
        super(cash);
    }

    @Override
    public String toString() {
        return "Козлик {" +
                "name='" + super.getName() + '\'' +
                ", cash=" + super.getValue() + '}';
    }

}

