package me.kyunghwan.review.movie.dto;

import me.kyunghwan.review.movie.Movie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class MovieResponseDtoTest {

    @DisplayName("MovieResponseDto 생성 테스트")
    @Test
    void test() {
        String code = "45290";
        String name = "인터스텔라";
        int runningTime = 169;
        LocalDate localDate = LocalDate.of(2014, 11, 6);
        String imageUrl = "imageUrl";

        Movie movie = Movie.builder()
                .code(code)
                .name(name)
                .runningTime(runningTime)
                .openingDate(localDate)
                .imageUrl(imageUrl)
                .build();

        MovieResponseDto movieResponseDto = new MovieResponseDto(movie);
        assertThat(movieResponseDto.getIdx()).isNull();
        assertThat(movieResponseDto.getCode()).isEqualTo(code);
        assertThat(movieResponseDto.getName()).isEqualTo(name);
        assertThat(movieResponseDto.getRunningTime()).isEqualTo(runningTime);
        assertThat(movieResponseDto.getOpeningDate()).isEqualTo(localDate);
        assertThat(movieResponseDto.getImageUrl()).isEqualTo(imageUrl);
        assertThat(movieResponseDto.getGenres()).isEmpty();
    }

}