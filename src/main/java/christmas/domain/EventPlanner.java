package christmas.domain;

import christmas.view.InputView;

public class EventPlanner {
    private final InputView inputView;

    public EventPlanner() {
        inputView = new InputView();
    }

    public void startPlanner() {
        Order order = inputView.takeOrder();
        System.out.println(order.getDateOfVisit() + "일 방문 예정");
        System.out.println("주문한 메뉴 : " + order.getOrderMenu());
    }
}
