package me.kyunghwan.review.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.javac.util.List;
import me.kyunghwan.review.BaseControllerTest;
import me.kyunghwan.review.account.dto.AccountRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AccountControllerTest extends BaseControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @DisplayName("POST /api/accounts 201")
    @Test
    void test() throws Exception {
        AccountRequestDto requestDto = AccountRequestDto.builder()
                .email(email)
                .password(password)
                .myGenres(List.of("SF", "느와르"))
                .build();

        mockMvc.perform(post("/api/accounts")
                    .content(objectMapper.writeValueAsString(requestDto))
                    .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
        ;
    }

    @DisplayName("POST /api/accounts 400")
    @ParameterizedTest(name = "#{index} : {2}")
    @MethodSource("params")
    void test1(String paramEmail, String paramPwd, String msg) throws Exception {
        AccountRequestDto requestDto = AccountRequestDto.builder()
                .email(paramEmail)
                .password(paramPwd)
                .myGenres(List.of("SF", "느와르"))
                .build();

        mockMvc.perform(post("/api/accounts")
                    .content(objectMapper.writeValueAsString(requestDto))
                    .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    static Stream<Arguments> params() {
        String email = "email@email.com";
        String password = "1q2w3e4r5t!";
        return Stream.of(
                Arguments.of("email", password, "이메일 형식 오류 - @ 없음"),
                Arguments.of("", password, "이메일 형식 오류 - empty"),
                Arguments.of(" ", password, "이메일 형식 오류 - 공백"),
                Arguments.of(null, password, "이메일 형식 오류 - null"),
                Arguments.of(email, "", "비밀번호 형식 오류 - empty"),
                Arguments.of(email, " ", "비밀번호 형식 오류 - 공백"),
                Arguments.of(email, null, "비밀번호 형식 오류 - null"),
                Arguments.of(email, "!1234A", "비밀번호 형식 오류 - 길이 8 미만"),
                Arguments.of(email, "!1234567890987654321A", "비밀번호 형식 오류 - 길이 16 초과"),
                Arguments.of(email, "aaaaa!!!!!", "비밀번호 형식 오류 - 숫자 X"),
                Arguments.of(email, "12345!!!!!", "비밀번호 형식 오류 - 대소문자 X"),
                Arguments.of(email, "aaaaa12345", "비밀번호 형식 오류 - 특수문자 X"),
                Arguments.of(email, "!!!!!!!!!!", "비밀번호 형식 오류 - 숫자 X, 대소문자 X"),
                Arguments.of(email, "aaaaAAAAAA", "비밀번호 형식 오류 - 숫자 X, 특수문자 X"),
                Arguments.of(email, "1234123412", "비밀번호 형식 오류 - 대소문자 X, 특수문자 X")
        );
    }

    @DisplayName("POST /api/accounts/login 200")
    @Test
    void test2() throws Exception {
        accountRepository.save(Account.builder()
                .email(email)
                .password(password)
                .loginType(LoginType.CREDENTIAL)
                .isVerified(false)
                .build());

        AccountRequestDto requestDto = AccountRequestDto.builder()
                .email(email)
                .password(password)
                .build();

        mockMvc.perform(post("/api/accounts/login")
                    .content(objectMapper.writeValueAsString(requestDto))
                    .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("access_token").exists())
        ;
    }

}