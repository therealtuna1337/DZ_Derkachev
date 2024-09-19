public class Bowl {
    private static int food;
    Bowl(int food) {
        this.food = food;
    }
    void addFood(int amount) {
        food = food + amount;
    }
    static void eatFood(int hunger) {
        food -= hunger;
    }

    public int getFood() {
        return food;
    }

}