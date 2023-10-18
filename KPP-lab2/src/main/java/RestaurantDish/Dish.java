package RestaurantDish;

public record Dish(int id, String name, DishType type, float price, MenuType menuType) implements Comparable<Dish> {

    @Override
    public String toString() {
        // Similar to the example you provided
        return String.format("%-5d | %-30s | %-20s | %-10.2f | %-15s",
                id, name, type.typeName(), price, menuType.name());
    }

    @Override
    public int compareTo(Dish otherDish) {
        if (otherDish == null) {
            return 1;         }

        return Double.compare(this.price, otherDish.price);
    }
}
