package courier;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {
    public Courier random() {
        return new Courier(RandomStringUtils.randomAlphanumeric(10), "123456789", "Naruto Uzumaki");
    }
}