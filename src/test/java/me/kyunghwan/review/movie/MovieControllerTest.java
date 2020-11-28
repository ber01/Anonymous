package me.kyunghwan.review.movie;

import javassist.NotFoundException;
import me.kyunghwan.review.BaseControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MovieControllerTest extends BaseControllerTest {

    @Autowired
    MovieService movieService;

    private void saveMovie() throws IOException {
        movieService.saveMovie("MovieInfo.json");
    }

    @DisplayName("전체 영화를 조회하는 테스트")
    @Test
    void test() throws Exception {
        saveMovie();

        mockMvc.perform(get("/api/movies"))
                .andExpect(status().isOk());
    }

    @DisplayName("하나의 영화를 조회하는 테스트")
    @Test
    void test1() throws Exception {
        saveMovie();

        String name = "어벤져스";
        Movie movie = movieRepository.findByName(name).orElseThrow(() -> new NotFoundException(name));

        Long idx = movie.getIdx();
        mockMvc.perform(get("/api/movies/" + idx))
                .andExpect(status().isOk());
    }

}