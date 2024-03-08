import enums.Locations;
import enums.MasksType;
import enums.TypeOfGun;
import interfaces.FuncInter;

public class Main {
    public static void main(String[] args) {
        Dunno person1 = new Dunno("тупенький", (int) (Math.random() * 10000));
        Goat person2 = new Goat("козлик", (int) (Math.random() * 10000));
        Guns pistol = new Firearms("огромный пистолет");
        Guns torch = new SteelArms("потайной электрпический фонарь");
        pistol.setType(TypeOfGun.PISTOL);
        pistol.setPrice(132);
        torch.setPrice(1);
        torch.setType(TypeOfGun.TORCH);
        person1.getMoney();
        person1.move(Locations.SHOP);
        person2.move(Locations.SHOP);
        person1.buy(pistol);
        try {
            person1.use(pistol);
        } catch (AvailabilityException ex) {
            System.out.println(ex.getMessage());
        }
        person2.buy(pistol);
        person1.buy(torch);
        person1.getItems();
        person2.getItems();
        person1.move(Locations.STREET);
        person2.move(Locations.STREET);
        try {
            person2.use(torch);
        } catch (AvailabilityException ex) {
            System.out.println(ex.getMessage());
        }
        //person1.isTrue();
        FuncInter<Goat, Dunno> converter = x -> new Dunno(x.getName(), x.getValue(), x.getLastItem());
        Dunno personConv = converter.convert(person2);
        System.out.println(personConv);
        System.out.println(person2);

        class PolicesUnits extends Guns {
            public PolicesUnits() {
                super("default");
            }

            public PolicesUnits(String name) {
                super(name);
            }

            @Override
            public String describe() {
                return " использует полицейское снаряжение в виде: ";
            }
        }
        System.out.println(new PolicesUnits() {
            public String writeOff() {
                return "часть оружия списали";
            }
        }.writeOff());


        Shop shop = new Shop("magazik");
        Shop.ShowCase vitrina = shop.new ShowCase("витрина");
        Shop.Dummy choochelo2 = new Shop.Dummy("полицейский");
        Masks helmet = new Masks("медная каска");
        helmet.setType(MasksType.HELMET);
        choochelo2.addMask(helmet);
        Guns baton = new PolicesUnits("дубинка");
        baton.setType(TypeOfGun.BATON);
        choochelo2.setLeftItem(baton);
        choochelo2.move(Locations.RIGHTCORNER);
        Shop.Dummy choochelo1 = new Shop.Dummy("грабитель");
        choochelo1.stealing(Locations.CASHBOX);
        choochelo1.setLeftItem(pistol);
        choochelo1.setRightItem(torch);
        Masks neck = new Masks("пестрый клетчатый платок");
        neck.setType(MasksType.SHAWL);
        choochelo1.addNeck(neck);
        Masks cap = new Masks("клетчатая кепка с широким козырьком");
        cap.setType(MasksType.CAP);
        choochelo1.addHelmet(cap);
        String pants = "клечатые брюки";
        choochelo1.addPants(pants);
        Masks mask = new Masks("черная маска");
        mask.setType(MasksType.MASK);
        choochelo1.addMask(mask);
        System.out.println(choochelo1.toString());
        System.out.println(choochelo2.toString());

        System.out.println(Dunno.testt(4));
    }
}
/*
операция которая кладет в стек значения
*/