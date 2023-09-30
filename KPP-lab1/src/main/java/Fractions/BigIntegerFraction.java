package Fractions;

import lombok.Getter;

import java.math.BigInteger;

@Getter
public class BigIntegerFraction implements IFraction {
    BigInteger numerator;
    BigInteger denominator;

    public BigIntegerFraction(BigInteger numerator, BigInteger denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Override
    public BigIntegerFraction plus(IFraction right) {
        if (!(right instanceof BigIntegerFraction other)) {
            throw new IllegalArgumentException("Unsupported Fractions.Fraction type");
        }
        var commonDenominator = this.denominator.multiply(other.getDenominator());
        var newNumerator = (this.numerator.multiply(other.getDenominator()))
                .add(other.getNumerator().multiply(this.denominator));
        return new BigIntegerFraction(newNumerator, commonDenominator);
    }

    @Override
    public BigIntegerFraction reduce() {
        var gcd = this.numerator.gcd(this.denominator);
        return this.divide(gcd);
    }

    public BigIntegerFraction divide(BigInteger num) {
        return new BigIntegerFraction(this.numerator.divide(num), this.denominator.divide(num));
    }

    @Override
    public String toString() {
        return numerator.toString() + "/" + denominator.toString();
    }
}
