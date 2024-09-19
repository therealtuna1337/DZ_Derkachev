class Task2Triangle implements Task2.Figure {
    private final double side1;
    private final double side2;
    private final double side3;
    private final String backgroundColor;
    private final String borderColor;

    public Task2Triangle(double side1, double side2, double side3, String backgroundColor, String borderColor) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
    }

    @Override
    public double calcPerimeter() {
        return side1 + side2 + side3;
    }

    @Override
    public double calcArea() {
        double semiperimeter = calcPerimeter() / 2;
        return Math.sqrt(semiperimeter * (semiperimeter - side1) * (semiperimeter - side2) * (semiperimeter - side3));
    }

    public String toString() {
        return "Треугольник: " + "Периметр = " + calcPerimeter() + "; " +
                "Площадь = " + calcArea() + "; " +
                "Цвет фона = " + backgroundColor + "; " +
                "Цвет границы = " + borderColor;
    }
}