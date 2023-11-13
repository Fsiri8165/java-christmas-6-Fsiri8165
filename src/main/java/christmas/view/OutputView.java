package christmas.view;

import christmas.domain.EventManager;
import christmas.domain.Menu;
import christmas.domain.Order;

public class OutputView {

    private Order order;
    private String[][] orderHistory;
    private final EventManager eventManager;

    public OutputView() {
        eventManager = new EventManager();
    }

    public void showEventBenefits(Order o) {
        order = o;
        orderHistory = order.getOrderHistory();
        int dateOfVisit = order.getDateOfVisit();
        System.out.println("12월" + dateOfVisit + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        showOrderHistory();
        showBonusMenu();
    }

    public void showBonusMenu() {
        System.out.println();
        System.out.println("<증정 메뉴>");
        int bonusCount = eventManager.getBonusMenu(order);
        if (bonusCount != 0) {
            System.out.printf("샴페인 %d개\n\n", bonusCount);
            return;
        }
        System.out.println("없음");
    }

    public void showOrderHistory() {
        System.out.println();
        System.out.println("<주문 메뉴>");
        for (String[] orders : orderHistory) {
            System.out.printf("%s %s개\n", orders[0], orders[1]);
        }
        System.out.println();
        showTotalPrice();
    }

    public void showTotalPrice() {
        System.out.println("<할인 전 총주문 금액>");
        int totalPrice = 0;
        for (String[] orders : orderHistory) {
            totalPrice += getMenuPrice(orders);
        }
        order.setTotalPrice(totalPrice);
        System.out.println(priceFormat(totalPrice));
        isEventTargetValidate(totalPrice);
    }

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

    public void isEventTargetValidate(int totalPrice) {
        if (totalPrice < 10000) {
            order.setEventTarget(true);
            return;
        }
        order.setEventTarget(false);
    }

}
