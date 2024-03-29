import java.util.Arrays;
import java.util.Objects;

public abstract class Human implements Attack {
    private final String name;
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

    public void addValue(int addValue) {
        this.cash += addValue;
    }

    public int getValue() {
        return this.cash;
    }

    public String getItems() {
        for (Guns elem : items) {
            if (elem != null) {
                return elem.getName();
            }
        }
        return "No items";
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
}