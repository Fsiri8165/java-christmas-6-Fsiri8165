package christmas.domain;

import christmas.view.InputView;

public class EventPlanner {
    private final InputView inputView;

    public EventPlanner() {
        inputView = new InputView();
    }

    public void startPlanner() {
        inputView.showGuide();
    }
}
