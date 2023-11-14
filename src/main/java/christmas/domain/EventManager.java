package christmas.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

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

    public int[] sales(int dateOfVisit) {
        int[] sales = new int[4];
        sales[0] = getChristmasDdaySale(dateOfVisit);
        sales[1] = getDateSale(dateOfVisit);
        sales[2] = getSpecialSale(dateOfVisit);
        sales[3] = getBonusPrice();
        return sales;
    }

    public int getChristmasDdaySale(int dateOfVisit) {
        if (dateOfVisit > 25) {
            return 0;
        }
        return 900 + (dateOfVisit * 100);
    }

    public int getDateSale(int dateOfVisit) {
        String checkWeekend = checkWeekend(dateOfVisit);
        int salePrice = getDateSalePrice(checkWeekend);
        if (salePrice == 0) {
            return 0;
        }
        return salePrice;
    }

    public int getSpecialSale(int dateOfVisit) {
        List<Integer> specialDays = List.of(3, 10, 17, 24, 25, 31);
        if (specialDays.contains(dateOfVisit)) {
            return 1000;
        }
        return 0;
    }

    public int getBonusPrice() {
        return getBonusMenu() * 25000;
    }

    public int getEventTotalPrice(int[] sales) {
        int eventTotalPrice = 0;
        for (int sale : sales) {
            eventTotalPrice += sale;
        }
        return eventTotalPrice;
    }

    public int getPriceAfterEvent(int eventTotalPrice, int specialPrice) {
        int totalEventPrice = eventTotalPrice - specialPrice;
        return totalPrice - totalEventPrice;
    }

    public String getEventBedge(int eventTotalPrice) {
        if (eventTotalPrice > 25000) {
            return "산타";
        }
        if (eventTotalPrice > 10000) {
            return "트리";
        }
        if (eventTotalPrice > 5000) {
            return "별";
        }
        return "없음";
    }
}
