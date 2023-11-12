import org.junit.jupiter.api.Test;

public class MathTests {

    @Test
    void testDivideByZero() {
        var a = 1;
        var b = 0;
        var c = a / b;
        System.out.println(c);
    }
}
