package christmas.domain;

import christmas.view.InputView;
import christmas.view.OutputView;

public class EventPlanner {
    private final InputView inputView;
    private final OutputView outputView;

    public EventPlanner() {
        inputView = new InputView();
        outputView = new OutputView();
    }

    public void startPlanner() {
        Order order = inputView.takeOrder();
        int dateOfVisit = order.getDateOfVisit();
        String[][] orderHistory = order.getOrderHistory();
        outputView.showEventBenefits(dateOfVisit, orderHistory);
    }
}
