import org.junit.Test;
import sun.jvm.hotspot.utilities.AssertionFailure;

public class JavaTest {
    private static boolean assertionsEnabled = false;

    @Test
    public void testAssert() {
        boolAssert(getValue());
    }

    private static boolean getValue() {
        System.out.println("valued");
        return 5 / 0 == 0;
    }


    private static void boolAssert(boolean b) {
        if(assertionsEnabled && !b) {
            throw new AssertionFailure("");
        }
    }
}
