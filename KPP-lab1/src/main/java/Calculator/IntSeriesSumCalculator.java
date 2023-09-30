package Calculator;

import Fractions.IFraction;
import Fractions.IntFraction;

public class IntSeriesSumCalculator implements ISeriesSumCalculator {
    @Override
    public IFraction calculate(int count) {
        IntFraction result = new IntFraction(0, 1);
        for (int i = 1; i <= count; ++i) {
            var fraction = new IntFraction(1, i);
            var nonReducedFraction = result.plus(fraction);
            result = nonReducedFraction.reduce();
        }
        return result;
    }
}
