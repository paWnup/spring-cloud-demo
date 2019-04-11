package solution;

import com.ftx.solution.kata.PaginationHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author puan
 * @date 2019-04-11 11:00
 **/
public class PaginationHelperTest {

    @Test
    public void testSomething() {
        PaginationHelper helper = new PaginationHelper(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f'), 4);
        Assert.assertEquals(2, helper.pageCount());
        Assert.assertEquals(6, helper.itemCount());
        Assert.assertEquals(4, helper.pageItemCount(0));
        Assert.assertEquals(2, helper.pageItemCount(1));
        Assert.assertEquals(-1, helper.pageItemCount(2));
        Assert.assertEquals(1, helper.pageIndex(5));
        Assert.assertEquals(0, helper.pageIndex(2));
        Assert.assertEquals(-1, helper.pageIndex(20));
        Assert.assertEquals(-1, helper.pageIndex(-10));
    }
}
