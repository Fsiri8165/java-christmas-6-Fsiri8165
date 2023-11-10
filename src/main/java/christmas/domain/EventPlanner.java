package christmas.domain;

import christmas.view.InputView;
import java.util.Arrays;

public class EventPlanner {
    private final InputView inputView;

    public EventPlanner() {
        inputView = new InputView();
    }

    public void startPlanner() {
        Order order = inputView.takeOrder();
        System.out.println(order.getDateOfVisit() + "일 방문 예정");
        for (String[] menu : order.getOrderMenu()) {
            System.out.println(Arrays.toString(menu));
        }
    }
}
