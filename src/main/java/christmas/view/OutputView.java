package christmas.view;

import christmas.domain.Menu;

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
        System.out.println();
        showTotalPrice(orderHistory);
    }

    public void showTotalPrice(String[][] orderHistory) {
        System.out.println("<할인 전 총주문 금액>");
        int totalPrice = 0;
        for (String[] orders : orderHistory) {
            totalPrice += getMenuPrice(orders);
        }
        System.out.println(priceFormat(totalPrice));
    }

    public int getMenuPrice(String[] orders) {
        int menuPrice = 0;
        int menuCount = Integer.parseInt(orders[1]);
        for (Menu m : Menu.values()) {
            String menuName = m.getName();
            if (menuName.equals(orders[0])) {
                menuPrice = m.getPrice() * menuCount;
            }
        }
        return menuPrice;
    }

    public String priceFormat(int price) {
        StringBuilder totalPrice = new StringBuilder();
        int count = 0;
        while (price > 0) {
            count++;
            totalPrice.append(price % 10);
            if (count % 3 == 0 && price / 10 > 0) {
                totalPrice.append(",");
            }
            price /= 10;
        }
        return totalPrice.reverse() + "원";
    }
}
