public class Task2 {

    interface Figure {
        default double calcPerimeter() {
            return 0;
        }

        default double calcArea() {
            return 0;
        }
    }

    public void main(String[] args) {
        Figure circle = new Task2Circle(15, "yellow", "pink");
        Figure rectangle = new Task2Rectangle(10, 2, "grey", "purple");
        Figure triangle = new Task2Triangle(4, 4, 7, "black", "orange");

        System.out.println(circle);
        System.out.println(rectangle);
        System.out.println(triangle);
    }
}
