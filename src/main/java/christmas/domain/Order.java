package christmas.domain;

public class Order {
    private int dateOfVisit;
    private String[][] orderHistory;

    public int getDateOfVisit() {
        return dateOfVisit;
    }

    public void setDateOfVisit(int dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    public String[][] getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(String[][] orderHistory) {
        this.orderHistory = orderHistory;
    }
}
