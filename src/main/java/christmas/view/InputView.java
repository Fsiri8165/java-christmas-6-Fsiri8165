package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public void showGuide() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        String dateOfVisit = askDate();
        System.out.println(dateOfVisit);
        String orderMenu = askMenu();
        System.out.println(orderMenu);
    }

    public String getInput() {
        return Console.readLine();
    }

    public String askDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String dateOfvisit = getInput();
        return dateOfvisit;
    }

    public String askMenu() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String orderMenu = getInput();
        return orderMenu;
    }

}
