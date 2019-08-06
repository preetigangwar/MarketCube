package Utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomGenerator {

    /**
     * Generates random Number.
     *
     * @param length length of random number to be generated
     */
    public static String randomInteger(Integer length) {
        return RandomStringUtils.randomNumeric(length);
    }
    public static String randomInteger(Integer min,Integer max) {
        return RandomStringUtils.randomNumeric(min,max);
    }


    /**
     * Generates random String.
     *
     * @param length length of random characters to be generated
     */
    public static String randomString(Integer length) {
        return RandomStringUtils.random(length, true, false);
    }

    public static String randomString(Integer min,Integer max) {
        return RandomStringUtils.randomAlphabetic(min,max);
    }
    /**
     * Generates random alphanumeric String.
     *
     * @param length length of random alphanumeric characters to be generated
     */
    public static String randomAlphanumeric(Integer length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public static String randomAlphanumeric(Integer min,Integer max) {
        return RandomStringUtils.randomAlphanumeric(min,max);
    }
    /**
     * Generates random alphabetic String.
     *
     * @param length length of random alphabetic characters to be generated
     */
    public static String randomAlphabetic(Integer length) {
        return RandomStringUtils.randomAlphabetic(length);
    }
    public static String randomAlphabetic(Integer min,Integer max) {
        return RandomStringUtils.randomAlphabetic(min,max);
    }



}
