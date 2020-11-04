package me.kyunghwan.review.movie.dto;

import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class MovieInfoTest {

    @DisplayName("MovieInfo 객체 생성 테스트")
    @Test
    void beanTest() {
        String str = "  {\n" +
                "    \"code\":\"30688\",\n" +
                "    \"name\":\"해리 포터와 마법사의 돌\",\n" +
                "    \"runningTime\":152,\n" +
                "    \"openingDate\":{\n" +
                "      \"year\":2001,\n" +
                "      \"month\":12,\n" +
                "      \"day\":14\n" +
                "    },\n" +
                "    \"imageUrl\":\"https://movie-phinf.pstatic.net/20181019_236/1539926790655oHv5z_JPEG/movie_image.jpg\",\n" +
                "    \"movieGenres\":[\n" +
                "      2,\n" +
                "      6,\n" +
                "      12,\n" +
                "      19\n" +
                "    ]\n" +
                "  }";
        Gson gson = new Gson();
        MovieInfo movieInfo = gson.fromJson(str, MovieInfo.class);
        assertThat(movieInfo.getCode()).isEqualTo("30688");
        assertThat(movieInfo.getName()).isEqualTo("해리 포터와 마법사의 돌");
        assertThat(movieInfo.getRunningTime()).isEqualTo(152);
        assertThat(movieInfo.getOpeningDate()).isEqualTo(LocalDate.of(2001, 12, 14));
        assertThat(movieInfo.getImageUrl()).isEqualTo("https://movie-phinf.pstatic.net/20181019_236/1539926790655oHv5z_JPEG/movie_image.jpg");
        assertThat(movieInfo.getMovieGenres().size()).isEqualTo(4);
    }

}