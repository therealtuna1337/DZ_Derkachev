public class Main {
    public static void main(String[] args) {

        Dog kurtka = new Dog("Куртка Бэйн");
        kurtka.run(250);
        kurtka.swim(9);


        Cat[] catArr = new Cat[3];
        {
            catArr[0] = new Cat("Мурка", 3, false);
            catArr[1] = new Cat("Чебурек", 5, false);
            catArr[2] = new Cat("Пухляш", 25, false);
        }

        catArr[1].run(100);
        catArr[1].swim(10);

        Bowl bowl = new Bowl(30);

        for (int i = 0; i < catArr.length; i++) {
            if (catArr[i].satiety == false && catArr[i].hunger < bowl.getFood()) {
                catArr[i].eat(bowl);
                catArr[i].satiety = true;
                System.out.println("\n" + catArr[i].name + " поел " + catArr[i].hunger + " еды");
            } else {
                System.out.println("\n" + catArr[i].name + " не поел");
            }
            if (catArr[i].satiety == true) {  // поскольку в задании сказано вывести информацию о сытости,
                // я сделал возможность отображения сытости, если кот не ел (если он был сыт изначально).
                // но в задании так же указано, что изначально все коты должны быть голодными, поэтому оставил их всех голодными
                System.out.println(catArr[i].name + " сыт");
            }
            System.out.println("осталось еды в миске: " + bowl.getFood());

        }

        bowl.addFood(10);
        System.out.println("\nДобавлена еда. теперь в миске " + bowl.getFood() + " еды");

        System.out.println("\nКоличество котов: " + Cat.getCount());
        System.out.println("Количество собак: " + Dog.getCount());
        System.out.println("Всего животных: " + Animal.getCount());

        System.out.println("\n~~~ЗАДАНИЕ НОМЕР 2~~~\n");
        Task2.Figure circle = new Task2Circle(15, "yellow", "pink");
        Task2.Figure rectangle = new Task2Rectangle(10, 2, "grey", "purple");
        Task2.Figure triangle = new Task2Triangle(4, 4, 7, "black", "orange");

        System.out.println(circle);
        System.out.println(rectangle);
        System.out.println(triangle);
    }
}