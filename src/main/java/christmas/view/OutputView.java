package christmas.view;

public class OutputView {
    public void showEventBenefits(int dateOfVisit, String[][] orderHistory) {
        System.out.println("12월" + dateOfVisit + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        showOrderHistory(orderHistory);
    }

    public void showOrderHistory(String[][] orderHistory) {
        System.out.println();
        System.out.println("<주문 메뉴>");
        for (String[] orders : orderHistory) {
            System.out.printf("%s %s개\n", orders[0], orders[1]);
        }
    }
}
