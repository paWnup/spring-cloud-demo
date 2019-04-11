package solution;

import com.ftx.solution.kata.Greed;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author puan
 * @date 2019-04-11 10:00
 **/
public class KataTest {

    @Test
    public void testExample() {
        Assert.assertEquals("Score for [5,1,3,4,1] must be 250:", 250, Greed.greedy(new int[]{5, 1, 3, 4, 1}));
        Assert.assertEquals("Score for [1,1,1,3,1] must be 1100:", 1100, Greed.greedy(new int[]{1, 1, 1, 3, 1}));
        Assert.assertEquals("Score for [2,4,4,5,4] must be 450:", 450, Greed.greedy(new int[]{2, 4, 4, 5, 4}));
    }
}
