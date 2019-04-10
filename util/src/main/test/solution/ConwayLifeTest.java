package solution;

import com.ftx.solution.kata.ConwayLife;
import org.junit.Test;

/**
 * @author puan
 * @date 2019-04-10 11:03
 **/
public class ConwayLifeTest {

    @Test
    public void testGlider() {
        int[][][] gliders = {
                {
                        {1, 0, 0},
                        {0, 1, 1},
                        {1, 1, 0}
                },
                {
                        {0, 1, 0},
                        {0, 0, 1},
                        {1, 1, 1}
                }
        };
        System.out.println("Glider");
        int[][] res = ConwayLife.getGeneration(gliders[0], 2);
        System.out.println(res.toString());
    }
}
