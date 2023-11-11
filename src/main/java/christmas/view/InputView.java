package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Menu;
import christmas.domain.Order;
import java.util.Arrays;

public class InputView {

    private final Order order;
    private String[][] orderHistory;

    public InputView() {
        order = new Order();
    }

    public Order takeOrder() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        askDate();
        askMenu();
        return order;
    }

    public String getInput() {
        return Console.readLine();
    }

    public void askDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String inputVisitDate = getInput();
        try {
            int dateOfVisit = Integer.parseInt(inputVisitDate);
            dateInMonthValidate(dateOfVisit);
            order.setDateOfVisit(dateOfVisit);
        } catch (Exception e) {
            exceptionHandling("날짜");
            askDate();
        }
    }

    public void exceptionHandling(String errBy) {
        System.out.println("[ERROR] 유효하지 않은 " + errBy + "입니다. 다시 입력해 주세요.");
    }

    public void dateInMonthValidate(int dateOfVisit) {
        if (dateOfVisit > 31 || dateOfVisit < 1) {
            throw new IllegalArgumentException();
        }
    }

    public void askMenu() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String inputOrder = getInput();
        menuCount(inputOrder);
    }

    public void menuCount(String inputOrder) {
        try {
            String[] orderMenu = orderPatternValidate(inputOrder);
            menuCount(orderMenu);
            order.setOrderMenu(orderHistory);
        } catch (Exception e) {
            exceptionHandling("주문");
            askMenu();
        }
    }

    public void menuCount(String[] orderMenus) {
        String[][] order = new String[orderMenus.length][2];
        for (int i = 0; i < orderMenus.length; i++) {
            String[] history = menuValidate(i, orderMenus);
            order[i][0] = history[0];
            order[i][1] = history[1];
        }
        orderHistory = order;
    }

    public String[] menuValidate(int i, String[] orderMenus) {
        String[] orderMenu = orderMenus[i].split("-");
        isNotInMenu(orderMenu[0]);
        return orderMenu;
    }

    public void isNotInMenu(String orderMenu) {
        for (Menu m : Menu.values()) {
            String menu = m.getName();
            if (menu.equals(orderMenu)) return;
        }
        throw new IllegalArgumentException();
    }

    public String[] orderPatternValidate(String inputOrder) {
        String pattern = "^[가-힣]+-\\d+(,[가-힣]+-\\d+)*$";
        if (!inputOrder.matches(pattern)) {
            throw new IllegalArgumentException();
        }
        return inputOrder.split(",");
    }

}
