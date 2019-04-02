package solution;

import com.ftx.solution.kata.TriangleTester;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author puan
 * @date 2019-04-01 15:13
 **/
public class TriangleTesterTest {

    @Test
    public void publicTests() {
        Assert.assertTrue(TriangleTester.isTriangle(1, 2, 2));
        Assert.assertFalse(TriangleTester.isTriangle(7, 2, 2));
    }
}
