public class Main {
    public static void main(String[] args) {
        Dunno person1 = new Dunno("тупенький", (int) (Math.random() * 10000));
        Dunno person2 = new Dunno("тупенький2", (int) (Math.random() * 10000));
        Guns pistol = new Firearms("tec9");
        pistol.setType(TypeOfGun.PISTOL);
        pistol.setPrice(132);
        //System.out.println(person1.getValue());
        person1.buy(pistol);
        person1.use(pistol);
        person2.buy(pistol);
        //System.out.println(pistol.getClass());
        System.out.println(person1.equals(person2));
    }
}
