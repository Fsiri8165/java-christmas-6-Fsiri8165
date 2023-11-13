package christmas.domain;

public class Order {
    private int dateOfVisit;
    private String[][] orderHistory;

    public boolean isEventTarget() {
        return isEventTarget;
    }

    private int totalPrice;

    private boolean isEventTarget;

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

    public void setEventTarget(boolean isEventTarget) {
        this.isEventTarget = isEventTarget;
    }

    public boolean getEventTarget() {
        return isEventTarget;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
