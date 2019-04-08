package solution;

import com.ftx.solution.kata.Line;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author puan
 * @date 2019-04-08 16:47
 **/
public class ListTests {
    @Test
    public void test1() {
        String[] names = new String[]{"Sheldon", "Leonard", "Penny", "Rajesh", "Howard"};
        int n = 1;
        Assert.assertEquals("Sheldon", Line.WhoIsNext(names, n));
    }

    @Test
    public void test2() {
        String[] names = new String[]{"Sheldon", "Leonard", "Penny", "Rajesh", "Howard"};
        int n = 6;
        Assert.assertEquals("Sheldon", Line.WhoIsNext(names, n));
    }

    @Test
    public void test3() {
        String[] names = new String[]{"Sheldon", "Leonard", "Penny", "Rajesh", "Howard"};
        int n = 52;
        Assert.assertEquals("Penny", Line.WhoIsNext(names, n));
    }
}
