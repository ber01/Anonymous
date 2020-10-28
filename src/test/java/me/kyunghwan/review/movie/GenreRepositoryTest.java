package me.kyunghwan.review.movie;

import me.kyunghwan.review.BaseControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GenreRepositoryTest extends BaseControllerTest {

    @DisplayName("Genre를 가져오는 테스트")
    @Test
    void getGenre() {
        List<Genre> genres = genreRepository.findAll();
        assertThat(genres.size()).isEqualTo(26);
        assertThat(genreRepository.findByName("테스트")).isNull();
        assertThat(genreRepository.findByName("드라마")).isNotNull();
    }

}