package ru.altruix;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppTest.class);

    @Test
    public void test() {
        try {
            final App app = new App();

            app.run();
            
            Assert.assertTrue(app.getDeaths() > 0);
        } catch (final Throwable throwable) {
            LOGGER.error("", throwable);
            Assert.fail(throwable.getMessage());
        }
    }
}
