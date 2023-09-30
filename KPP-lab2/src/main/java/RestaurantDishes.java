import RestaurantDish.Dish;
import lombok.Getter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;

@Getter
public class RestaurantDishes {
    private final Dish[] dishes;

    public RestaurantDishes(int count) {
        this.dishes = RestaurantDishGenerator.generateRandomDishes(count);
    }

    public void sort(Comparator<Dish> comparator) {
        Arrays.sort(dishes, comparator);
    }

    public Dish[] findByType(Set<Integer> typeIds) {
        return Arrays.stream(dishes)
                .filter(dish -> typeIds.contains(dish.type().ID()))
                .toArray(Dish[]::new);
    }
}
