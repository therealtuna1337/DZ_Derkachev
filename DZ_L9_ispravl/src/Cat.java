public class Cat extends Animal {
    String name;
    int hunger; // количество еды, необходимое для того чтобы наесться
    boolean satiety; // сытость. true = сыт
    public static int countCat;


    Cat(String name, int hunger, boolean satiety) {
        super();
        countCat++;
        this.name = name;
        this.hunger = hunger;
        this.satiety = satiety;
    }

    public void run(int distance) {
        if (distance > 200) {
            System.out.println("Кошки не могут бежать более 200 м");
        } else if (distance < 0) {
            System.out.println("Не может быть отрицательного расстояния");
        } else {
            System.out.println("Кошка " + name + " пробежала " + distance + " м");
        }
    }

    public void swim(int distance) {
        System.out.println("Коты не умеют плавать");
    }

    public void eat(Bowl b) {
        Bowl.eatFood(getHunger());
    }

    public static int getCount() {
        return countCat;
    }

    public int getHunger() {
        return hunger;
    }
}
