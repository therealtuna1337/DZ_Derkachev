class Task2Rectangle implements Task2.Figure {
    private final double width;
    private final double height;
    private final String backgroundColor;
    private final String borderColor;

    public Task2Rectangle(double width, double height, String backgroundColor, String borderColor) {
        this.width = width;
        this.height = height;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
    }

    @Override
    public double calcPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public double calcArea() {
        return width * height;
    }

    public String toString() {
        return "Прямоугольник: " + "Периметр = " + calcPerimeter() + "; " +
                "Площадь = " + calcArea() + "; " +
                "Цвет фона = " + backgroundColor + "; " +
                "Цвет границы = " + borderColor;
    }
}
