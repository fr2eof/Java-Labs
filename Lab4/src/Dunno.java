import enums.Locations;

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

    public static int testt(int f1){
        try{
            return f1;
        }
        catch(Throwable e){

        }
        finally {
            return f1+1;
        }
    }

    @Override
    public String toString() {
        return "Незнайка {" +
                "name='" + super.getName() + '\'' +
                ", cash=" + super.getValue() + '}';
    }
}