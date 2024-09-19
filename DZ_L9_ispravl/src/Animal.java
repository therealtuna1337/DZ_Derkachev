public abstract class Animal {
    private String name;
    private int distance;
    private static int count;

    public void run() {
        System.out.println(name + " Пробежал " + distance + " м");
    }
    public void swim() {
        System.out.println(name + " Проплыл " + distance + " м");
    }
    public Animal() {
        count++;
    }
    public static int getCount() {
        return count;
    }

}
