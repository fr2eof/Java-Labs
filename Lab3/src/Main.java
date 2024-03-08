public class Main{
    public static void main(String[] args) {
        Dunno person1 = new Dunno("тупенький", (int) (Math.random() * 10000));
        Goat person2 = new Goat("тупенький2", (int) (Math.random() * 10000));
        Guns pistol = new Firearms("tec9");
        Guns pistol1 = new Firearms("tec9_1");
        pistol.setType(TypeOfGun.PISTOL);
        pistol.setPrice(132);
        pistol1.setPrice(1);
        person1.getMoney();
        person1.move(Locations.SHOP);
        person2.move(Locations.SHOP);
        person1.buy(pistol);
        person1.use(pistol);
        person2.buy(pistol1);
        person1.buy(pistol1);
        person1.getItems();
        person2.getItems();
        person1.move(Locations.STREET);
        person2.move(Locations.STREET);
        //person1.isTrue();
        FuncInter<Goat, Dunno> converter = x -> new Dunno(x.getName(), x.getValue(), x.getLastItem());
        Dunno personConv = converter.convert(person2);

        System.out.println(personConv);
        System.out.println(person1);
    }

}
/*
garbige collector
4 вида
как работают
*/