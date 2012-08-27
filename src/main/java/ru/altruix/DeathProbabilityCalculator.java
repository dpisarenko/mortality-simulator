package ru.altruix;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DeathProbabilityCalculator {
    private Map<Integer, Double> deathRatesByAge;
    private Map<Integer, double[]> conditionalProbabilityRangesByAge;

    public void init() {
        final Set<Integer> ages = deathRatesByAge.keySet();

        double totalDeathRate = 0.;

        for (final Integer curAge : ages) {
            totalDeathRate += deathRatesByAge.get(curAge);
        }

        this.conditionalProbabilityRangesByAge =
                new HashMap<Integer, double[]>();

        double curConditionalProbability = 0.;

        for (final Integer curAge : ages) {
            double[] range = new double[2];

            range[0] = curConditionalProbability;

            double deathRate = deathRatesByAge.get(curAge);

            curConditionalProbability += deathRate / totalDeathRate;

            range[1] = curConditionalProbability;

            this.conditionalProbabilityRangesByAge.put(curAge, range);
        }
    }

    public double[] getConditionalProbabilityRange(final int aAge) {
        return this.conditionalProbabilityRangesByAge.get(aAge);
    }

    public void setDeathRatesByAge(final Map<Integer, Double> aDeathRatesByAge) {
        this.deathRatesByAge = aDeathRatesByAge;
    }
}