package me.kyunghwan.review.config;

import me.kyunghwan.review.util.JsonUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JsonUtilsTest {

    @DisplayName("Json 형태로 잘 만들어지는지 테스트")
    @Test
    void test1() {
        String s = JsonUtils.toJson("key", "value");
        assertThat(s).isEqualTo("{\"key\":" + "\"value\"}");
    }

}