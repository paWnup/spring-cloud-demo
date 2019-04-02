package solution;

import com.ftx.solution.kata.Max;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author puan
 * @date 2019-04-02 10:05
 **/
public class MaxSequenceTest {

    @Test
    public void testEmptyArray() {
        Assert.assertEquals("Empty arrays should have a max of 0", 0,
                Max.sequence(new int[]{}));
    }

    @Test
    public void testExampleArray() {
        Assert.assertEquals("Example array should have a max of 6", 6,
                Max.sequence(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }
}
