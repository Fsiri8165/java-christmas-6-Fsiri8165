package christmas.view;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.Application;
import org.junit.jupiter.api.Test;

class InputViewTest extends NsTest {
    @Test
    void 날짜_예외_테스트_범위_밖의_수를_입력했을_때() {
        날짜_예외_테스트("-1");
        날짜_예외_테스트("32");
    }

    @Test
    void 날짜_예외_테스트_문자가_포함_되었을_때() {
        날짜_예외_테스트("1일");
    }

    @Test
    void 주문_예외_테스트_한개_주문() {
        주문_예외_테스트("파스타 1개");
        주문_예외_테스트("1콜라-2");
        주문_예외_테스트("치킨-하나");
        주문_예외_테스트("-5");
        주문_예외_테스트("햄버거-");
        주문_예외_테스트("1-5");
    }

    @Test
    void 주문_예외_테스트_여러개_주문() {
        주문_예외_테스트("해산물파스타-1,");
        주문_예외_테스트("토마토파스타-1, 띄어쓰기하면안돼-3");
        주문_예외_테스트("첫번째메뉴가-이상함,피자-2");
    }

    @Test
    void 주문_예외_테스트_메뉴에_없는_주문() {
        주문_예외_테스트("굽네고추바사삭-3");
        주문_예외_테스트("해산물리조또-5");
    }

    void 날짜_예외_테스트(String date) {
        assertSimpleTest(() -> {
            runException(date);
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    void 주문_예외_테스트(String order) {
        assertSimpleTest(() -> {
            runException("1", order);
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}