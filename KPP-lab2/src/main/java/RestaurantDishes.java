import RestaurantDish.Dish;
import lombok.Getter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;
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

    public Dish[] findByMenuTypeIds(Set<Integer> typeIds) {
        List<Dish> filteredDishes = new ArrayList<>();
        for (Dish dish : dishes) {
            if (typeIds.contains(dish.menuType().Id())) {
                filteredDishes.add(dish);
            }
        }
        return filteredDishes.toArray(new Dish[filteredDishes.size()]);
    }

}
