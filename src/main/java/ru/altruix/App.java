package ru.altruix;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
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
        final List<Human> returnValue = new LinkedList<Human>();
        final InputStream inputStream =
                Thread.currentThread()
                        .getContextClassLoader()
                        .getResourceAsStream(
                                aPath);

        try {
            final List<String> lines = IOUtils.readLines(inputStream);

            for (final String curLine : lines) {
                final StringTokenizer tokenizer =
                        new StringTokenizer(curLine, ";");

                final String idAsString = tokenizer.nextToken();
                final String ageAsString = tokenizer.nextToken();

                long id = -1;
                try {
                    id = Long.parseLong(idAsString);
                } catch (final NumberFormatException exception) {
                    LOGGER.error("Cannot parse ID '{}'", idAsString);
                }

                int age = -1;
                try {
                    age = Integer.parseInt(ageAsString);
                } catch (final NumberFormatException exception) {
                    LOGGER.error("Cannot parse age '{}'", ageAsString);
                }

                if ((id > 0) && (age >= 0)) {
                    final Human human = new Human();

                    human.setAge(age);
                    human.setId(id);

                    returnValue.add(human);
                }
            }
        } catch (final IOException exception) {
            LOGGER.error("", exception);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
        return returnValue;
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
