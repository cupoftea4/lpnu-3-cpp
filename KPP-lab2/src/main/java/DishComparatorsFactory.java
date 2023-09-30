import RestaurantDish.Dish;

import java.util.Comparator;

public class DishComparatorsFactory {

    private static class DishNameComparator implements Comparator<Dish> {
        @Override
        public int compare(Dish o1, Dish o2) {
            return o1.name().compareTo(o2.name());
        }
    }

    public static Comparator<Dish> byName() {
        return new DishNameComparator();
    }

    public static Comparator<Dish> byPrice() {
        return (d1, d2) -> Float.compare(d1.price(), d2.price());
    }

    public static Comparator<Dish> byMenuType(){
        return new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return o1.menuType().name().compareTo(o2.menuType().name());
            }
        };
    }

    public static Comparator<Dish> byDishType() {
        return Comparator.comparing(o -> o.type().typeName());
    }

    public static Comparator<Dish> byTwoComparators(Comparator<Dish> a, Comparator<Dish> b) {
        return a.thenComparing(b);
    }
}
