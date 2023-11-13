package christmas.domain;

public class EventManager {

    private Order order;

    public int getMenuPrice(String[] orders) {
        int menuPrice = 0;
        int menuCount = Integer.parseInt(orders[1]);
        for (Menu m : Menu.values()) {
            String menuName = m.getName();
            if (menuName.equals(orders[0])) {
                menuPrice = m.getPrice() * menuCount;
            }
        }
        return menuPrice;
    }

    public String priceFormat(int price) {
        StringBuilder totalPrice = new StringBuilder();
        int count = 0;
        while (price > 0) {
            count++;
            totalPrice.append(price % 10);
            if (count % 3 == 0 && price / 10 > 0) {
                totalPrice.append(",");
            }
            price /= 10;
        }
        return totalPrice.reverse() + "원";
    }

    public void isEventTargetValidate(int totalPrice, Order o) {
        order = o;
        if (totalPrice < 10000) {
            order.setEventTarget(false);
            return;
        }
        order.setEventTarget(true);
    }

    public int getBonusMenu() {
        int totalPrice = order.getTotalPrice();
        return totalPrice / 120000;
    }


}
