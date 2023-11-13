package christmas.view;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
                    "<증정 메뉴>",
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
        String orderHistory = "티본스테이크-1";
        assertSimpleTest(() -> {
            run("27", orderHistory);
            assertThat(output()).contains(
                    "<혜택 내역>",
                    "없음"
            );
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}