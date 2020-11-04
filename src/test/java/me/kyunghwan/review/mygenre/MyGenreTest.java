package me.kyunghwan.review.mygenre;

import me.kyunghwan.review.account.Account;
import me.kyunghwan.review.account.LoginType;
import me.kyunghwan.review.movie.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MyGenreTest {

    @DisplayName("MyGenre 객체 생성 테스트")
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

        String name = "name";
        Genre genre = Genre.builder()
                .name(name)
                .build();

        MyGenre myGenre = MyGenre.builder()
                .account(account)
                .genre(genre)
                .build();

        assertThat(myGenre.getAccount().getEmail()).isEqualTo(email);
        assertThat(myGenre.getGenre().getName()).isEqualTo(name);
    }

}