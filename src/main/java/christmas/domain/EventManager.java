package christmas.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class EventManager {

    private Order order;
    private int totalPrice;

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

    public String getTotalPrice(String[][] orderHistory, Order o) {
        order = o;
        totalPrice = 0;
        for (String[] orders : orderHistory) {
            totalPrice += getMenuPrice(orders);
        }
        order.setTotalPrice(totalPrice);
        isEventTargetValidate();
        return priceFormat(totalPrice);
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

    public void isEventTargetValidate() {
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

    public String checkWeekend(int dateOfVisit) {
        LocalDate date = LocalDate.of(2023, 12, dateOfVisit);
        DayOfWeek visitDayOfWeek = date.getDayOfWeek();
        if (visitDayOfWeek == DayOfWeek.SATURDAY
                || visitDayOfWeek == DayOfWeek.SUNDAY) {
            return "주말";
        }
        return "평일";
    }

    public int getDateSalePrice(String divideWeek) {
        String[][] orderHistory = order.getOrderHistory();
        int eventMenuCount = 0;
        for (String[] orders : orderHistory) {
            eventMenuCount += isWeekend(orders[0], divideWeek);
        }
        return 2023 * eventMenuCount;
    }

    public int isWeekend(String menuName, String divideWeek) {
        if (divideWeek.equals("주말")) {
            return weekendSaleCount(menuName);
        }
        return weekdaySaleCount(menuName);
    }

    public int weekendSaleCount(String menuName) {
        for (Menu m : Menu.values()) {
            if (m.getName().equals(menuName) && m.getCategory().equals("메인")) {
                return 1;
            }
        }
        return 0;
    }

    public int weekdaySaleCount(String menuName) {
        for (Menu m : Menu.values()) {
            if (m.getName().equals(menuName) && m.getCategory().equals("디저트")) {
                return 1;
            }
        }
        return 0;
    }
}
