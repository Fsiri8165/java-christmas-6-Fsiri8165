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

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}