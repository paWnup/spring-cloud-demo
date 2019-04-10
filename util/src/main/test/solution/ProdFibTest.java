package solution;

import com.ftx.solution.kata.ProdFib;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author puan
 * @date 2019-04-10 14:13
 **/
public class ProdFibTest {

    @Test
    public void test1() {
        long[] r = new long[]{55, 89, 1};
        Assert.assertArrayEquals(r, ProdFib.productFib(4895));
    }

    @Test
    public void test2() {
        long[] r = new long[]{89, 144, 0};
        Assert.assertArrayEquals(r, ProdFib.productFib(5895));
    }
}
