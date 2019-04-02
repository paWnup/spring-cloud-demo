package solution;

import com.ftx.solution.kata.DRoot;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author puan
 * @date 2019-03-28 14:12
 **/
public class DRootTest {

    @Test
    public void Tests() {
        Assert.assertEquals("Nope!", 7, DRoot.digital_root(16));
        Assert.assertEquals("Nope!", 6, DRoot.digital_root(456));
        Assert.assertEquals("Nope!", 4, DRoot.digital_root(4567));
    }
}
