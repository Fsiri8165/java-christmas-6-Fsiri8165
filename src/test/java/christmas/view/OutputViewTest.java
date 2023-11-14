package christmas.view;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.Application;
import org.junit.jupiter.api.Test;

class OutputViewTest extends NsTest {

    @Test
    void 주문_내역_테스트() {
        String orderHistory = "티본스테이크-1,제로콜라-1";
        assertSimpleTest(() -> {
            run("5", orderHistory);
            assertThat(output()).contains(
                    "<주문 메뉴>",
                    "티본스테이크 1개",
                    "제로콜라 1개"
            );
        });
    }

    @Test
    void 할인_전_주문_금액_확인() {
        String orderHistory = "제로콜라-3,초코케이크-2,티본스테이크-2,해산물파스타-3";
        assertSimpleTest(() -> {
            run("5", orderHistory);
            assertThat(output()).contains(
                    "<할인 전 총주문 금액>",
                    "254,000원"
            );
        });
    }

    @Test
    void 증정_메뉴_이벤트_120000원이_넘을_때() {
        String orderHistory = "티본스테이크-5,제로콜라-5";
        assertSimpleTest(() -> {
            run("5", orderHistory);
            assertThat(output()).contains(
                    "<증정 메뉴>",
                    "샴페인 2개"
            );
        });
    }

    @Test
    void 증정_메뉴_이벤트_120000원이_안넘을_때() {
        String orderHistory = "양송이수프-1,제로콜라-1";
        assertSimpleTest(() -> {
            run("5", orderHistory);
            assertThat(output()).contains(
                    "<증정 메뉴>",
                    "없음"
            );
        });
    }

    @Test
    void 주문금액이_10000원이_안될_때() {
        String orderHistory = "타파스-1";
        assertSimpleTest(() -> {
            run("5", orderHistory);
            assertThat(output()).contains(
                    "<혜택 내역>",
                    "없음"
            );
        });
    }

    @Test
    void 크리스마스_디데이_할인_이벤트_기간이_아닐_때() {
        String orderHistory = "27";
        assertSimpleTest(() -> {
            run(orderHistory, "티본스테이크-1");
            assertThat(output()).contains(
                    "<혜택 내역>",
                    "없음"
            );
        });
    }
    
    @Test
    void 요일_할인_주말인_경우() {
        String dateOfVisit = "3";
        assertSimpleTest(() -> {
            run(dateOfVisit, "티본스테이크-1,바비큐립-1,해산물파스타-1,크리스마스파스타-1");
            assertThat(output()).contains(
                    "<혜택 내역>",
                    "주말 할인: -8,092원"
            );
        });
    }

    @Test
    void 요일_할인_평일인_경우() {
        String dateOfVisit = "4";
        assertSimpleTest(() -> {
            run(dateOfVisit, "초코케이크-1,아이스크림-2,티본스테이크-3");
            assertThat(output()).contains(
                    "<혜택 내역>",
                    "평일 할인: -4,046원"
            );
        });
    }

    @Test
    void 특별_할인_테스트() {
        String dateOfVisit = "17";
        assertSimpleTest(() -> {
            run(dateOfVisit, "초코케이크-1,아이스크림-2,티본스테이크-3");
            assertThat(output()).contains(
                    "<혜택 내역>",
                    "특별 할인: -1,000원"
            );
        });
    }

    @Test
    void 증정_이벤트_가격_확인() {
        String dateOfVisit = "17";
        assertSimpleTest(() -> {
            run(dateOfVisit, "초코케이크-1,아이스크림-2,티본스테이크-3");
            assertThat(output()).contains(
                    "<혜택 내역>",
                    "증정 이벤트: -25,000원"
            );
        });
    }

    @Test
    void 총혜택_금액_확인() {
        String dateOfVisit = "16";
        assertSimpleTest(() -> {
            run(dateOfVisit, "티본스테이크-1,양송이수프-1,아이스크림-2,제로콜라-1,바비큐립-1");
            assertThat(output()).contains(
                    "<총혜택 금액>",
                    "31,546원"
            );
        });
    }

    @Test
    void 할인_후_금액_확인() {
        String dateOfVisit = "16";
        String orderHistory = "크리스마스파스타-3,초코케이크-2,레드와인-1,시저샐러드-2,아이스크림-1";
        assertSimpleTest(() -> {
            run(dateOfVisit, orderHistory);
            assertThat(output()).contains(
                    "<할인 후 예상 결제 금액>",
                    "181,477원"
            );
        });
    }

    @Test
    void 이벤트_배지_확인() {
        String dateOfVisit = "15";
        String orderHistory = "해산물파스타-1,초코케이크-1,티본스테이크-1,아이스크림-2,타파스-1,레드와인-1";
        assertSimpleTest(() -> {
            run(dateOfVisit, orderHistory);
            assertThat(output()).contains(
                    "<12월 이벤트 배지>",
                    "산타"
            );
        });
    }
    
    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}