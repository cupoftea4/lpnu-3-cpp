package Fractions;

import lombok.Getter;

@Getter
public class IntFraction implements IFraction {
    int numerator;
    int denominator;

    public IntFraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Override
    public IntFraction plus(IFraction right) {
        if (!(right instanceof IntFraction other)) {
            throw new IllegalArgumentException("Unsupported Fractions.Fraction type");
        }
        var commonDenominator = this.denominator * other.getDenominator();
        var newNumerator = (this.numerator * other.getDenominator()) + (other.getNumerator() * this.denominator);
        return new IntFraction(newNumerator, commonDenominator);
    }

    @Override
    public IntFraction reduce() {
        int gcd = GreatestCommonDivisor.gcd(this.numerator, this.denominator);
        return this.divide(gcd);
    }

    public IntFraction divide(int num){
        return new IntFraction(numerator / num,denominator / num);
    }

    @Override
    public String toString(){
        return numerator + "/" + denominator;
    }
}
