public class Dog extends Animal {
    String name;
    public static int countDog;

    Dog(String name){
        super();
        countDog++;
        this.name = name;
    }

    void run(int distance) {
        if (distance > 500) {
            System.out.println("Собаки не могут бежать больше 500 м");
        } else if (distance < 0) {
            System.out.println("Не может быть отрицательного расстояния");
        } else {
            System.out.println("Собака " + name + " пробежала " + distance + " м");
        }

    }

    void swim(int distance) {
        if (distance > 10) {
            System.out.println("Собаки не могут плыть больше 10 м");
        } else if (distance < 0) {
            System.out.println("Не может быть отрицательного расстояния");
        } else {
            System.out.println("Собака " + name + " проплыла " + distance + " м");
        }
    }

    public static int getCount() {
        return countDog;
    }
}