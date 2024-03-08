import java.util.Scanner;

public class Practice {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Human person1 = new Human("bob",191);
        Human.printPeopleNumber();
        Human person2 = new Human("bob",191);
        Human.printPeopleNumber();

    }
}

class Human {
    private String name;
    private int age;

    public static int countPeople;

    public Human() {
        this.name = "BaseName";
        this.age = 0;
        countPeople++;
    }

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
        countPeople++;
    }

    public void setName(String name) {
        if (name.isEmpty()) {
            System.out.println("Всё хуйня , давай по-новой");
        } else {
            this.name = name;
        }
    }

    public String getName() {
        return this.name;
    }

    public void setAge(int age) {
        if (age < 0) {
            System.out.println("Всё хуйня, давай по-новой");
        } else {
            this.age = age;
        }
    }

    public Integer getAge() {
        return this.age;
    }

    public void getInfo() {
        System.out.println(this.name + " " + this.age);
    }


    public static void printPeopleNumber(){
        System.out.println(countPeople);
    }
}