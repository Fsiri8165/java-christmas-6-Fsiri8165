package christmas.view;

import christmas.domain.EventManager;
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
        if (isEventTarget()) {
            int[] sales = eventManager.sales(dateOfVisit);
            showEventDetails(sales);
            int eventTotalPrice = eventManager.getEventTotalPrice(sales);
            showTotalEventPrice(eventTotalPrice);
            showPriceAfterEvent(eventTotalPrice, sales[3]);
        }
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

    public boolean isEventTarget() {
        System.out.println("<혜택 내역>");
        if (!order.getEventTarget()) {
            System.out.println("없음");
            return false;
        }
        return true;

    }

    public void showEventDetails(int[] sales) {
        showChristmasDdaySale(sales[0]);
        showDateSale(sales[1]);
        showSpecialSale(sales[2]);
        showBonusPrice(sales[3]);
        System.out.println();
    }

    public void showChristmasDdaySale(int ddaySale) {
        String ddaySaleFormat = eventManager.priceFormat(ddaySale);
        System.out.printf("크리스마스 디데이 할인: -%s\n", ddaySaleFormat);
    }

    public void showDateSale(int salePrice) {
        int dateOfVisit = order.getDateOfVisit();
        String checkWeekend = eventManager.checkWeekend(dateOfVisit);
        String salepriceFormat = eventManager.priceFormat(salePrice);
        System.out.printf("%s 할인: -%s\n", checkWeekend, salepriceFormat);
    }

    public void showSpecialSale(int specialSalePrice) {
        if (specialSalePrice == 1000) {
            System.out.println("특별 할인: -1,000원");
        }
    }

    public void showBonusPrice(int bonusPrice) {
        String bonusPriceFormat = eventManager.priceFormat(bonusPrice);
        System.out.printf("증정 이벤트: -%s\n", bonusPriceFormat);
    }

    public void showTotalEventPrice(int eventTotalPrice) {
        System.out.println("<총혜택 금액>");
        System.out.printf("-%s\n", eventManager.priceFormat(eventTotalPrice));
        System.out.println();
    }

    public void showPriceAfterEvent(int eventTotalPrice, int specialPrice) {
        System.out.println("<할인 후 예상 결제 금액>");
        int priceAfterEvent = eventManager.getPriceAfterEvent(eventTotalPrice, specialPrice);
        System.out.println(eventManager.priceFormat(priceAfterEvent));
        System.out.println();
        showEventBedge(eventTotalPrice);
    }
    
    public void showEventBedge(int eventTotalPrice) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(eventManager.getEventBedge(eventTotalPrice));

    }
}
