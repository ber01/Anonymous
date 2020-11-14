package me.kyunghwan.review.account;

import me.kyunghwan.review.BaseControllerTest;
import me.kyunghwan.review.movie.Genre;
import me.kyunghwan.review.mygenre.MyGenre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class AccountRepositoryTest extends BaseControllerTest {

    @DisplayName("Account를 조회하는 테스트")
    @Test
    @Transactional
    void findByEmailTest() {
        Account account = accountRepository.save(Account.builder()
                .email(email)
                .password(password)
                .loginType(LoginType.CREDENTIAL)
                .isVerified(false)
                .build());

        String[] str = {"공포", "SF"};
        for (String s : str) {
            Genre genre = genreRepository.findByName(s);
            account.add(myGenreRepository.save(MyGenre.builder()
                    .account(account)
                    .genre(genre)
                    .build()));
        }

        account = accountRepository.findByEmail(email);
        Set<MyGenre> genres = account.getMyGenres();
        for (MyGenre genre : genres) {
            assertThat(genre.getAccount().getEmail()).isEqualTo(email);
        }
        assertThat(genres.size()).isNotNull();
    }

    @DisplayName("BaseTimeEntity 테스트")
    @Test
    void test() {
        Account account = accountRepository.save(Account.builder()
                .email(email)
                .password(password)
                .loginType(LoginType.CREDENTIAL)
                .isVerified(false)
                .build());

        assertThat(account.getCreatedAt()).isBefore(LocalDateTime.now());
        assertThat(account.getUpdatedAt()).isBefore(LocalDateTime.now());
    }

}