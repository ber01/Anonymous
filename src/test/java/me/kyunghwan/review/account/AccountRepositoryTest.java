package me.kyunghwan.review.account;

import me.kyunghwan.review.BaseControllerTest;
import me.kyunghwan.review.mygenre.MyGenre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class AccountRepositoryTest extends BaseControllerTest {

    @DisplayName("Account를 조회하는 테스트")
    @Test
    @Transactional
    void findByEmailTest() {
        Account account = accountRepository.findByEmail(email);
        Set<MyGenre> genres = account.getMyGenres();
        for (MyGenre genre : genres) {
            assertThat(genre.getAccount().getEmail()).isEqualTo(email);
        }
        assertThat(genres.size()).isNotNull();
    }

}