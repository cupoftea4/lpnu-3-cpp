import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import RestaurantDish.Dish;

public class RestaurantDishStdRunner {
    private static final Scanner scanner = new Scanner(System.in);

    public static void run() {
        System.out.println("Welcome to the Restaurant!");

        int numberOfDishes = 0;
        while (numberOfDishes <= 0) {
            System.out.print("Enter the number of dishes: ");
            if (scanner.hasNextInt()) {
                numberOfDishes = scanner.nextInt();
                if (numberOfDishes <= 0) {
                    System.out.println("It's a restaurant, not some kind of poor diner");
                }
            } else {
                System.out.println("Invalid input. Please enter a positive integer.");
                scanner.next();
            }
        }
        scanner.nextLine();

        RestaurantDishes dishes = new RestaurantDishes(numberOfDishes);
        chooseOperation(dishes);
    }

    private static void chooseOperation(RestaurantDishes dishes) {
        while (true) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Sort Dishes");
            System.out.println("2. Find Dishes by Menu Type");
            System.out.println("3. View All Dishes");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1/2/3/4): ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    sortAndPrint(dishes);
                    break;
                case 2:
                    findAndPrint(dishes);
                    break;
                case 3:
                    print(dishes.getDishes());
                    break;
                case 4:
                    finish();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
            }
        }
    }

    private static void sortAndPrint(RestaurantDishes dishes) {
        System.out.println("\nSorting Options:");
        System.out.println("1. Sort by Name");
        System.out.println("2. Sort by Type");
        System.out.println("3. Sort by Menu Type");
        System.out.println("4. Sort by Price");
        System.out.print("Enter your sorting choices (e.g., '1 2 4' for Name, Type, and Price): ");
        String sortingChoices = scanner.nextLine();

        System.out.print("Do you want to reverse the sorting order? (yes/no): ");
        boolean reverse = scanner.nextLine().equalsIgnoreCase("yes");

        String[] sortingChoiceArray = sortingChoices.split(" ");
        Arrays.sort(sortingChoiceArray);

        int[] chosenSortingChoices = new int[sortingChoiceArray.length];
        for (int i = 0; i < sortingChoiceArray.length; i++) {
            chosenSortingChoices[i] = Integer.parseInt(sortingChoiceArray[i]);
        }

        dishes.sort(getComparator(chosenSortingChoices, !reverse));
        System.out.println("\nSorted Dishes:");
        print(dishes.getDishes());
    }

    private static void findAndPrint(RestaurantDishes dishes) {
        System.out.println("\nAvailable Menu Types:");

        var menuTypes = RestaurantDishGenerator.menuTypes;
        for (var type : menuTypes) {
            System.out.println(type.Id() + ". " + type.name());
        }

        System.out.print("Enter the type(s) of dishes to find (' ' separated): ");
        String typeInput = scanner.nextLine();
        String[] typeIds = typeInput.split(" ");
        Set<Integer> selectedIds = new HashSet<>();
        for (String typeId : typeIds) {
            selectedIds.add(Integer.parseInt(typeId));
        }

        System.out.println("\nMatching Dishes by Menu Type:");
        print(dishes.findByMenuTypeIds(selectedIds));
    }

    private static Comparator<Dish> getComparator(int[] sortingChoices, boolean isAsc) {
        Comparator<Dish> tempComp, result = null;
        for (int choice : sortingChoices) {
            tempComp = switch (choice) {
                case 1 -> DishComparators.byName();
                case 2 -> DishComparators.byDishType();
                case 3 -> DishComparators.byMenuType();
                case 4 -> DishComparators.byPrice();
                default -> null;
            };
            result = result != null ? DishComparators.byTwoComparators(result, tempComp) : tempComp;
        }
        if (!isAsc) {
            assert result != null;
            result = result.reversed();
        }
        return result;
    }

    private static void print(Dish[] dishes) {
        System.out.println("\nAll Dishes:");
        System.out.printf("%-5s | %-30s | %-20s | %-10s | %-10s%n", "ID", "Name", "Type", "Price", "Menu Type");
        System.out.println("-".repeat(85));

        for (Dish dish : dishes) {
            System.out.println(dish);
        }
    }

    private static void finish() {
        System.out.println("It was great to see you! Good luck!");
        scanner.close();
        System.exit(0);
    }
}
