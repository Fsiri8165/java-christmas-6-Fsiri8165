package christmas.domain;

public class EventManager {

    private Order order;
    public int getBonusMenu(Order o) {
        order = o;
        int totalPrice = order.getTotalPrice();
        return totalPrice / 120000;
    }
}
