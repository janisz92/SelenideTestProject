package helpers;

import org.apache.commons.lang3.RandomStringUtils;

public class TestCasesHelper {

    public static String generateValidMail() {
        return  RandomStringUtils.randomAlphanumeric(20).concat("@test.com");
    }

}
