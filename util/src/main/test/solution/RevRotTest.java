package solution;

import com.ftx.solution.kata.RevRot;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author puan
 * @date 2019-04-09 11:33
 **/
public class RevRotTest {

    private static void testing(String actual, String expected) {
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test() {
        System.out.println("Fixed Tests: revRot");
        testing(RevRot.revRot("1234", 0), "");
        testing(RevRot.revRot("", 0), "");
        testing(RevRot.revRot("1234", 5), "");
        testing(RevRot.revRot("733049910872815764", 5), "330479108928157");
    }
}
