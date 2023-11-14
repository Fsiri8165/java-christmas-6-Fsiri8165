package christmas.view;

import christmas.domain.EventManager;
import christmas.domain.Order;
import java.util.List;

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
        String totalPrice = eventManager.getTotalPrice(orderHistory, order);
        System.out.println(totalPrice);
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

    public void showBenefitsDetails() {
        System.out.println("<혜택 내역>");
        int dateOfVisit = order.getDateOfVisit();
        if (!order.getEventTarget()) {
            System.out.println("없음");
        }
        int ddaySale = showChristmasDdaySale(dateOfVisit);
        int dateSale = showDateSale(dateOfVisit);
        int specialSale = showSpecialSale(dateOfVisit);
        int bonusPrice = showBonusPrice();
        System.out.println();
    }

    public int showChristmasDdaySale(int dateOfVisit) {
        if (dateOfVisit > 25) {
            return 0;
        }
        int ddaySale = 900 + (dateOfVisit * 100);
        String ddaySaleFormat = eventManager.priceFormat(ddaySale);
        System.out.printf("크리스마스 디데이 할인: -%s\n", ddaySaleFormat);
        return ddaySale;
    }

    public int showDateSale(int dateOfVisit) {
        String checkWeekend = eventManager.checkWeekend(dateOfVisit);
        int salePrice = eventManager.getDateSalePrice(checkWeekend);
        if (salePrice == 0) {
            return 0;
        }
        String salepriceFormat = eventManager.priceFormat(salePrice);
        System.out.printf("%s 할인: -%s\n", checkWeekend, salepriceFormat);
        return salePrice;
    }

    public int showSpecialSale(int dateOfVisit) {
        List<Integer> specialDays = List.of(3, 10, 17, 24, 25, 31);
        if (specialDays.contains(dateOfVisit)) {
            System.out.println("특별 할인: -1,000원");
            return 1000;
        }
        return 0;
    }

    public int showBonusPrice() {
        int bonusPrice = eventManager.getBonusMenu() * 25000;
        System.out.printf("증정 이벤트: -%s\n", eventManager.priceFormat(bonusPrice));
        return bonusPrice;
    }
}
