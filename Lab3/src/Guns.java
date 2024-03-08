abstract class Guns extends Shop implements Describe {
    private TypeOfGun type;
    private final String name;

    public Guns(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setType(TypeOfGun type) {
        this.type = type;
    }

    public TypeOfGun getType() {
        return this.type;
    }

}
