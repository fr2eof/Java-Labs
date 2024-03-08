import enums.MasksType;

public class Masks {
    private MasksType type;
    private final String name;
    private Integer price;

    public Masks(String name) {
        this.name = name;
        System.out.println("Устанавливаем имя данной маски");
    }

    public String getName() {
        return this.name;
    }

    public void setType(MasksType type) {
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

    public MasksType getType() {
        return this.type;
    }
}
