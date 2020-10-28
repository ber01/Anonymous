package me.kyunghwan.review.account;

import me.kyunghwan.review.BaseControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AccountRepositoryTest extends BaseControllerTest {

    @DisplayName("Account를 저장하는 테스트")
    @Test
    void saveAccount() {
        String email = "email@email.com";
        String password = "1q2w3e4r!";

        Account account = Account.builder()
                .email(email)
                .password(password)
                .loginType(LoginType.CREDENTIAL)
                .isVerified(false)
                .build();

        account.getGenres().add(genreRepository.findByName("공포"));
        account.getGenres().add(genreRepository.findByName("SF"));

        Account save = accountRepository.save(account);

        assertThat(account.getEmail()).isEqualTo(save.getEmail());
        assertThat(account.getPassword()).isEqualTo(save.getPassword());
        assertThat(account.getLoginType()).isEqualTo(save.getLoginType());
        assertThat(account.getIsVerified()).isEqualTo(save.getIsVerified());
        assertThat(save.getGenres().size()).isEqualTo(2);
    }

}