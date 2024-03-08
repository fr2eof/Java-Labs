import enums.Locations;
import interfaces.Move;
import interfaces.Steal;

public class Shop {
    private final String shopName;

    public Shop(String shopName) {
        this.shopName = shopName;
    }

    public class ShowCase {
        private final String ShowCaseName;

        public ShowCase(String ShowCaseName) {
            this.ShowCaseName = ShowCaseName;
        }

        public void show(Firearms item) {
            System.out.println("Зачетный ствол от зачетного производителя");
        }

        public void show(SteelArms item) {
            System.out.println("Отличный вариант для техаской резни");
        }

        public void show(ThiefsUnits item) {
            System.out.println("Хочешь что-то украсть? - тогда это тебе точно понадобиться  ");
        }
    }

    public static class Dummy implements Move, Steal {
        private final String name;
        private Masks helmet;
        private Masks mask;
        private Masks neck;
        private Guns leftItem;
        private Guns rightItem;
        private String pants;

            public Dummy(String name) {
                this.name = name;
            }

            public Dummy() {
                this.name = "известное в узких кругах";
            }

            public void addNeck(Masks neck) {
                this.neck = neck;
                System.out.println("Шея чучела " + getName() + " повязана " + this.neck.getName());
            }

            public Masks getNeck() {
                return this.neck;
            }

            public void addPants(String pants) {
                this.pants = pants;
                System.out.println(this.pants + " плотно облегали ноги чечела " + getName());
            }

            public String getPants() {
                return this.pants;
            }

            public void addHelmet(Masks helmet) {
                this.helmet = helmet;
                System.out.println("На голове чучала " + getName() + " надет " + this.helmet.getName());
            }

            public void addMask(Masks mask) {
                this.mask = mask;
                System.out.println("Лицо чучела " + getName() + " закрывала " + this.mask.getName());
            }

            public Masks getMask() {
                return this.mask;
            }

            public Masks getHelmet() {
                return this.helmet;
            }

            public String getName() {
                return this.name;
            }

            public Guns getLeftItem() {
                return this.leftItem;
            }

            public void setLeftItem(Guns leftItem) {
                this.leftItem = leftItem;
                System.out.println("В левой руке чучала " + getName() + " находится " + this.leftItem.getName());
            }

            public Guns getRightItem() {
                return this.rightItem;
            }

            public void setRightItem(Guns rightItem) {
                this.rightItem = rightItem;
                System.out.println("В правой руке чучала " + getName() + " находится " + this.rightItem.getName());
            }

            public void move(Locations Loc) {
                System.out.println("Чучело " + getName() + " переместили в " + Loc);
            }

            public void stealing(Locations Loc) {
                System.out.println("Чучело " + getName() + " крадется к " + Loc);
            }

            @Override
            public String toString() {
                return "Чучело " + getName() + " очень похож на настояшего живого коротышку";
            }
        }
}