package ru.altruix;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 * 
 */
public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public void run() {
        LOGGER.info("Sample application");

        final Map<Integer, Double> deathRatesByAge =
                readDeathRates("death-rates.csv");

        final List<Human> people = readPeople("population.csv");

        final DeathProbabilityCalculator calculator =
                new DeathProbabilityCalculator();

        calculator.setDeathRatesByAge(deathRatesByAge);
        calculator.init();

        for (final Human curPerson : people) {
            final double experiment = Math.random();

            final double[] range =
                    calculator.getConditionalProbabilityRange(curPerson
                            .getAge());

            boolean death;
            if ((range[0] >= experiment) && (experiment <= range[1])) {
                death = true;
            } else {
                death = false;
            }

            LOGGER.debug("Age: {}, death: {}",
                    new Object[] { curPerson.getAge(), death });
        }
    }

    private List<Human> readPeople(final String aPath) {
        // TODO Auto-generated method stub
        return null;
    }

    private Map<Integer, Double> readDeathRates(final String aPath) {
        // TODO Auto-generated method stub
        return null;
    }

    public static void main(final String[] aArgs) {
        final App app = new App();

        app.run();
    }
}
