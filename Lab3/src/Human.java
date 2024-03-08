import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class Human implements Attack {
    public String name;
    private int cash;
    private Guns item;
    Guns[] items = new Guns[3];

    public Human(String name, int cash) {
        this.name = name;
        this.cash = cash;
    }

    public Human(int cash) {
        this.name = "неизвестный";
        this.cash = cash;
    }
    public Human(String name, int cash, Guns item) {
        this.name = name;
        this.cash = cash;
        this.item = item;
    }

    public void addValue(int addValue) {
        this.cash += addValue;
    }

    public int getValue() {
        return this.cash;
    }
    public Guns getLastItem() {
        return items[items.length-1];
    }
    public void getMoney() {
        System.out.println("У "+ getName()+" "+getValue()+" денег");
    }

    public void getItems() {
        List<String> itt = new ArrayList<>();
        for (Guns elem : items) {
            if (elem!=null){
            itt.add(elem.getName());}
        }
        itt.forEach(System.out::println);

        }


    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Человек {" +
                "name='" + name + '\'' +
                ", cash=" + cash + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cash, Arrays.hashCode(items));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Human person = (Human) obj;
        return cash == person.cash && name.equals(person.name) && items == person.items;
    }

    public void buy(Guns item) {
        if (Arrays.asList(items).contains(null)) {
            if (item.getPrice() <= getValue()) {
                for (int i = 0; i < 3; i++) {
                    if (items[i] == null) {
                        items[i] = item;
                        System.out.println(getName()+" успешно покупает это оружие");
                        break;
                    }
                }
                addValue(-item.getPrice());
            } else {
                System.out.println("Не хватает денег на это оружие");
            }
        } else {
            System.out.println("Убери какое-нибудь оружие");
        }
    }

    public void use(Guns item) {
        if (Arrays.asList(items).contains(item)) {
            System.out.println(getClass().toString().substring(6) + " " + getName() + " " + item.describe() + item.getName());
        } else {
            System.out.println("У него нет такого оружия");
        }
    }

    public void move(Locations Loc) {
        System.out.println(getClass().toString().substring(6) + " " + getName() + " перемещается в " + Loc);
    }
}