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
        showBenefitsDetails();
    }

    public void showBenefitsDetails() {
        System.out.println("<혜택 내역>");
        if (!order.getEventTarget() || !showChristmasDdaySale(order.getDateOfVisit())) {
            System.out.println("없음");
            System.out.println();
        }
    }
    
    public void showBonusMenu() {
        System.out.println();
        System.out.println("<증정 메뉴>");
        int bonusCount = eventManager.getBonusMenu();
        if (bonusCount != 0) {
            System.out.printf("샴페인 %d개\n\n", bonusCount);
            return;
        }
        System.out.println("없음");
        System.out.println();
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
            totalPrice += eventManager.getMenuPrice(orders);
        }
        order.setTotalPrice(totalPrice);
        String totalPriceFormat = eventManager.priceFormat(totalPrice);
        System.out.println(totalPriceFormat);
        eventManager.isEventTargetValidate(totalPrice, order);
    }

    public boolean showChristmasDdaySale(int dateOfVisit) {
        if (dateOfVisit > 25) return false;
        int sale = 900 + (dateOfVisit * 100);
        System.out.printf("크리스마스 디데이 할인: -%s\n", eventManager.priceFormat(sale));
        return true;
    }
}
