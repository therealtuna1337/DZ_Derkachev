import org.example.FindFactorial;
import org.junit.Assert;
import org.junit.Test;

public class FactorialTest {

    FindFactorial calculator = new FindFactorial();

    @Test
    public void FactorialOfPositiveNumberTest() {
        Assert.assertEquals(calculator.factorialCalculator(5), 120);
    }

    @Test
    public void FactorialOfZeroTest() {
        Assert.assertEquals(calculator.factorialCalculator(0), 1);
    }

    @Test
    public void FactorialOfNegativeNumberTest() {
        try {
            calculator.factorialCalculator(-3);
            Assert.fail("java.lang.IllegalArgumentException: Факториал от отрицательного числа вычислить нельзя");
        } catch (IllegalArgumentException e) {
        }
    }

}


