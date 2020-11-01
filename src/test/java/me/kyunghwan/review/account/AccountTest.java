package me.kyunghwan.review.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AccountTest {

    @DisplayName("Account 객체 생성 테스트")
    @Test
    void beanTest() {
        String email = "email@email.com";
        String password = "password";

        Account account = Account.builder()
                .email(email)
                .password(password)
                .loginType(LoginType.CREDENTIAL)
                .isVerified(false)
                .build();

        assertThat(account.getEmail()).isEqualTo(email);
        assertThat(account.getPassword()).isEqualTo(password);
        assertThat(account.getLoginType()).isEqualTo(LoginType.CREDENTIAL);
        assertThat(account.getIsVerified()).isFalse();
        assertThat(account.getMyGenres()).isEmpty();
    }

}