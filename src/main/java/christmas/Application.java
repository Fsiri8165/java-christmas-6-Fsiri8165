package christmas;

import christmas.domain.EventPlanner;

public class Application {

    private static final EventPlanner eventPlanner = new EventPlanner();

    public static void main(String[] args) {
        // TODO: 프로그램 구현
        eventPlanner.startPlanner();
    }
}
