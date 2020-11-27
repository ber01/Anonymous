package me.kyunghwan.review.movie;

import me.kyunghwan.review.BaseControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MovieControllerTest extends BaseControllerTest {

    @Autowired
    MovieService movieService;

    @DisplayName("전체 영화를 조회하는 테스트")
    @Test
    void test() throws Exception {
        movieService.saveMovie("MovieInfo.json");

        mockMvc.perform(get("/api/movies"))
                .andExpect(status().isOk());
    }

}