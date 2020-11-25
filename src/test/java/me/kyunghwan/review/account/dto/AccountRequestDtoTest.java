package me.kyunghwan.review.account.dto;

import me.kyunghwan.review.BaseControllerTest;
import me.kyunghwan.review.account.Account;
import me.kyunghwan.review.account.LoginType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class AccountRequestDtoTest extends BaseControllerTest {

    @DisplayName("ReuqestDto 생성 테스트")
    @Test
    void test() {
        AccountRequestDto requestDto = getAccountRequestDto();

        assertThat(requestDto.getEmail()).isEqualTo(email);
        assertThat(requestDto.getPassword()).isEqualTo(password);
        assertThat(requestDto.getMyGenres().size()).isEqualTo(2);
    }

    private AccountRequestDto getAccountRequestDto() {
        return AccountRequestDto.builder()
                    .email(email)
                    .password(password)
                    .myGenres(new ArrayList<>(Arrays.asList("SF", "느와르")))
                    .build();
    }

    @DisplayName("Account 변환 후 저장 테스트")
    @Test
    void test2() {
        AccountRequestDto requestDto = getAccountRequestDto();
        Account account = accountRepository.save(requestDto.toEntity(passwordEncoder));

        assertThat(account.getEmail()).isEqualTo(email);
        assertThat(account.getPassword()).isNotEqualTo(password);
        assertThat(account.getLoginType()).isEqualTo(LoginType.CREDENTIAL);
        assertThat(account.getIsVerified()).isFalse();
        assertThat(account.getCreatedAt()).isBefore(LocalDateTime.now());
        assertThat(account.getUpdatedAt()).isBefore(LocalDateTime.now());
        assertThat(account.getMyGenres()).isEmpty();
    }

}