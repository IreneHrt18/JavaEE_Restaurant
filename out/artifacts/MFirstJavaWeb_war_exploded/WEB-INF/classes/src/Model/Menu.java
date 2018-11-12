package Model;

public class Menu {
    private Restaurant restaurant;
    private Dish dish;

    public Menu(Restaurant restaurant, Dish dish) {
        this.restaurant = restaurant;
        this.dish = dish;
    }

    public Menu() {
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }
}
