package solution;

import com.ftx.solution.kata.Order;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author puan
 * @date 2019-04-04 10:03
 **/
public class OrderTest {

    @Test
    public void test1() {
        Assert.assertThat(Order.order("is2 Thi1s T4est 3a"), CoreMatchers.equalTo("Thi1s is2 3a T4est"));
    }

    @Test
    public void test2() {
        Assert.assertThat(Order.order("4of Fo1r pe6ople g3ood th5e the2"), CoreMatchers.equalTo("Fo1r the2 g3ood 4of th5e pe6ople"));
    }

    @Test
    public void test3() {
        Assert.assertThat("Empty input should return empty string", Order.order(""), CoreMatchers.equalTo(""));
    }
}
