package Calculator;

import Fractions.BigIntegerFraction;
import Fractions.IFraction;

import java.math.BigInteger;

public class BigIntegerSeriesSumCalculator implements ISeriesSumCalculator {
    @Override
    public IFraction calculate(int count) {
        var result = new BigIntegerFraction(BigInteger.ZERO, BigInteger.ONE);
        for (int i = 1; i <= count; ++i){
            var fractionToAdd = new BigIntegerFraction(BigInteger.ONE, BigInteger.valueOf(i));
            var nonReducedFraction = result.plus(fractionToAdd);
            result = nonReducedFraction.reduce();
        }
        return result;
    }
}



