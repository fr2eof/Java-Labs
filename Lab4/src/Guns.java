import enums.TypeOfGun;
import interfaces.Describe;


abstract public class Guns implements Describe {
    private TypeOfGun type;
    private final String name;
    private Integer price;

    public Guns(String name) {
        this.name = name;
        System.out.println("Устанавливаем имя данного оружия");


    }

    public String getName() {
        return this.name;
    }

    public void setType(TypeOfGun type) {
        System.out.println("Устанавливаем тип " + getName());
        this.type = type;
    }

    public void setPrice(int price) {
        this.price = price;
        System.out.println("Устанавливаем цену " + getName());
    }

    public int getPrice() {
        return this.price;
    }

    public TypeOfGun getType() {
        return this.type;
    }

}
