package Calculator;

import Fractions.IFraction;

public class SeriesCalculator {
    public static IFraction calculate(int count) {
        if (count >= Constants.MIN_BIGINT_ITERATIONS_NUM) {
            return new BigIntegerSeriesSumCalculator().calculate(count);
        } else {
            return new IntSeriesSumCalculator().calculate(count);
        }
    }
}