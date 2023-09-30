import java.util.InputMismatchException;
import java.util.Scanner;

public class CalculatorScannerUtil {
        public int getNumOfIterations(){
            System.out.print("Enter the number of iterations: ");
            var scanner = new Scanner(System.in);
            int count;
    
            while(true) {
                try {
                    count = scanner.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("You entered an incorrect character, please try again:");
                    scanner.next();
                }
            }
            return count;
        }
        public boolean shouldTryAgain(){
            var scanner = new Scanner(System.in);
            System.out.println("Do you want to try again? (y/n):");
            var input = scanner.nextLine();
            return input.equalsIgnoreCase("y");
        }
    }