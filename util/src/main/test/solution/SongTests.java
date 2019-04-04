package solution;

import com.ftx.solution.kata.Dubstep;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author puan
 * @date 2019-04-04 9:47
 **/
public class SongTests {

    @Test
    public void Test1() {
        Assert.assertEquals("ABC", Dubstep.SongDecoder("WUBWUBABCWUB"));
    }

    @Test
    public void Test2() {
        Assert.assertEquals("R L", Dubstep.SongDecoder("RWUBWUBWUBLWUB"));
    }
}
