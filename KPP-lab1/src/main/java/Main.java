import Calculator.SeriesCalculator;

public class Main {
    public static void main(String[] arg) {
        var scannerUtil = new CalculatorScannerUtil();
        do {
            int count = scannerUtil.getNumOfIterations();
            var result = SeriesCalculator.calculate(count);
            System.out.println("Result: " + result.toString());
        } while (scannerUtil.shouldTryAgain());
    }
}
