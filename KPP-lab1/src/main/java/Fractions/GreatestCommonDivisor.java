package Fractions;

public class GreatestCommonDivisor {
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
