import RestaurantDish.Dish;
import RestaurantDish.DishType;
import RestaurantDish.MenuType;

import java.util.Random;

public class RestaurantDishGenerator {

    private static final int MAX_PRICE = 785;

    static final DishType[] dishTypes = {
            new DishType(1,"Starter", "Light dishes to start your meal."),
            new DishType(2,"Main Course", "Hearty dishes that fill you up."),
            new DishType(3,"Dessert", "Sweet treats for the end of your meal."),
            new DishType(4,"Drink", "Beverages to accompany your dishes."),
            new DishType(5,"Snack", "Light snacks for a quick bite."),
            new DishType(6,"Salad", "Fresh salads for a healthy choice."),
            new DishType(7,"Soup", "Warm soups for a comforting meal."),
            // Add more if needed...
    };

     static final MenuType[] menuTypes = {
            new MenuType(1,"Vegetarian"),
            new MenuType(2, "Children's"),
            new MenuType(3, "Seafood"),
            new MenuType(4, "Steakhouse"),
            new MenuType(5, "Pasta"),
            // Add more if needed...
    };

    private static final String[] adjectives = {
            "Delicious",
            "Tasty",
            "Yummy",
            "Spicy",
            "Savory",
            "Sweet",
            "Refreshing",
            "Crispy",
            "Tender",
            "Juicy",
            // Add more if needed...
    };

    private static final String[] mainIngredients = {
            "Chicken",
            "Beef",
            "Tofu",
            "Prawn",
            "Salmon",
            "Mushroom",
            "Potato",
            "Rice",
            "Noodle",
            "Pasta",
            // Add more if needed...
    };

    public static Dish[] generateRandomDishes(int numberOfDishes) {
        Dish[] dishes = new Dish[numberOfDishes];
        Random random = new Random();

        for (int i = 0; i < numberOfDishes; i++) {
            int randomTypeIndex = random.nextInt(dishTypes.length);
            int randomMenuTypeIndex = random.nextInt(menuTypes.length);
            int randomAdjectiveIndex = random.nextInt(adjectives.length);
            int randomMainIngredientIndex = random.nextInt(mainIngredients.length);

            DishType dishType = dishTypes[randomTypeIndex];
            MenuType menuType = menuTypes[randomMenuTypeIndex];
            String adjective = adjectives[randomAdjectiveIndex];
            String mainIngredient = mainIngredients[randomMainIngredientIndex];

            String dishName = adjective + " " + mainIngredient + " " + dishType.typeName();

            float randomPrice = Float.parseFloat(String.format("%.2f", random.nextFloat() * MAX_PRICE));

            dishes[i] = new Dish(i + 1, dishName, dishType, randomPrice, menuType);
        }

        return dishes;
    }
}
