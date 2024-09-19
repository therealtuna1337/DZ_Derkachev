class Task2Circle implements Task2.Figure {
    private final double radius;
    private final String backgroundColor;
    private final String borderColor;

    public Task2Circle(double radius, String backgroundColor, String borderColor) {
        this.radius = radius;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
    }

    public double calcPerimeter() {
        return 2 * Math.PI * radius;
    }

    public double calcArea() {
        return Math.PI * radius * radius;
    }

    public String toString() {
        return "Круг: " + "Периметр = " + calcPerimeter() + "; " +
                "Площадь = " + calcArea() + "; " +
                "Цвет фона = " + backgroundColor + "; " +
                "Цвет границы = " + borderColor;
    }
}