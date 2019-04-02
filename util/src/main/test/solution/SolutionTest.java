package solution;

import com.ftx.solution.kata.Solution;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author puan
 * @date 2019-04-02 11:22
 **/
public class SolutionTest {

    @Test
    public void staticTests() {
        Assert.assertEquals("no one likes this", Solution.whoLikesIt());
        Assert.assertEquals("Peter likes this", Solution.whoLikesIt("Peter"));
        Assert.assertEquals("Jacob and Alex like this", Solution.whoLikesIt("Jacob", "Alex"));
        Assert.assertEquals("Max, John and Mark like this", Solution.whoLikesIt("Max", "John", "Mark"));
        Assert.assertEquals("Alex, Jacob and 2 others like this", Solution.whoLikesIt("Alex", "Jacob", "Mark", "Max"));
    }
}
