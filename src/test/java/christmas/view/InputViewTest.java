package christmas.view;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.Application;
import org.junit.jupiter.api.Test;

class InputViewTest extends NsTest {
    @Test
    void 날짜_예외_테스트_1보다_작은_수를_입력했을_때() {
        날짜_예외_테스트("-1");
    }

    @Test
    void 날짜_예외_테스트_31보다_큰_수를_입력했을_때() {
        날짜_예외_테스트("32");
    }

    @Test
    void 날짜_예외_테스트_문자가_포함_되었을_때() {
        날짜_예외_테스트("1일");
    }

    void 날짜_예외_테스트(String date) {
        assertSimpleTest(() -> {
            runException(date);
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}