package me.kyunghwan.review;

import com.google.gson.Gson;
import me.kyunghwan.review.account.AccountRepository;
import me.kyunghwan.review.movie.Genre;
import me.kyunghwan.review.movie.GenreRepository;
import me.kyunghwan.review.movie.MovieRepository;
import me.kyunghwan.review.moviegenre.MovieGenreRepository;
import me.kyunghwan.review.mygenre.MyGenreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("dev")
@DirtiesContext
public class BaseControllerTest {

    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Autowired
    protected GenreRepository genreRepository;

    @Autowired
    protected AccountRepository accountRepository;

    @Autowired
    protected MyGenreRepository myGenreRepository;

    @Autowired
    protected MovieRepository movieRepository;

    @Autowired
    protected MovieGenreRepository movieGenreRepository;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired
    protected Gson gson;

    protected String email = "email@email.com";
    protected String password = "1q2w3e4r!";

    @BeforeEach
    void init() {
        movieGenreRepository.deleteAll();
        myGenreRepository.deleteAll();
        movieRepository.deleteAll();
        genreRepository.deleteAll();
        accountRepository.deleteAll();

        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
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

}
