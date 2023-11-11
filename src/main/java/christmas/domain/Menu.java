package christmas.domain;

public enum Menu {
    MENU1("양송이수프",6000, "애피타이저"),
    MENU2("타파스", 5500, "애피타이저"),
    MENU3("시저샐러드", 8000, "애피타이저"),
    MENU4("티본스테이크", 55000, "메인"),
    MENU5("바비큐립", 54000, "메인"),
    MENU6("해산물파스타", 35000, "메인"),
    MENU7("크리스마스파스타", 25000, "메인"),
    MENU8("초코케이크", 15000, "디저트"),
    MENU9("아이스크림", 5000, "디저트"),
    MENU10("제로콜라", 3000, "음료"),
    MENU11("레드와인", 60000, "음료"),
    MENU12("샴페인", 25000, "음료");

    private final String name;
    private final int price;
    private final String category;

    public String getName() {return name;}

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    Menu(String name, int price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
}
