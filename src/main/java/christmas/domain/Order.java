package christmas.domain;

public class Order {
    private int dateOfVisit;
    private String[][] orderMenu;

    public int getDateOfVisit() {
        return dateOfVisit;
    }

    public void setDateOfVisit(int dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    public String[][] getOrderMenu() {
        return orderMenu;
    }

    public void setOrderMenu(String[][] orderMenu) {
        this.orderMenu = orderMenu;
    }
}
