package me.kyunghwan.review.movie;

import me.kyunghwan.review.BaseControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class MovieServiceTest extends BaseControllerTest {

    @Autowired
    MovieService movieService;

    @DisplayName("Movie 를 저장하는 테스트")
    @Test
    void saveMovie() throws IOException {
        movieService.saveMovie("MovieInfo.json");
        int size = movieRepository.findAll().size();
        assertThat(size).isEqualTo(32);

        size = movieGenreRepository.findAll().size();
        assertThat(size).isEqualTo(102);
    }

}