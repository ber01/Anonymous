package me.kyunghwan.review.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AccountAdapterTest {

    @DisplayName("AccountAdapter 생성 테스트")
    @Test
    void test() {
        String email = "account@email.com";
        String password = "1q2w3e4r!";

        Account account = Account.builder()
                .email(email)
                .password(password)
                .loginType(LoginType.CREDENTIAL)
                .isVerified(false)
                .build();

        AccountAdapter accountAdapter = new AccountAdapter(account);

        assertThat(accountAdapter.getUsername()).isEqualTo(email);
        assertThat(accountAdapter.getPassword()).isEqualTo(password);
        assertThat(accountAdapter.getAuthorities()).isNotNull();
        assertThat(accountAdapter.isAccountNonExpired()).isTrue();
        assertThat(accountAdapter.isAccountNonLocked()).isTrue();
        assertThat(accountAdapter.isCredentialsNonExpired()).isTrue();
        assertThat(accountAdapter.isEnabled()).isTrue();
    }

}