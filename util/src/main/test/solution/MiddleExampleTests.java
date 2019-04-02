package solution;

import com.ftx.solution.kata.MiddleExample;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author puan
 * @date 2019-04-01 15:29
 **/
public class MiddleExampleTests {

    @Test
    public void evenTests() {
        Assert.assertEquals("es", MiddleExample.getMiddle("test"));
        Assert.assertEquals("dd", MiddleExample.getMiddle("middle"));
    }

    @Test
    public void oddTests() {
        Assert.assertEquals("t", MiddleExample.getMiddle("testing"));
        Assert.assertEquals("A", MiddleExample.getMiddle("A"));
    }
}
