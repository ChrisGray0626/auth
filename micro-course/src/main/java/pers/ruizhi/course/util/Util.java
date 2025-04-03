package pers.ruizhi.course.util;

/**
 * @Description
 * @Author RuiZhi Li
 * @Date 2024/7/29
 */
public class Util {

    // Generate a random number between 0 and 10 with one decimal place
    public static double generateRandom10() {
        // Generate a random number between 0 and 10
        double random = Math.random() * 10;
        // Keep one decimal place
        random = Math.round(random * 10.0) / 10.0;

        return random;
    }
}
