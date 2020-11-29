package me.kyunghwan.review.movie;

import me.kyunghwan.review.exception.GenreNotFoundException;
import me.kyunghwan.review.exception.MovieNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("dev")
class MovieControllerTest {

    @Autowired
    MovieService movieService;

    MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    MovieRepository movieRepository;

    @BeforeEach
    void init() { this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 필터 추가
                .alwaysDo(print())
                .build();

        String[] names = {"드라마", "판타지", "서부", "공포", "멜로/로맨스", "모험", "스릴러", "느와르", "컬트", "다큐멘터리", "코미디", "가족", "미스터리", "전쟁", "애니메이션", "범죄", "뮤지컬", "SF", "액션", "무협", "에로", "서스펜스", "서사", "블랙코미디", "실험", "공연실황"};
        for (String name : names) {
            genreRepository.save(Genre.builder()
                    .name(name)
                    .build());
        }
    }

    private void saveMovie() throws Exception {
        movieService.saveMovie("MovieInfo.json");
    }

    @DisplayName("전체 영화를 조회하는 테스트") @DirtiesContext
    @Test
    void test() throws Exception {
        saveMovie();

        mockMvc.perform(get("/api/movies"))
                .andExpect(status().isOk());
    }

    @DisplayName("하나의 영화를 조회하는 테스트")
    @DirtiesContext
    @Test
    void test1() throws Exception {
        saveMovie();

        String name = "어벤져스";
        Movie movie = movieRepository.findByName(name).orElseThrow(() -> new MovieNotFoundException(name));

        Long idx = movie.getIdx();
        mockMvc.perform(get("/api/movies/" + idx))
                .andExpect(status().isOk());
    }

    @DisplayName("하나의 영화 조회에 실패하는 테스트")
    @DirtiesContext
    @Test
    void test2() throws Exception {
        int idx = 1000;
        mockMvc.perform(get("/api/movies/" + idx))
                .andExpect(jsonPath("message").exists())
                .andExpect(status().isBadRequest())
        ;
    }

    @DisplayName("장르 조회에 실패하는 테스트")
    @DirtiesContext
    @Test
    void test3() {
        genreRepository.deleteAll();
        GenreNotFoundException genreNotFoundException = assertThrows(GenreNotFoundException.class, () -> saveMovie());
        assertThat(genreNotFoundException.getMessage()).contains("장르");
    }

}