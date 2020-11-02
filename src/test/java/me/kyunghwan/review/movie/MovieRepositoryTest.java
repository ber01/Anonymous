package me.kyunghwan.review.movie;


import me.kyunghwan.review.BaseControllerTest;
import me.kyunghwan.review.moviegenre.MovieGenre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class MovieRepositoryTest extends BaseControllerTest {

    @DisplayName("Movie를 저장하는 테스트")
    @Test
    @Transactional
    void movieTest() {
        String code = "45290";
        String name = "인터스텔라";
        int runningTime = 169;
        LocalDate localDate = LocalDate.of(2014, 11, 6);
        String imageUrl = "imageUrl";

        Movie movie = movieRepository.save(Movie.builder()
                .code(code)
                .name(name)
                .runningTime(runningTime)
                .openingDate(localDate)
                .imageUrl(imageUrl)
                .build());

        String[] arr = {"SF", "판타지"};
        for (String str : arr) {
            Genre genre = genreRepository.findByName(str);
            MovieGenre movieGenre = movieGenreRepository.save(MovieGenre.builder()
                    .movie(movie)
                    .genre(genre)
                    .build());
            movie.add(movieGenre);
            genre.add(movieGenre);
        }

        assertThat(movieGenreRepository.findAll().size()).isEqualTo(2);
    }

}