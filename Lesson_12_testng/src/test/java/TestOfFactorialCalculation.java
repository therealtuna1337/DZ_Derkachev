import org.example.FindFactorial;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TestOfFactorialCalculation {

    FindFactorial calculator = new FindFactorial();

    @Test
    public void FactorialOfPositiveNumberTest() {
        Assert.assertEquals(calculator.factorial(5), 120);
    }

    @Test
    public void FactorialOfZeroTest() {
        Assert.assertEquals(calculator.factorial(0), 1);
    }

    @Test
    public void FactorialOfNegativeNumberTest() {
        try {
            calculator.factorial(-3);
            Assert.fail();
        } catch (IllegalArgumentException e) {
        }
    }

}